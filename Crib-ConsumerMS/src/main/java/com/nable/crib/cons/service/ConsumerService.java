/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 12, 2022 - 12:07:22 AM
 *  ***************************************
 */

package com.nable.crib.cons.service;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.nable.crib.cons.repository.ConsCatalogueDescriptionRepository;
import com.nable.crib.cons.repository.ConsCreditFacilityDetailsLast24MonthsRepository;
import com.nable.crib.cons.repository.ConsCreditFacilityDetailsRepository;
import com.nable.crib.cons.repository.ConsCreditFacilityOfGlanceStatusRepository;
import com.nable.crib.cons.repository.ConsCreditFacilityRepository;
import com.nable.crib.cons.repository.ConsDemographicDetailsRepository;
import com.nable.crib.cons.repository.ConsDishonouredChequeDetailsRepository;
import com.nable.crib.cons.repository.ConsDishonouredChequeSummaryRepository;
import com.nable.crib.cons.repository.ConsDisputeDetailsRepository;
import com.nable.crib.cons.repository.ConsEmploymentDetailsRepository;
import com.nable.crib.cons.repository.ConsIdentificationDetailsRepository;
import com.nable.crib.cons.repository.ConsInquiriesBySubjectRepository;
import com.nable.crib.cons.repository.ConsLast24MonthsRepository;
import com.nable.crib.cons.repository.ConsLendingInstutionsInquiriesRepository;
import com.nable.crib.cons.repository.ConsMailingAddressesRepository;
import com.nable.crib.cons.repository.ConsPermanentAddressesRepository;
import com.nable.crib.cons.repository.ConsPotentialAndCurrentLiabilitiesHeaderRepository;
import com.nable.crib.cons.repository.ConsPotentialAndCurrentLiabilitiesRepository;
import com.nable.crib.cons.repository.ConsRelationshipAddressDetailsRepository;
import com.nable.crib.cons.repository.ConsRelationshipDetailsRepository;
import com.nable.crib.cons.repository.ConsReportedNamesRepository;
import com.nable.crib.cons.repository.ConsSearchResultsRepository;
import com.nable.crib.cons.repository.ConsSettledCreditFacilitiesDetailsRepository;
import com.nable.crib.cons.repository.ConsSettledCreditFacilitiesSummaryRepository;
import com.nable.crib.cons.request.ConsumerApiInvokeRequest;
import com.nable.crib.cons.request.MultiHitRequestBean;
import com.nable.crib.cons.request.MultiHitRequestXmlGenerator;
import com.nable.crib.cons.request.PerHitCost;
import com.nable.crib.cons.request.RequestErrorRequest;
import com.nable.crib.cons.request.RequestType;
import com.nable.crib.cons.request.SingleHitRequestBean;
import com.nable.crib.cons.request.SingleHitRequestXmlGenerator;
import com.nable.crib.cons.response.ApiInvokeResponseBean;
import com.nable.crib.cons.response.ConsumerDataResponse;
import com.nable.crib.cons.response.Status;
import com.nable.crib.cons.soap.LiveRequestInvoker;
import com.nable.crib.cons.soap.LiveRequestInvokerSoap;
import com.nable.crib.cons.util.CommonUtilsMicroservice;
import com.nable.crib.cons.util.ConsumerResponseReader;
import com.nable.crib.cons.util.encrypt.CribEncryption;

@Service("ConsumerService")
@Transactional
public class ConsumerService {
	
	static Logger log = LoggerFactory.getLogger(ConsumerService.class);
	
	@Value("${crib.wsdl.url}")
	private URL wsdl;
	@Value("${crib.apiUsername}")
	private String apiUsername;
	@Value("${crib.apiPassword}")
	private String apiPassword;
	@Value("${crib.encryptionKey}")
	private String encryptionKey;
	@Value("${crib.decryptionKey}")
	private String decryptionKey;
	
	@Autowired
	ConsumerResponseReader conRsOp;
	
	@Autowired
	CommonUtilsMicroservice comnUtilsMS;
	
	@Autowired
    private ConsDemographicDetailsRepository consDemographicDetailsRepository;
	
	@Autowired
	private ConsIdentificationDetailsRepository consIdentificationDetailsRepository;
	
	@Autowired
	private ConsMailingAddressesRepository consMailingAddressesRepository;
	
	@Autowired
	private ConsPermanentAddressesRepository consPermanentAddressesRepository;
	
	@Autowired
	private ConsReportedNamesRepository consReportedNamesRepository;
	
	@Autowired
	private ConsEmploymentDetailsRepository consEmploymentDetailsRepository;
	
	@Autowired
	private ConsRelationshipDetailsRepository consRelationshipDetailsRepository;
	
	@Autowired
	private ConsRelationshipAddressDetailsRepository consRelationshipAddressDetailsRepository;
	
	@Autowired
	private ConsSettledCreditFacilitiesDetailsRepository consSettledCreditFacilitiesDetailsRepository;
	
	@Autowired
	private ConsSettledCreditFacilitiesSummaryRepository consSettledCreditFacilitiesSummaryRepository;
	
	@Autowired
	private ConsLendingInstutionsInquiriesRepository consLendingInstutionsInquiriesRepository;
	
	@Autowired
	private ConsInquiriesBySubjectRepository consInquiriesBySubjectRepository;
	
	@Autowired
	private ConsCreditFacilityRepository consCreditFacilityRepository;
	
	@Autowired
	private ConsCreditFacilityDetailsRepository consCreditFacilityDetailsRepository;
	
	@Autowired
	private ConsLast24MonthsRepository consLast24MonthsRepository;
	
	@Autowired
	private ConsCreditFacilityDetailsLast24MonthsRepository consCreditFacilityDetailsLast24MonthsRepository;
	
	@Autowired
	private ConsDisputeDetailsRepository consDisputeDetailsRepository;
	
	@Autowired
	private ConsPotentialAndCurrentLiabilitiesHeaderRepository consPotentialAndCurrentLiabilitiesHeaderRepository;
	
	@Autowired
	private ConsPotentialAndCurrentLiabilitiesRepository consPotentialAndCurrentLiabilitiesRepository;
	
	@Autowired
	private ConsCreditFacilityOfGlanceStatusRepository consCreditFacilityOfGlanceStatusRepository;
	
	@Autowired
	private ConsDishonouredChequeSummaryRepository consDishonouredChequeSummaryRepository;
	
	@Autowired
	private ConsDishonouredChequeDetailsRepository consDishonouredChequeDetailsRepository;
	
	@Autowired
	private ConsCatalogueDescriptionRepository consCatalogueDescriptionRepository;
	
	@Autowired
	private ConsSearchResultsRepository consSearchResultsRepository;
			
	public ApiInvokeResponseBean save(ConsumerApiInvokeRequest requestObj) throws XPathExpressionException {
		
		String requestXml = null;
		String responseType = null;
		String bureauRequestId = null;
		PerHitCost perHitCost = null;
		String errorCode = "";
		String error = "";
		ApiInvokeResponseBean responseBean;
		
		Long requestDetailId = requestObj.getRequestDetailId();
		String username = requestObj.getUsername();
		
		try {
			responseBean = new ApiInvokeResponseBean();
		
			if(requestObj.getRequestType().equals(RequestType.MULTI_HIT)) {
				MultiHitRequestBean multiHitRequestObj = requestObj.getMultiHitRequestBean();
				MultiHitRequestXmlGenerator reqXmlGen = new MultiHitRequestXmlGenerator();
				requestXml = reqXmlGen.generateReqXml(multiHitRequestObj);
				
				perHitCost = requestObj.getMultiHitRequestBean().getPerHitCost();
			}else {
				SingleHitRequestBean singleHitRequestObj = requestObj.getSingleHitRequestBean();	
				SingleHitRequestXmlGenerator reqXmlGen = new SingleHitRequestXmlGenerator();
				requestXml = reqXmlGen.generateReqXml(singleHitRequestObj);	
				
				perHitCost = requestObj.getSingleHitRequestBean().getPerHitCost();
			}
			log.info("RequestDetailId : "+requestDetailId+ " ------ invoked by : "+username);
			log.info("RequestXml : "+requestXml);
						
			LiveRequestInvoker client = new LiveRequestInvoker(wsdl);
			LiveRequestInvokerSoap soapClient = client.getLiveRequestInvokerSoap();
			
			CribEncryption cribEncryptor = new CribEncryption();
			String sRequestXml = cribEncryptor.encryptorSHA(requestXml, encryptionKey);
			String sUserName = cribEncryptor.encryptorSHA(apiUsername, encryptionKey);
			String sPassword = cribEncryptor.encryptorSHA(apiPassword, encryptionKey);
	    
			// Sending SOAP Request to CRIB API.
			log.info("Sending Consumer SOAP request to H2H CRIB API...");
			String sResponseXml = soapClient.postRequest(sRequestXml,sUserName,sPassword);
			log.info("Response received from H2H CRIB API.");
			//log.info("Encrypted ResponseXml : "+sResponseXml);
			
			String responseXml = cribEncryptor.decryptorSHA(sResponseXml, decryptionKey);
							
			Document doc = convertStringToXMLDocument(responseXml);							
			responseType = getResposeType(doc);
			if(responseType.equals("singleHit")) {
				
				consDemographicDetailsRepository.save(conRsOp.insrtDemographicDetails(doc, requestDetailId, username));
				consIdentificationDetailsRepository.saveAll(conRsOp.insrtIdentificationDetails(doc, requestDetailId, username));						
				consMailingAddressesRepository.saveAll(conRsOp.insrtMailingAddresses(doc, requestDetailId, username));
				consPermanentAddressesRepository.saveAll(conRsOp.insrtPermanentAddresses(doc, requestDetailId, username));
				consReportedNamesRepository.saveAll(conRsOp.insrtReportedNames(doc, requestDetailId, username));
				consEmploymentDetailsRepository.saveAll(conRsOp.insrtEmploymentDetails(doc, requestDetailId, username));
				consRelationshipDetailsRepository.saveAll(conRsOp.insrtRelationshipDetails(doc, requestDetailId, username));
				consRelationshipAddressDetailsRepository.saveAll(conRsOp.insrtRelationshipAddressDetails(doc, requestDetailId, username));
				consSettledCreditFacilitiesDetailsRepository.saveAll(conRsOp.insrtSettledCFDetails(doc, requestDetailId, username));
				consSettledCreditFacilitiesSummaryRepository.saveAll(conRsOp.insrtSettledCFSummary(doc, requestDetailId, username));
				consLendingInstutionsInquiriesRepository.saveAll(conRsOp.insrtLendingInstInquiries(doc, requestDetailId, username));
				consInquiriesBySubjectRepository.saveAll(conRsOp.insrtInquiriesBySubject(doc, requestDetailId, username));
				consCreditFacilityRepository.saveAll(conRsOp.insrtCreditFacility(doc, requestDetailId, username));
				consCreditFacilityDetailsRepository.saveAll(conRsOp.insrtCreditFacilityDetails(doc, requestDetailId, username));
				consLast24MonthsRepository.saveAll(conRsOp.insrtLast24Months(doc, requestDetailId, username));
				consCreditFacilityDetailsLast24MonthsRepository.saveAll(conRsOp.insrtCFForLast24Months(doc, requestDetailId, username));
				consDisputeDetailsRepository.saveAll(conRsOp.insrtDisputeDetails(doc, requestDetailId, username));
				consPotentialAndCurrentLiabilitiesHeaderRepository.saveAll(conRsOp.insrtPotAndCurrLiabilitiesHeader(doc, requestDetailId, username));
				consPotentialAndCurrentLiabilitiesRepository.saveAll(conRsOp.insrtPotAndCurrLiabilities(doc, requestDetailId, username));
				consCreditFacilityOfGlanceStatusRepository.saveAll(conRsOp.insrtCFOfGlanceStatus(doc, requestDetailId, username));
				consDishonouredChequeSummaryRepository.saveAll(conRsOp.insrtDishonChequeSummary(doc, requestDetailId, username));
				consDishonouredChequeDetailsRepository.saveAll(conRsOp.insrtDishonChequeDetails(doc, requestDetailId, username));
				consCatalogueDescriptionRepository.saveAll(conRsOp.insrtCatalogueDescription(doc, requestDetailId, username));
				consSearchResultsRepository.saveAll(conRsOp.insrtSearchResults(doc, requestDetailId, username));
	
				bureauRequestId = doc.getDocumentElement().getAttribute("REQUEST-ID");
				comnUtilsMS.updateRequestDetails(requestDetailId, bureauRequestId, 1, 1, Status.SINGLE_HIT, "-", "-", perHitCost.getHit(), username);
								
				// Setting ResponseBean
				responseBean.setPerHitCost(perHitCost.getHit());
				responseBean.setStatusCode(Status.SINGLE_HIT.getStatusCode());
				responseBean.setStatus(Status.SINGLE_HIT.getStatus());
				responseBean.setMessage("Insert Crib Data Successful");
				responseBean.setSearchResults(conRsOp.getSearchResults(doc));
				
			}else if(responseType.equals("multiHit")) {
	
				bureauRequestId = doc.getDocumentElement().getAttribute("REFERENCE-NO");
				consSearchResultsRepository.saveAll(conRsOp.insrtSearchResults(doc, requestDetailId, username));
				comnUtilsMS.updateRequestDetails(requestDetailId, bureauRequestId, 0, 1, Status.MULTI_HIT, "-", "-", perHitCost.getMultiHit(), username);
				
				// Setting ResponseBean
				responseBean.setPerHitCost(perHitCost.getMultiHit());
				responseBean.setStatusCode(Status.MULTI_HIT.getStatusCode());
				responseBean.setStatus(Status.MULTI_HIT.getStatus());
				responseBean.setMessage("MultiHit Response Received");
				responseBean.setMultiHit(conRsOp.getMultiHitResponse(doc));
				
			}else if(responseType.equals("noHit")) {
				bureauRequestId = doc.getDocumentElement().getAttribute("REQUEST-ID");
				comnUtilsMS.updateRequestDetails(requestDetailId, bureauRequestId, 0, 0, Status.NO_HIT, "-", "-", perHitCost.getNoHit(), username);
				
				// Setting ResponseBean
				responseBean.setPerHitCost(perHitCost.getNoHit());
				responseBean.setStatusCode(Status.NO_HIT.getStatusCode());
				responseBean.setStatus(Status.NO_HIT.getStatus());
				responseBean.setMessage("NoHit Response Received");
			}else {	
				bureauRequestId = doc.getDocumentElement().getAttribute("REQUEST-ID");				
				
				List<RequestErrorRequest> requestErrorRequestList = new ArrayList<RequestErrorRequest>();
				List<String> eCode = new ArrayList<String>();
				List<String> e = new ArrayList<String>();
				for(int x=0; x<doc.getElementsByTagName("ERROR").getLength(); x++) {
					String errCode = (doc.getElementsByTagName("ERROR-CODE").getLength() > 0) ? doc.getElementsByTagName("ERROR-CODE").item(0).getTextContent() : "-";
					String err = (doc.getElementsByTagName("ERROR").getLength() > 0) ? doc.getElementsByTagName("ERROR").item(0).getTextContent() : "-";
					
					RequestErrorRequest requestError = new RequestErrorRequest();
					requestError.setRequestDetailId(requestDetailId);
					requestError.setErrorCode(errCode);
					requestError.setErrorDescription(err);
					requestErrorRequestList.add(requestError);
					eCode.add(errCode);
					e.add(err);
				}
				errorCode = String.join(", ", eCode);
				error = String.join(", ", e);
				comnUtilsMS.addRequestError(requestDetailId, requestErrorRequestList, username);
				comnUtilsMS.updateRequestDetails(requestDetailId, bureauRequestId, 0, 0, Status.ERROR_HIT, errorCode, error, 0.0, username);
				
				// Setting ResponseBean
				responseBean.setErrorCode(errorCode);
				responseBean.setError(error);
				responseBean.setStatusCode(Status.ERROR_HIT.getStatusCode());
				responseBean.setStatus(Status.ERROR_HIT.getStatus());
				responseBean.setMessage(error);
			}
			
			log.info("#--------------------------------- Request : "+requestDetailId+" completed ---------------------------------#");							
			
		}catch(Exception e) {
			StringWriter sw1 = new StringWriter();
			PrintWriter pw1 = new PrintWriter(sw1);
			e.printStackTrace(pw1);
			
			e.printStackTrace();
			log.info("Exception occured : " +sw1.toString());
			
			Double pcost = 0.0;
			Status status = Status.API_ERROR;
			String errCode = "";
			String err = "";
			int isHit = 0;
			int isCribAvailable = 0;
							
			if(responseType != null) {
				if(responseType.equals("singleHit")) {
					isHit = 1;	
					pcost = perHitCost.getHit();
				}else if(responseType.equals("multiHit")) {
					isHit = 1;	
					pcost = perHitCost.getMultiHit();
				}else if(responseType.equals("noHit")) {
					isHit = 1;	
					pcost = perHitCost.getNoHit();
				}				
				status = Status.APPLICATION_ERROR;
				errCode = errorCode;
				err = error;
			}
			
			responseBean = new ApiInvokeResponseBean();
			responseBean.setPerHitCost(pcost);
			responseBean.setStatusCode(status.getStatusCode());
			responseBean.setStatus(status.getStatus());
			responseBean.setErrorCode(errCode);
			
			try {
				comnUtilsMS.updateRequestDetails(requestDetailId, bureauRequestId, isCribAvailable, isHit, status, status.getStatusCode(), e.fillInStackTrace().toString(), pcost, username);
			} catch (Exception ex) {
				StringWriter sw2 = new StringWriter();
				PrintWriter pw2 = new PrintWriter(sw2);
				ex.printStackTrace(pw2);
				
				ex.printStackTrace();
				log.info("Exception occured : " +sw2.toString());
				
				responseBean.setMessage("Error occurred : "+ex.fillInStackTrace());				
				responseBean.setError(err);
			}
						
			responseBean.setMessage("Error occurred : "+e.fillInStackTrace());			
			responseBean.setError(err);								
		}
		
		return responseBean;
	}
	
	public ConsumerDataResponse get(Long requestDetailId) {
		
		ConsumerDataResponse consumerDataResponse = new ConsumerDataResponse();
		
		consumerDataResponse.setConsDemographicDetails(consDemographicDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsIdentificationDetailsList(consIdentificationDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsMailingAddressesList(consMailingAddressesRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsPermanentAddressesList(consPermanentAddressesRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsReportedNamesList(consReportedNamesRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsEmploymentDetailsList(consEmploymentDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsRelationshipDetailsList(consRelationshipDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsRelationshipAddressDetailsList(consRelationshipAddressDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsSettledCreditFacilitiesDetailsList(consSettledCreditFacilitiesDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsSettledCreditFacilitiesSummaryList(consSettledCreditFacilitiesSummaryRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsLendingInstutionsInquiriesList(consLendingInstutionsInquiriesRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsInquiriesBySubjectList(consInquiriesBySubjectRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsCreditFacilityList(consCreditFacilityRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsCreditFacilityDetailsList(consCreditFacilityDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsLast24MonthsList(consLast24MonthsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsCreditFacilityDetailsLast24MonthsList(consCreditFacilityDetailsLast24MonthsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsDisputeDetailsList(consDisputeDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsPotentialAndCurrentLiabilitiesHeaderList(consPotentialAndCurrentLiabilitiesHeaderRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsPotentialAndCurrentLiabilitiesList(consPotentialAndCurrentLiabilitiesRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsCreditFacilityOfGlanceStatusList(consCreditFacilityOfGlanceStatusRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsDishonouredChequeSummaryList(consDishonouredChequeSummaryRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsDishonouredChequeDetailsList(consDishonouredChequeDetailsRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsCatalogueDescriptionList(consCatalogueDescriptionRepository.findByRequestDetailId(requestDetailId));
		consumerDataResponse.setConsSearchResultsList(consSearchResultsRepository.findByRequestDetailId(requestDetailId));
			
		consumerDataResponse.setStatus(Status.SUCCESS.getStatus());
		consumerDataResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		consumerDataResponse.setMessage("Get Crib Data Successful");
		
		return consumerDataResponse;
	}
	
	// Convert XML string to XML document.
	private static Document convertStringToXMLDocument(String xmlString) {
		
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try{
            builder = factory.newDocumentBuilder();
            xmlString = sanitizeXmlChars(xmlString);
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            doc.getDocumentElement().normalize();
            return doc;
        }catch(Exception e){
            e.printStackTrace();
            log.info("Error convertStringToXMLDocument : Error occured while converting H2H consumer response String to XML document");
            log.info("Error convertStringToXMLDocument : " +e.fillInStackTrace());
        }
        return null;
    }
	
	// Clear XML (Remove invalid characters)
	private static String sanitizeXmlChars(String xmlString) {
		
	    if (xmlString == null) {
	    	return null;
	    }else if(xmlString.equals("")) {
	    	return "";
	    }else {
	    	Pattern xmlInvalidChars = Pattern.compile("[^\\u0009\\u000A\\u000D\\u0020-\\uD7FF\\uE000-\\uFFFD\\x{10000}-\\x{10FFFF}]");
	    	return xmlInvalidChars.matcher(xmlString).replaceAll("").replaceAll("&(?!amp;)", "&amp;");
	    }
	}
	
	// Return response type
	private String getResposeType(Document doc) {
		
		String responseType = null;			
		String responseTypeCode = doc.getElementsByTagName("RESPONSE-TYPE").item(0).getAttributes().item(0).getTextContent();
		System.out.println("ResponseTypeCode: "+responseTypeCode);

		if(responseTypeCode.equals("1")){
			responseType = "singleHit";
		}else if(responseTypeCode.equals("2")){
			responseType = "noHit";
		}else if(responseTypeCode.equals("3")){
			responseType = "multiHit";
		}else{
			responseType = "error";
		}
        
        System.out.println("ResponseType: "+responseType);
        log.info("ResponseType: "+responseType);
		return responseType;
	}

}
