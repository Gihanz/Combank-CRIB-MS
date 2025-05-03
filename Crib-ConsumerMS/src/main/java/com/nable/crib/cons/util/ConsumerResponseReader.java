package com.nable.crib.cons.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.nable.crib.cons.model.ConsCatalogueDescription;
import com.nable.crib.cons.model.ConsCreditFacility;
import com.nable.crib.cons.model.ConsCreditFacilityDetails;
import com.nable.crib.cons.model.ConsCreditFacilityDetailsLast24Months;
import com.nable.crib.cons.model.ConsCreditFacilityOfGlanceStatus;
import com.nable.crib.cons.model.ConsDemographicDetails;
import com.nable.crib.cons.model.ConsDishonouredChequeDetails;
import com.nable.crib.cons.model.ConsDishonouredChequeSummary;
import com.nable.crib.cons.model.ConsDisputeDetails;
import com.nable.crib.cons.model.ConsEmploymentDetails;
import com.nable.crib.cons.model.ConsIdentificationDetails;
import com.nable.crib.cons.model.ConsInquiriesBySubject;
import com.nable.crib.cons.model.ConsLast24Months;
import com.nable.crib.cons.model.ConsLendingInstutionsInquiries;
import com.nable.crib.cons.model.ConsMailingAddresses;
import com.nable.crib.cons.model.ConsPermanentAddresses;
import com.nable.crib.cons.model.ConsPotentialAndCurrentLiabilities;
import com.nable.crib.cons.model.ConsPotentialAndCurrentLiabilitiesHeader;
import com.nable.crib.cons.model.ConsRelationshipAddressDetails;
import com.nable.crib.cons.model.ConsRelationshipDetails;
import com.nable.crib.cons.model.ConsReportedNames;
import com.nable.crib.cons.model.ConsSearchResults;
import com.nable.crib.cons.model.ConsSettledCreditFacilitiesDetails;
import com.nable.crib.cons.model.ConsSettledCreditFacilitiesSummary;
import com.nable.crib.cons.response.MultiHit;
import com.nable.crib.cons.response.SearchResultItem;
import com.nable.crib.cons.response.SearchResults;

@Component
@PropertySource("classpath:application.properties")
public class ConsumerResponseReader {
		
	static Logger log = LoggerFactory.getLogger(ConsumerResponseReader.class);
	
	@Value("${crib.cons.s_consumerDetails}")
	private String s_consumerDetails;
	@Value("${crib.cons.s_identificationDetails}")
	private String s_identificationDetails;
	@Value("${crib.cons.s_mailingAddresses}")
	private String s_mailingAddresses;	
	@Value("${crib.cons.s_permanentAddresses}")
	private String s_permanentAddresses;
	@Value("${crib.cons.s_reportedNames}")
	private String s_reportedNames;
	@Value("${crib.cons.s_employmentDetails}")
	private String s_employmentDetails;
	@Value("${crib.cons.s_relationships}")
	private String s_relationships;
	@Value("${crib.cons.s_relationshipAddresses}")
	private String s_relationshipAddresses;
	@Value("${crib.cons.s_settledCFDetils}")
	private String s_settledCFDetils;
	@Value("${crib.cons.s_settledCFSummary}")
	private String s_settledCFSummary;
	@Value("${crib.cons.s_lendingInstInquiries}")
	private String s_lendingInstInquiries;
	@Value("${crib.cons.s_inqBySubject}")
	private String s_inqBySubject;
	@Value("${crib.cons.s_creditFacility}")
	private String s_creditFacility;
	@Value("${crib.cons.s_cFDetails}")
	private String s_cFDetails;
	@Value("${crib.cons.s_last24Months}")
	private String s_last24Months;
	@Value("${crib.cons.s_cfFor24Months}")
	private String s_cfFor24Months;
	@Value("${crib.cons.s_disputeDetails}")
	private String s_disputeDetails;
	@Value("${crib.cons.s_potAndCurrLiabilitiesHeader}")
	private String s_potAndCurrLiabilitiesHeader;
	@Value("${crib.cons.s_potAndCurrLiabilities}")
	private String s_potAndCurrLiabilities;
	@Value("${crib.cons.s_cFOfGlanceStatus}")
	private String s_cFOfGlanceStatus;
	@Value("${crib.cons.s_dishonChequeSummary}")
	private String s_dishonChequeSummary;
	@Value("${crib.cons.s_dishonChequeDetails}")
	private String s_dishonChequeDetails;
	@Value("${crib.cons.s_catalogueDescription}")
	private String s_catalogueDescription;
		
	Date created = new Date(System.currentTimeMillis());
	XPath xPath = XPathFactory.newInstance().newXPath();
	
		//================ Setting Demographic Details ================//
		public ConsDemographicDetails insrtDemographicDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			
			NodeList nodeList = (NodeList) xPath.compile(s_consumerDetails).evaluate(doc, XPathConstants.NODESET);

			Element consumerDetails = (Element) nodeList.item(0);
			String name              =  consumerDetails.getElementsByTagName("NAME").item(0).getTextContent();
			String dob               = (consumerDetails.getElementsByTagName("DATE_OF_BIRTH").getLength() > 0) ? consumerDetails.getElementsByTagName("DATE_OF_BIRTH").item(0).getTextContent() : null;
			String gender            = (consumerDetails.getElementsByTagName("GENDER").getLength() > 0) ? consumerDetails.getElementsByTagName("GENDER").item(0).getTextContent() : "-";
			String spouseName        = (consumerDetails.getElementsByTagName("SPOUSE_NAME").getLength() > 0) ? consumerDetails.getElementsByTagName("SPOUSE_NAME").item(0).getTextContent() : "-";
			String citizenship       = (consumerDetails.getElementsByTagName("CITIZENSHIP").getLength() > 0) ? consumerDetails.getElementsByTagName("CITIZENSHIP").item(0).getTextContent() : "-";
			String maritalStatus     = (consumerDetails.getElementsByTagName("MARITAL_STATUS").getLength() > 0) ? consumerDetails.getElementsByTagName("MARITAL_STATUS").item(0).getTextContent() : "-";
			String telephoneAreaCode = (consumerDetails.getElementsByTagName("TELEPHONE_AREA_CODE").getLength() > 0) ? consumerDetails.getElementsByTagName("TELEPHONE_AREA_CODE").item(0).getTextContent() : "-";
			String telephoneNumber   = (consumerDetails.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? consumerDetails.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
			String mobileNumber      = (consumerDetails.getElementsByTagName("MOBILE_NUMBER").getLength() > 0) ? consumerDetails.getElementsByTagName("MOBILE_NUMBER").item(0).getTextContent() : "-";
			String email             = (consumerDetails.getElementsByTagName("EMAIL_ID").getLength() > 0) ? consumerDetails.getElementsByTagName("EMAIL_ID").item(0).getTextContent() : "-";
			String ruid              = (consumerDetails.getElementsByTagName("RUID").getLength() > 0) ? consumerDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;
			String nicNumber         = (consumerDetails.getElementsByTagName("NIC").getLength() > 0) ? consumerDetails.getElementsByTagName("NIC").item(0).getTextContent().trim() : "-";
			String nicRuid           = (consumerDetails.getElementsByTagName("NIC_RUID").getLength() > 0) ? consumerDetails.getElementsByTagName("NIC_RUID").item(0).getTextContent() : null;
			String passportNumber    = (consumerDetails.getElementsByTagName("PAS").getLength() > 0) ? consumerDetails.getElementsByTagName("PAS").item(0).getTextContent().trim() : "-";
			String passportRuid      = (consumerDetails.getElementsByTagName("PAS_RUID").getLength() > 0) ? consumerDetails.getElementsByTagName("PAS_RUID").item(0).getTextContent() : null;
			String rtoRuid           = (consumerDetails.getElementsByTagName("RTO_RUID").getLength() > 0) ? consumerDetails.getElementsByTagName("RTO_RUID").item(0).getTextContent() : null;
			String blockFlag         = (consumerDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? consumerDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
										
			ConsDemographicDetails consDemographicDetails = new ConsDemographicDetails();
			consDemographicDetails.setRequestDetailId(requestDetailId);
			consDemographicDetails.setName(name);
			consDemographicDetails.setDob(formatDates(dob));
			consDemographicDetails.setGender(gender);
			consDemographicDetails.setSpouseName(spouseName);
			consDemographicDetails.setCitizenship(citizenship);
			consDemographicDetails.setMaritalStatus(maritalStatus);
			consDemographicDetails.setTelephoneAreaCode(telephoneAreaCode);
			consDemographicDetails.setTelephoneNumber(telephoneNumber);
			consDemographicDetails.setMobileNumber(mobileNumber);
			consDemographicDetails.setEmailId(email);
			consDemographicDetails.setRuId(formatLongs(ruid));
			consDemographicDetails.setNicNumber(nicNumber);
			consDemographicDetails.setNicRuId(formatLongs(nicRuid));
			consDemographicDetails.setPassportNumber(passportNumber);
			consDemographicDetails.setPasRuId(formatLongs(passportRuid));
			consDemographicDetails.setRtoRuId(formatLongs(rtoRuid));
			consDemographicDetails.setBlockFlag(formatShorts(blockFlag));
			consDemographicDetails.setIsActive(1);
			consDemographicDetails.setCreatedBy(username);
			consDemographicDetails.setCreated(created);			
			
			return consDemographicDetails;
		}
		
		//================ Setting Identification Details ================//
		public List<ConsIdentificationDetails> insrtIdentificationDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
								
			List<ConsIdentificationDetails> consIdentificationDetailsList = new ArrayList<ConsIdentificationDetails>();	  
			NodeList nodeList = (NodeList) xPath.compile(s_identificationDetails).evaluate(doc, XPathConstants.NODESET);
							
			for(int x=0; x<nodeList.getLength(); x++) {
									
				Element identificationDetails = (Element) nodeList.item(x);
				if(identificationDetails.hasChildNodes()) {	
					String sourceId      = (identificationDetails.getElementsByTagName("SOURCE_ID").getLength() > 0) ? identificationDetails.getElementsByTagName("SOURCE_ID").item(0).getTextContent() : "-";
					String idValue       = (identificationDetails.getElementsByTagName("ID_VALUE").getLength() > 0) ? identificationDetails.getElementsByTagName("ID_VALUE").item(0).getTextContent() : "-";
					String idDisplayName = (identificationDetails.getElementsByTagName("ID_DISPLAY_NAME").getLength() > 0) ? identificationDetails.getElementsByTagName("ID_DISPLAY_NAME").item(0).getTextContent() : "-";
					String ruid          = (identificationDetails.getElementsByTagName("RUID").getLength() > 0) ? identificationDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;			
													
					ConsIdentificationDetails consIdentificationDetails = new ConsIdentificationDetails();
					consIdentificationDetails.setRequestDetailId(requestDetailId);
					consIdentificationDetails.setSourceId(sourceId);
					consIdentificationDetails.setIdValue(idValue);
					consIdentificationDetails.setIdDisplayName(idDisplayName);
					consIdentificationDetails.setRuId(formatLongs(ruid));
					consIdentificationDetails.setIsActive(1);					
					consIdentificationDetails.setCreatedBy(username);
					consIdentificationDetails.setCreated(created);
					
					consIdentificationDetailsList.add(consIdentificationDetails);
				}
			}
			return consIdentificationDetailsList;
		} 
		
		//================ Setting Mailing Details ================//
		public List<ConsMailingAddresses> insrtMailingAddresses(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			
			List<ConsMailingAddresses> consMailingAddressesList = new ArrayList<ConsMailingAddresses>();	    		    
		    NodeList nodeList = (NodeList) xPath.compile(s_mailingAddresses).evaluate(doc, XPathConstants.NODESET);
				
			for(int x=0; x<nodeList.getLength(); x++) {
				
				Element mailingAddresses = (Element) nodeList.item(x);
				if(mailingAddresses.hasChildNodes()) {	
					String sNo          = (mailingAddresses.getElementsByTagName("SERIAL_NO").getLength() > 0) ? mailingAddresses.getElementsByTagName("SERIAL_NO").item(0).getTextContent() : null;
					String reportedDate = (mailingAddresses.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? mailingAddresses.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String address      = (mailingAddresses.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? mailingAddresses.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent().replace("'", "`") : "-";
					String blockFlag    = (mailingAddresses.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? mailingAddresses.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					String ruid         = (mailingAddresses.getElementsByTagName("RUID").getLength() > 0) ? mailingAddresses.getElementsByTagName("RUID").item(0).getTextContent() : null;
					
					ConsMailingAddresses consMailingAddresses = new ConsMailingAddresses();
					consMailingAddresses.setRequestDetailId(requestDetailId);
					consMailingAddresses.setSNo(formatInts(sNo));
					consMailingAddresses.setReportedDate(formatDates(reportedDate));
					consMailingAddresses.setAddress(address);
					consMailingAddresses.setBlockFlag(formatShorts(blockFlag));
					consMailingAddresses.setRuId(formatLongs(ruid));
					consMailingAddresses.setIsActive(1);					
					consMailingAddresses.setCreatedBy(username);	
					consMailingAddresses.setCreated(created);
					
					consMailingAddressesList.add(consMailingAddresses);					
				}
			}		
			return consMailingAddressesList;
		}    
				
		//================ Setting Permanent Details ================//
		public List<ConsPermanentAddresses> insrtPermanentAddresses(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
		    
			List<ConsPermanentAddresses> consPermanentAddressesList = new ArrayList<ConsPermanentAddresses>();			
			NodeList nodeList = (NodeList) xPath.compile(s_permanentAddresses).evaluate(doc, XPathConstants.NODESET);
				
			for(int x=0; x<nodeList.getLength(); x++) {
			  			
			  	Element permanentAddresses = (Element) nodeList.item(x);
			  	if(permanentAddresses.hasChildNodes()) {		
				  	String sNo          = (permanentAddresses.getElementsByTagName("SERIAL_NO").getLength() > 0) ? permanentAddresses.getElementsByTagName("SERIAL_NO").item(0).getTextContent() : null;
				  	String reportedDate = (permanentAddresses.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? permanentAddresses.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
				  	String address      = (permanentAddresses.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? permanentAddresses.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent().replace("'", "`") : "-";
				  	String blockFlag    = (permanentAddresses.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? permanentAddresses.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
								
					ConsPermanentAddresses consPermanentAddresses = new ConsPermanentAddresses();
					consPermanentAddresses.setRequestDetailId(requestDetailId);
					consPermanentAddresses.setSNo(formatInts(sNo));
					consPermanentAddresses.setReportedDate(formatDates(reportedDate));
					consPermanentAddresses.setAddress(address);
					consPermanentAddresses.setBlockFlag(formatShorts(blockFlag));
					consPermanentAddresses.setIsActive(1);					
					consPermanentAddresses.setCreatedBy(username);	
					consPermanentAddresses.setCreated(created);
					
					consPermanentAddressesList.add(consPermanentAddresses);				
			  	}
			}
			return consPermanentAddressesList;
		}
		
		//================ Setting Reported Names ================//
		public List<ConsReportedNames> insrtReportedNames(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsReportedNames> consReportedNamesList = new ArrayList<ConsReportedNames>();			
			NodeList nodeList = (NodeList) xPath.compile(s_reportedNames).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element reportedNames = (Element) nodeList.item(x);
				if(reportedNames.hasChildNodes()) {			
					String sNo                 = (reportedNames.getElementsByTagName("SNO").getLength() > 0) ? reportedNames.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String name                = (reportedNames.getElementsByTagName("NAME").getLength() > 0) ? reportedNames.getElementsByTagName("NAME").item(0).getTextContent() : "-";
					String reportedInstitution = (reportedNames.getElementsByTagName("INSTITUTION_NAME").getLength() > 0) ? reportedNames.getElementsByTagName("INSTITUTION_NAME").item(0).getTextContent().replace("'", "`") : "-";				
					String reportedDate        = (reportedNames.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? reportedNames.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String blockFlag           = (reportedNames.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? reportedNames.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					
					ConsReportedNames consReportedNames = new ConsReportedNames();
					consReportedNames.setRequestDetailId(requestDetailId);
					consReportedNames.setSNo(formatInts(sNo));
					consReportedNames.setName(name);
					consReportedNames.setReportedInstitution(reportedInstitution);
					consReportedNames.setReportedDate(formatDates(reportedDate));
					consReportedNames.setBlockFlag(formatShorts(blockFlag));
					consReportedNames.setIsActive(1);					
					consReportedNames.setCreatedBy(username);
					consReportedNames.setCreated(created);
					
					consReportedNamesList.add(consReportedNames);
				}
			}
			return consReportedNamesList;
		}
			
		//================ Setting Employment Details ================//
		public List<ConsEmploymentDetails> insrtEmploymentDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsEmploymentDetails> consEmploymentDetailsList = new ArrayList<ConsEmploymentDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_employmentDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element employmentDetails = (Element) nodeList.item(x);
				if(employmentDetails.hasChildNodes()) {	
					String employment   = (employmentDetails.getElementsByTagName("EMPLOYMENT").getLength() > 0) ? employmentDetails.getElementsByTagName("EMPLOYMENT").item(0).getTextContent() : "-";
					String profession   = (employmentDetails.getElementsByTagName("PROFESSION").getLength() > 0) ? employmentDetails.getElementsByTagName("PROFESSION").item(0).getTextContent() : "-";
					String employerName = (employmentDetails.getElementsByTagName("EMPLOYER_NAME").getLength() > 0) ? employmentDetails.getElementsByTagName("EMPLOYER_NAME").item(0).getTextContent() : "-";
					String businessName = (employmentDetails.getElementsByTagName("BUSINESS_ENTITY_NAME").getLength() > 0) ? employmentDetails.getElementsByTagName("BUSINESS_ENTITY_NAME").item(0).getTextContent().replace("'", "`") : "-";
					String brNumber     = (employmentDetails.getElementsByTagName("BUSINESS_REGISTRATION_NUMBER").getLength() > 0) ? employmentDetails.getElementsByTagName("BUSINESS_REGISTRATION_NUMBER").item(0).getTextContent() : "-";
					String reportedDate = (employmentDetails.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? employmentDetails.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;		
					String ruid         = (employmentDetails.getElementsByTagName("RUID").getLength() > 0) ? employmentDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;
					String blockFlag    = (employmentDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? employmentDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					  			
					ConsEmploymentDetails consEmploymentDetails = new ConsEmploymentDetails();
					consEmploymentDetails.setRequestDetailId(requestDetailId);
					consEmploymentDetails.setEmployment(employment);
					consEmploymentDetails.setProfession(profession);
					consEmploymentDetails.setEmployerName(employerName);
					consEmploymentDetails.setBusinessName(businessName);
					consEmploymentDetails.setBrNumber(brNumber);
					consEmploymentDetails.setReportedDate(formatDates(reportedDate));
					consEmploymentDetails.setRuId(formatLongs(ruid));
					consEmploymentDetails.setBlockFlag(formatShorts(blockFlag));
					consEmploymentDetails.setIsActive(1);					
					consEmploymentDetails.setCreatedBy(username);	
					consEmploymentDetails.setCreated(created);
					
					consEmploymentDetailsList.add(consEmploymentDetails);
				}
			}
			return consEmploymentDetailsList;
		}
		
		//================ Setting Relationship Details ================//
		public List<ConsRelationshipDetails> insrtRelationshipDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsRelationshipDetails> consRelationshipDetailsList = new ArrayList<ConsRelationshipDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_relationships).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element relationships = (Element) nodeList.item(x);
				if(relationships.hasChildNodes()) {		
					String sNo                  = (relationships.getElementsByTagName("SNO").getLength() > 0) ? relationships.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String entityId             = (relationships.getElementsByTagName("ID_VALUE").getLength() > 0) ? relationships.getElementsByTagName("ID_VALUE").item(0).getTextContent() : "-";
					String entityName           = (relationships.getElementsByTagName("NAME").getLength() > 0) ? relationships.getElementsByTagName("NAME").item(0).getTextContent() : "-";
					String relationshipType     = (relationships.getElementsByTagName("RELATIONSHIP_TYPE").getLength() > 0) ? relationships.getElementsByTagName("RELATIONSHIP_TYPE").item(0).getTextContent() : "-";
					String natureOfRelationship = (relationships.getElementsByTagName("NATURE").getLength() > 0) ? relationships.getElementsByTagName("NATURE").item(0).getTextContent() : "-";
					String address              = (relationships.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? relationships.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent() : "-";
					String phone                = (relationships.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? relationships.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
					String fax                  = (relationships.getElementsByTagName("FAX_NUMBER").getLength() > 0) ? relationships.getElementsByTagName("FAX_NUMBER").item(0).getTextContent() : "-";
					String city                 = (relationships.getElementsByTagName("CITY").getLength() > 0) ? relationships.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String postalCode           = (relationships.getElementsByTagName("POSTAL_CODE").getLength() > 0) ? relationships.getElementsByTagName("POSTAL_CODE").item(0).getTextContent() : "-";
					String district             = (relationships.getElementsByTagName("DISTRICT").getLength() > 0) ? relationships.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String province             = (relationships.getElementsByTagName("PROVINCE").getLength() > 0) ? relationships.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String relationRuid         = (relationships.getElementsByTagName("RELATION_RUID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_RUID").item(0).getTextContent() : null;
					String relationTypeId       = (relationships.getElementsByTagName("RELATION_TYPE_ID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_TYPE_ID").item(0).getTextContent() : "";
					String relationNatureId     = (relationships.getElementsByTagName("RELATION_NATURE_ID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_NATURE_ID").item(0).getTextContent() : "";
					String blockFlag            = (relationships.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? relationships.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					String ruid                 = (relationships.getElementsByTagName("RUID").getLength() > 0) ? relationships.getElementsByTagName("RUID").item(0).getTextContent() : null;
					 					  						
					ConsRelationshipDetails consRelationshipDetails = new ConsRelationshipDetails();
					consRelationshipDetails.setRequestDetailId(requestDetailId);
					consRelationshipDetails.setSNo(formatInts(sNo));
					consRelationshipDetails.setEntityId(entityId);
					consRelationshipDetails.setEntityName(entityName);
					consRelationshipDetails.setRelationshipType(relationshipType);
					consRelationshipDetails.setNatureOfRelationship(natureOfRelationship);
					consRelationshipDetails.setAddressValue(address);
					consRelationshipDetails.setPhoneNo(phone);
					consRelationshipDetails.setFaxNo(fax);
					consRelationshipDetails.setCity(city);
					consRelationshipDetails.setPostalCode(postalCode);
					consRelationshipDetails.setDistrict(district);
					consRelationshipDetails.setProvince(province);
					consRelationshipDetails.setRelationRuId(formatLongs(relationRuid));
					consRelationshipDetails.setRelationTypeId(relationTypeId);
					consRelationshipDetails.setRelationNatureId(relationNatureId);
					consRelationshipDetails.setRuId(formatLongs(ruid));
					consRelationshipDetails.setBlockFlag(formatShorts(blockFlag));
					consRelationshipDetails.setIsActive(1);					
					consRelationshipDetails.setCreatedBy(username);
					consRelationshipDetails.setCreated(created);
					
					consRelationshipDetailsList.add(consRelationshipDetails);
				}
			}
			return consRelationshipDetailsList;
		}
		
		//================ Setting Relationship Address Details ================//
		public List<ConsRelationshipAddressDetails> insrtRelationshipAddressDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
					    
			List<ConsRelationshipAddressDetails> consRelationshipAddressDetailsList = new ArrayList<ConsRelationshipAddressDetails>();		
			NodeList nodeList = (NodeList) xPath.compile(s_relationshipAddresses).evaluate(doc, XPathConstants.NODESET);
			
			for(int x=0; x<nodeList.getLength(); x++) {
						  			
				Element relationshipAddresses = (Element) nodeList.item(x);
				if(relationshipAddresses.hasChildNodes()) {	
					String sNo          = (relationshipAddresses.getElementsByTagName("SNO").getLength() > 0) ? relationshipAddresses.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String relationRuid = (relationshipAddresses.getElementsByTagName("RELATION_RUID").getLength() > 0) ? relationshipAddresses.getElementsByTagName("RELATION_RUID").item(0).getTextContent() : null;
					String address      = (relationshipAddresses.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? relationshipAddresses.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent() : "-";
					String province     = (relationshipAddresses.getElementsByTagName("PROVINCE").getLength() > 0) ? relationshipAddresses.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String district     = (relationshipAddresses.getElementsByTagName("DISTRICT").getLength() > 0) ? relationshipAddresses.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String city         = (relationshipAddresses.getElementsByTagName("CITY").getLength() > 0) ? relationshipAddresses.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String phone        = (relationshipAddresses.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? relationshipAddresses.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
					String fax          = (relationshipAddresses.getElementsByTagName("FAX_NUMBER").getLength() > 0) ? relationshipAddresses.getElementsByTagName("FAX_NUMBER").item(0).getTextContent() : "-";					
					String postalCode   = (relationshipAddresses.getElementsByTagName("POSTAL_CODE").getLength() > 0) ? relationshipAddresses.getElementsByTagName("POSTAL_CODE").item(0).getTextContent() : "-";
												 					  						
					ConsRelationshipAddressDetails consRelationshipAddressDetails = new ConsRelationshipAddressDetails();
					consRelationshipAddressDetails.setRequestDetailId(requestDetailId);
					consRelationshipAddressDetails.setSNo(formatInts(sNo));
					consRelationshipAddressDetails.setRelationRuId(formatLongs(relationRuid));
					consRelationshipAddressDetails.setAddress(address);
					consRelationshipAddressDetails.setProvince(province);
					consRelationshipAddressDetails.setDistrict(district);
					consRelationshipAddressDetails.setCity(city);
					consRelationshipAddressDetails.setPhoneNumber(phone);
					consRelationshipAddressDetails.setFaxNumber(fax);
					consRelationshipAddressDetails.setPostalCode(postalCode);
					consRelationshipAddressDetails.setIsActive(1);					
					consRelationshipAddressDetails.setCreatedBy(username);
					consRelationshipAddressDetails.setCreated(created);
					
					consRelationshipAddressDetailsList.add(consRelationshipAddressDetails);
				}
			}
			return consRelationshipAddressDetailsList;
		}
		
		//================ Setting Settled Credit Facilities Details ================//
		public List<ConsSettledCreditFacilitiesDetails> insrtSettledCFDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsSettledCreditFacilitiesDetails> consSettledCreditFacilitiesDetailsList = new ArrayList<ConsSettledCreditFacilitiesDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_settledCFDetils).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element settledCFDetails = (Element) nodeList.item(x);
				if(settledCFDetails.hasChildNodes()) {	
					String currency              = (settledCFDetails.getElementsByTagName("Currency").getLength() > 0) ? settledCFDetails.getElementsByTagName("Currency").item(0).getTextContent() : "-";
					String cfType                = (settledCFDetails.getElementsByTagName("CF_Type").getLength() > 0) ? settledCFDetails.getElementsByTagName("CF_Type").item(0).getTextContent() : "-";
					String noOfCfasBorrower      = (settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_BRW").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_BRW").item(0).getTextContent() : null;
					String amtGrantedasBorrower  = (settledCFDetails.getElementsByTagName("Total_Amt_Granted_BRW").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_Amt_Granted_BRW").item(0).getTextContent() : null;
					String noOfCfasGuarantor     = (settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_GRT").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_GRT").item(0).getTextContent() : null;
					String amtGrantedasGuarantor = (settledCFDetails.getElementsByTagName("Total_Amt_Granted_GRT").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_Amt_Granted_GRT").item(0).getTextContent() : null;
					  						
					ConsSettledCreditFacilitiesDetails consSettledCreditFacilitiesDetails = new ConsSettledCreditFacilitiesDetails();
					consSettledCreditFacilitiesDetails.setRequestDetailId(requestDetailId);
					consSettledCreditFacilitiesDetails.setCurrency(currency);
					consSettledCreditFacilitiesDetails.setCfType(cfType);
					consSettledCreditFacilitiesDetails.setNoOfCreditFacilitiesAsBorrower(formatInts(noOfCfasBorrower));
					consSettledCreditFacilitiesDetails.setAmountGrantedAsBorrower(formatAmounts(amtGrantedasBorrower));
					consSettledCreditFacilitiesDetails.setNoOfCreditFacilitiesAsGuarantor(formatInts(noOfCfasGuarantor));
					consSettledCreditFacilitiesDetails.setAmountGrantedAsGuarantor(formatAmounts(amtGrantedasGuarantor));
					consSettledCreditFacilitiesDetails.setIsActive(1);					
					consSettledCreditFacilitiesDetails.setCreatedBy(username);
					consSettledCreditFacilitiesDetails.setCreated(created);
					
					consSettledCreditFacilitiesDetailsList.add(consSettledCreditFacilitiesDetails);
				}
			}
			return consSettledCreditFacilitiesDetailsList;
		}

		//================ Setting Settled Credit Facilities Summary ================//
		public List<ConsSettledCreditFacilitiesSummary> insrtSettledCFSummary(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsSettledCreditFacilitiesSummary> consSettledCreditFacilitiesSummaryList = new ArrayList<ConsSettledCreditFacilitiesSummary>();
			NodeList nodeList = (NodeList) xPath.compile(s_settledCFSummary).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element settledCFSummary = (Element) nodeList.item(x);
				if(settledCFSummary.hasChildNodes()) {	
					String currency     = (settledCFSummary.getElementsByTagName("Currency").getLength() > 0) ? settledCFSummary.getElementsByTagName("Currency").item(0).getTextContent() : "-";
					String ownership    = (settledCFSummary.getElementsByTagName("Ownership").getLength() > 0) ? settledCFSummary.getElementsByTagName("Ownership").item(0).getTextContent() : "-";
					String noOfCF_1     = (settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr1").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr1").item(0).getTextContent() : null;
					String amtGranted_1 = (settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr1").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr1").item(0).getTextContent() : null;
					String noOfCF_2     = (settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr2").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr2").item(0).getTextContent() : null;
					String amtGranted_2 = (settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr2").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr2").item(0).getTextContent() : null;
					String noOfCF_3     = (settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr3").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr3").item(0).getTextContent() : null;
					String amtGranted_3 = (settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr3").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr3").item(0).getTextContent() : null;
					String noOfCF_4     = (settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr4").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr4").item(0).getTextContent() : null;
					String amtGranted_4 = (settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr4").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr4").item(0).getTextContent() : null;
					String noOfCF_5     = (settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr5").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_no_of_credit_facilities_yr5").item(0).getTextContent() : null;
					String amtGranted_5 = (settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr5").getLength() > 0) ? settledCFSummary.getElementsByTagName("Total_Amt_Granted_yr5").item(0).getTextContent() : null;
					String rp           = (settledCFSummary.getElementsByTagName("RP").getLength() > 0) ? settledCFSummary.getElementsByTagName("RP").item(0).getTextContent() : "-";
					String year1        = (settledCFSummary.getElementsByTagName("STYR1").getLength() > 0) ? settledCFSummary.getElementsByTagName("STYR1").item(0).getTextContent() : "-";
					String year2        = (settledCFSummary.getElementsByTagName("STYR2").getLength() > 0) ? settledCFSummary.getElementsByTagName("STYR2").item(0).getTextContent() : "-";
					String year3        = (settledCFSummary.getElementsByTagName("STYR3").getLength() > 0) ? settledCFSummary.getElementsByTagName("STYR3").item(0).getTextContent() : "-";
					String year4        = (settledCFSummary.getElementsByTagName("STYR4").getLength() > 0) ? settledCFSummary.getElementsByTagName("STYR4").item(0).getTextContent() : "-";
					String year5        = (settledCFSummary.getElementsByTagName("STYR5").getLength() > 0) ? settledCFSummary.getElementsByTagName("STYR5").item(0).getTextContent() : "-";
					String isColorNeed  = (settledCFSummary.getElementsByTagName("IsColoringNeeded").getLength() > 0) ? settledCFSummary.getElementsByTagName("IsColoringNeeded").item(0).getTextContent() : "-";
					
					ConsSettledCreditFacilitiesSummary consSettledCreditFacilitiesSummary = new ConsSettledCreditFacilitiesSummary();
					consSettledCreditFacilitiesSummary.setRequestDetailId(requestDetailId);
					consSettledCreditFacilitiesSummary.setCurrency(currency);
					consSettledCreditFacilitiesSummary.setOwnership(ownership);
					consSettledCreditFacilitiesSummary.setNoOfCreditFacilities1(formatInts(noOfCF_1));
					consSettledCreditFacilitiesSummary.setAmountGranted1(formatAmounts(amtGranted_1));
					consSettledCreditFacilitiesSummary.setNoOfCreditFacilities2(formatInts(noOfCF_2));
					consSettledCreditFacilitiesSummary.setAmountGranted2(formatAmounts(amtGranted_2));
					consSettledCreditFacilitiesSummary.setNoOfCreditFacilities3(formatInts(noOfCF_3));
					consSettledCreditFacilitiesSummary.setAmountGranted3(formatAmounts(amtGranted_3));
					consSettledCreditFacilitiesSummary.setNoOfCreditFacilities4(formatInts(noOfCF_4));
					consSettledCreditFacilitiesSummary.setAmountGranted4(formatAmounts(amtGranted_4));
					consSettledCreditFacilitiesSummary.setNoOfCreditFacilities5(formatInts(noOfCF_5));
					consSettledCreditFacilitiesSummary.setAmountGranted5(formatAmounts(amtGranted_5));
					consSettledCreditFacilitiesSummary.setRp(rp);
					consSettledCreditFacilitiesSummary.setYear1(year1);
					consSettledCreditFacilitiesSummary.setYear2(year2);
					consSettledCreditFacilitiesSummary.setYear3(year3);
					consSettledCreditFacilitiesSummary.setYear4(year4);
					consSettledCreditFacilitiesSummary.setYear5(year5);
					consSettledCreditFacilitiesSummary.setIsColoringNeeded(isColorNeed);
					consSettledCreditFacilitiesSummary.setIsActive(1);					
					consSettledCreditFacilitiesSummary.setCreatedBy(username);
					consSettledCreditFacilitiesSummary.setCreated(created);
					
					consSettledCreditFacilitiesSummaryList.add(consSettledCreditFacilitiesSummary);
				}
			}
			return consSettledCreditFacilitiesSummaryList;
		}
		
		//================ Setting Lending Institutions Inquiries ================//
		public List<ConsLendingInstutionsInquiries> insrtLendingInstInquiries(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsLendingInstutionsInquiries> consLendingInstutionsInquiriesList = new ArrayList<ConsLendingInstutionsInquiries>();
			NodeList nodeList = (NodeList) xPath.compile(s_lendingInstInquiries).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element lendingInstInquiries = (Element) nodeList.item(x);
				if(lendingInstInquiries.hasChildNodes()) {	
					String sNo         = (lendingInstInquiries.getElementsByTagName("SLNO").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("SLNO").item(0).getTextContent() : null;
					String instCategory= (lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").item(0).getTextContent() : "-";
					String inquiryDate = (lendingInstInquiries.getElementsByTagName("INQUIRY_DATE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INQUIRY_DATE").item(0).getTextContent() : null;
					String reasonId    = (lendingInstInquiries.getElementsByTagName("REASON_ID").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("REASON_ID").item(0).getTextContent() : null;
					String reason      = (lendingInstInquiries.getElementsByTagName("REASON").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("REASON").item(0).getTextContent() : "-";
					String instName    = (lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").item(0).getTextContent() : "-";
					String product     = (lendingInstInquiries.getElementsByTagName("PRODUCT_NAME").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("PRODUCT_NAME").item(0).getTextContent() : "-";
					String currency    = (lendingInstInquiries.getElementsByTagName("CURRENCY").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("CURRENCY").item(0).getTextContent() : "-";
					String amount      = (lendingInstInquiries.getElementsByTagName("AMOUNT").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("AMOUNT").item(0).getTextContent() : null;
					String cfType      = (lendingInstInquiries.getElementsByTagName("CREDIT_FACILITY_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("CREDIT_FACILITY_TYPE").item(0).getTextContent() : "-";
					String ruid        = (lendingInstInquiries.getElementsByTagName("RUID").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("RUID").item(0).getTextContent() : null;
					
					ConsLendingInstutionsInquiries consLendingInstutionsInquiries = new ConsLendingInstutionsInquiries();
					consLendingInstutionsInquiries.setRequestDetailId(requestDetailId);
					consLendingInstutionsInquiries.setSNo(formatInts(sNo));
					consLendingInstutionsInquiries.setInstituionCategory(instCategory);
					consLendingInstutionsInquiries.setInquiryDate(formatDates(inquiryDate));
					consLendingInstutionsInquiries.setReasonId(formatInts(reasonId));
					consLendingInstutionsInquiries.setReason(reason);
					consLendingInstutionsInquiries.setInstitutionName(instName);
					consLendingInstutionsInquiries.setProductName(product);
					consLendingInstutionsInquiries.setCurrency(currency);
					consLendingInstutionsInquiries.setAmount(formatAmounts(amount));
					consLendingInstutionsInquiries.setCfType(cfType);
					consLendingInstutionsInquiries.setRuId(formatLongs(ruid));
					consLendingInstutionsInquiries.setIsActive(1);					
					consLendingInstutionsInquiries.setCreatedBy(username);
					consLendingInstutionsInquiries.setCreated(created);
					
					consLendingInstutionsInquiriesList.add(consLendingInstutionsInquiries);
				}
			}
			return consLendingInstutionsInquiriesList;
		}
		
		//================ Setting Inquiries By Subject ================//
		public List<ConsInquiriesBySubject> insrtInquiriesBySubject(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsInquiriesBySubject> consInquiriesBySubjectList = new ArrayList<ConsInquiriesBySubject>();
			NodeList nodeList = (NodeList) xPath.compile(s_inqBySubject).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element inqBySubject = (Element) nodeList.item(x);
				if(inqBySubject.hasChildNodes()) {	
					String sNo         = (inqBySubject.getElementsByTagName("SLNO").getLength() > 0) ? inqBySubject.getElementsByTagName("SLNO").item(0).getTextContent() : null;
					String inquiryDate = (inqBySubject.getElementsByTagName("INQUIRY_DATE").getLength() > 0) ? inqBySubject.getElementsByTagName("INQUIRY_DATE").item(0).getTextContent() : null;
					String reason      = (inqBySubject.getElementsByTagName("REASON").getLength() > 0) ? inqBySubject.getElementsByTagName("REASON").item(0).getTextContent() : "-";
					String reasonId    = (inqBySubject.getElementsByTagName("REASON_ID").getLength() > 0) ? inqBySubject.getElementsByTagName("REASON_ID").item(0).getTextContent() : null;

					ConsInquiriesBySubject consInquiriesBySubject = new ConsInquiriesBySubject();
					consInquiriesBySubject.setRequestDetailId(requestDetailId);
					consInquiriesBySubject.setSNo(formatInts(sNo));
					consInquiriesBySubject.setInquiryDate(formatDates(inquiryDate));
					consInquiriesBySubject.setReason(reason);
					consInquiriesBySubject.setReasonId(formatInts(reasonId));
					consInquiriesBySubject.setIsActive(1);					
					consInquiriesBySubject.setCreatedBy(username);
					consInquiriesBySubject.setCreated(created);
					
					consInquiriesBySubjectList.add(consInquiriesBySubject);
				}
			}
			return consInquiriesBySubjectList;
		}
		
		//================ Setting Credit Facility ================//
		public List<ConsCreditFacility> insrtCreditFacility(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
					    
			List<ConsCreditFacility> consCreditFacilityList = new ArrayList<ConsCreditFacility>();	
			NodeList nodeList = (NodeList) xPath.compile(s_creditFacility).evaluate(doc, XPathConstants.NODESET);
			
			for(int x=0; x<nodeList.getLength(); x++) {
						  			
				Element creditFacility = (Element) nodeList.item(x);
				if(creditFacility.hasChildNodes()) {	
					String relationId   = (creditFacility.getElementsByTagName("relation_id").getLength() > 0) ? creditFacility.getElementsByTagName("relation_id").item(0).getTextContent() : null;
					String serialNumber = (creditFacility.getElementsByTagName("SerialNumber").getLength() > 0) ? creditFacility.getElementsByTagName("SerialNumber").item(0).getTextContent() : null;					

					ConsCreditFacility consCreditFacility = new ConsCreditFacility();
					consCreditFacility.setRequestDetailId(requestDetailId);
					consCreditFacility.setRelationId(formatInts(relationId));
					consCreditFacility.setSerialNumber(formatInts(serialNumber));
					consCreditFacility.setIsActive(1);				
					consCreditFacility.setCreatedBy(username);
					consCreditFacility.setCreated(created);
					
					consCreditFacilityList.add(consCreditFacility);
				}
			}
			return consCreditFacilityList;
		}
		
		//================ Setting Credit Facility Details ================//		
		public List<ConsCreditFacilityDetails> insrtCreditFacilityDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			
			List<ConsCreditFacilityDetails> consCreditFacilityDetailsList = new ArrayList<ConsCreditFacilityDetails>();
		    NodeList nodeList = (NodeList) xPath.compile(s_cFDetails).evaluate(doc, XPathConstants.NODESET);
		    	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element cFDetails = (Element) nodeList.item(x);
				if(cFDetails.hasChildNodes()) {	
					String sNo               = (cFDetails.getElementsByTagName("SNO").getLength() > 0) ? cFDetails.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String institutionCatg   = (cFDetails.getElementsByTagName("INS_CATEGORY").getLength() > 0) ? cFDetails.getElementsByTagName("INS_CATEGORY").item(0).getTextContent() : "-";
					String institutionBranch = (cFDetails.getElementsByTagName("BRANCH_NAME").getLength() > 0) ? cFDetails.getElementsByTagName("BRANCH_NAME").item(0).getTextContent() : "-";
					String cfType            = (cFDetails.getElementsByTagName("CREDIT_FACILITY_TYPE").getLength() > 0) ? cFDetails.getElementsByTagName("CREDIT_FACILITY_TYPE").item(0).getTextContent() : "-";
					String cfStatus          = (cFDetails.getElementsByTagName("CREDIT_FACILITY_STATUS").getLength() > 0) ? cFDetails.getElementsByTagName("CREDIT_FACILITY_STATUS").item(0).getTextContent() : "-";
					String ownership         = (cFDetails.getElementsByTagName("OWNER_SHIP_INDICATOR").getLength() > 0) ? cFDetails.getElementsByTagName("OWNER_SHIP_INDICATOR").item(0).getTextContent() : "-";
					String currency          = (cFDetails.getElementsByTagName("CURRENCY").getLength() > 0) ? cFDetails.getElementsByTagName("CURRENCY").item(0).getTextContent() : "-";
					String amtGranted        = (cFDetails.getElementsByTagName("SANCTIONED_AMOUNT").getLength() > 0) ? cFDetails.getElementsByTagName("SANCTIONED_AMOUNT").item(0).getTextContent() : null;
					String currentBalance    = (cFDetails.getElementsByTagName("CURRENT_BALANCE").getLength() > 0) ? cFDetails.getElementsByTagName("CURRENT_BALANCE").item(0).getTextContent() : null;
					String arrearsAmt        = (cFDetails.getElementsByTagName("OVERDUE_AMOUNT").getLength() > 0) ? cFDetails.getElementsByTagName("OVERDUE_AMOUNT").item(0).getTextContent() : null;
					String installmentAmt    = (cFDetails.getElementsByTagName("INSTALLMENT_AMOUNT").getLength() > 0) ? cFDetails.getElementsByTagName("INSTALLMENT_AMOUNT").item(0).getTextContent() : null;
					String amtWrittenOff     = (cFDetails.getElementsByTagName("WRITE_OFF_AMOUNT").getLength() > 0) ? cFDetails.getElementsByTagName("WRITE_OFF_AMOUNT").item(0).getTextContent() : null;
					String reportedDate      = (cFDetails.getElementsByTagName("REPORTED_DATE").getLength() > 0) ? cFDetails.getElementsByTagName("REPORTED_DATE").item(0).getTextContent() : null;
					String firstDisburseDate = (cFDetails.getElementsByTagName("FIRST_DISBURSE_DATE").getLength() > 0) ? cFDetails.getElementsByTagName("FIRST_DISBURSE_DATE").item(0).getTextContent() : null;
					String lastPaymentDate   = (cFDetails.getElementsByTagName("DATE_LATEST_PAY_RECEIVED").getLength() > 0) ? cFDetails.getElementsByTagName("DATE_LATEST_PAY_RECEIVED").item(0).getTextContent() : null;
					String restructuringDate = (cFDetails.getElementsByTagName("RESTRUCTURING_DATE").getLength() > 0) ? cFDetails.getElementsByTagName("RESTRUCTURING_DATE").item(0).getTextContent() : null;
					String endDate           = (cFDetails.getElementsByTagName("DATE_ACC_CLOSE").getLength() > 0) ? cFDetails.getElementsByTagName("DATE_ACC_CLOSE").item(0).getTextContent() : null;
					String repayType         = (cFDetails.getElementsByTagName("REPAY_TYPE").getLength() > 0) ? cFDetails.getElementsByTagName("REPAY_TYPE").item(0).getTextContent() : "-";
					String purpose           = (cFDetails.getElementsByTagName("BUREAU_CREDIT_FAC_PURPOSE").getLength() > 0) ? cFDetails.getElementsByTagName("BUREAU_CREDIT_FAC_PURPOSE").item(0).getTextContent() : "-";
					String coverage          = (cFDetails.getElementsByTagName("COVERAGE").getLength() > 0) ? cFDetails.getElementsByTagName("COVERAGE").item(0).getTextContent() : "-";
					
					String relationId         = (cFDetails.getElementsByTagName("RELATION_ID").getLength() > 0) ? cFDetails.getElementsByTagName("RELATION_ID").item(0).getTextContent() : null;
					String accountStatus      = (cFDetails.getElementsByTagName("ACCOUNT_STATUS").getLength() > 0) ? cFDetails.getElementsByTagName("ACCOUNT_STATUS").item(0).getTextContent() : "-";
					String dispute            = (cFDetails.getElementsByTagName("DISPUTE").getLength() > 0) ? cFDetails.getElementsByTagName("DISPUTE").item(0).getTextContent() : "-";
					String guaranteeCoverage  = (cFDetails.getElementsByTagName("BUREAU_GUARANTEE_COVERAGE").getLength() > 0) ? cFDetails.getElementsByTagName("BUREAU_GUARANTEE_COVERAGE").item(0).getTextContent() : "-";
					String securityCoverage   = (cFDetails.getElementsByTagName("BUREAU_SECURITY_COVERAGE").getLength() > 0) ? cFDetails.getElementsByTagName("BUREAU_SECURITY_COVERAGE").item(0).getTextContent() : "-";
					String interestOutstnding = (cFDetails.getElementsByTagName("INTEREST_OUTSTANDING").getLength() > 0) ? cFDetails.getElementsByTagName("INTEREST_OUTSTANDING").item(0).getTextContent() : null;
					String numDaysDue         = (cFDetails.getElementsByTagName("MAX_NUM_DAYS_DUE").getLength() > 0) ? cFDetails.getElementsByTagName("MAX_NUM_DAYS_DUE").item(0).getTextContent() : null;
					String loanType           = (cFDetails.getElementsByTagName("LOAN_TYPE").getLength() > 0) ? cFDetails.getElementsByTagName("LOAN_TYPE").item(0).getTextContent() : "-";
					String sanctionDate       = (cFDetails.getElementsByTagName("SANCTION_DATE").getLength() > 0) ? cFDetails.getElementsByTagName("SANCTION_DATE").item(0).getTextContent() : null;
					String ownershipIndicator = (cFDetails.getElementsByTagName("OWNERSHIP_INDICATOR").getLength() > 0) ? cFDetails.getElementsByTagName("OWNERSHIP_INDICATOR").item(0).getTextContent() : "-";
					String repaymentType      = (cFDetails.getElementsByTagName("REPAYMENT_TYPE").getLength() > 0) ? cFDetails.getElementsByTagName("REPAYMENT_TYPE").item(0).getTextContent() : "-";
					String priority           = (cFDetails.getElementsByTagName("PRIORITY").getLength() > 0) ? cFDetails.getElementsByTagName("PRIORITY").item(0).getTextContent() : null;
					String priority2          = (cFDetails.getElementsByTagName("PRIORITY2").getLength() > 0) ? cFDetails.getElementsByTagName("PRIORITY2").item(0).getTextContent() : null;
					String legalAction        = (cFDetails.getElementsByTagName("LEGAL_ACTION").getLength() > 0) ? cFDetails.getElementsByTagName("LEGAL_ACTION").item(0).getTextContent() : "-";
					String numOfInstallments  = (cFDetails.getElementsByTagName("NUMBER_OF_INSTALLMENTS").getLength() > 0) ? cFDetails.getElementsByTagName("NUMBER_OF_INSTALLMENTS").item(0).getTextContent() : null;
					String primaryRoot        = (cFDetails.getElementsByTagName("PRIMARY_ROOT_ID").getLength() > 0) ? cFDetails.getElementsByTagName("PRIMARY_ROOT_ID").item(0).getTextContent() : null;
					String activeRoot         = (cFDetails.getElementsByTagName("ACTIVE_ROOT_ID").getLength() > 0) ? cFDetails.getElementsByTagName("ACTIVE_ROOT_ID").item(0).getTextContent() : null;
					String ruId               = (cFDetails.getElementsByTagName("RUID").getLength() > 0) ? cFDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;
					String providerBranch     = (cFDetails.getElementsByTagName("PROVIDER_BRANCH").getLength() > 0) ? cFDetails.getElementsByTagName("PROVIDER_BRANCH").item(0).getTextContent() : "-";
					String providerSource     = (cFDetails.getElementsByTagName("PROVIDER_SOURCE").getLength() > 0) ? cFDetails.getElementsByTagName("PROVIDER_SOURCE").item(0).getTextContent() : "-";
					String categoryDesc       = (cFDetails.getElementsByTagName("CATEGORY_DESC").getLength() > 0) ? cFDetails.getElementsByTagName("CATEGORY_DESC").item(0).getTextContent() : "-";
					String siInstName         = (cFDetails.getElementsByTagName("SI_INST_NAME").getLength() > 0) ? cFDetails.getElementsByTagName("SI_INST_NAME").item(0).getTextContent() : "-";
					String siBranchName       = (cFDetails.getElementsByTagName("SI_BRNH_NAME").getLength() > 0) ? cFDetails.getElementsByTagName("SI_BRNH_NAME").item(0).getTextContent() : "-";
					String disputeId          = (cFDetails.getElementsByTagName("DISPUTE_ID").getLength() > 0) ? cFDetails.getElementsByTagName("DISPUTE_ID").item(0).getTextContent() : "-";
					String rank               = (cFDetails.getElementsByTagName("RANK").getLength() > 0) ? cFDetails.getElementsByTagName("RANK").item(0).getTextContent() : null;
					String rowNum             = (cFDetails.getElementsByTagName("ROWNUM").getLength() > 0) ? cFDetails.getElementsByTagName("ROWNUM").item(0).getTextContent() : null;
					String securType          = (cFDetails.getElementsByTagName("SECUR_TYPE").getLength() > 0) ? cFDetails.getElementsByTagName("SECUR_TYPE").item(0).getTextContent() : "-";
					String blockFlag          = (cFDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? cFDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					  						
					ConsCreditFacilityDetails consCreditFacilityDetails = new ConsCreditFacilityDetails();
					consCreditFacilityDetails.setRequestDetailId(requestDetailId);
					consCreditFacilityDetails.setSNo(formatInts(sNo));
					consCreditFacilityDetails.setInstitutionCategory(institutionCatg);
					consCreditFacilityDetails.setInstitutionBranch(institutionBranch);
					consCreditFacilityDetails.setCfType(cfType);
					consCreditFacilityDetails.setCfStatus(cfStatus);
					consCreditFacilityDetails.setOwnership(ownership);
					consCreditFacilityDetails.setCurrency(currency);
					consCreditFacilityDetails.setAmountGranted(formatAmounts(amtGranted));
					consCreditFacilityDetails.setCurrentBalance(formatAmounts(currentBalance));
					consCreditFacilityDetails.setArrearsAmount(formatAmounts(arrearsAmt));
					consCreditFacilityDetails.setInstallmentAmount(formatAmounts(installmentAmt));
					consCreditFacilityDetails.setAmountWrittenOff(formatAmounts(amtWrittenOff));
					consCreditFacilityDetails.setReportedDate(formatDates(reportedDate));
					consCreditFacilityDetails.setFirstDisburseDate(formatDates(firstDisburseDate));
					consCreditFacilityDetails.setLatestPaymentDate(formatDates(lastPaymentDate));
					consCreditFacilityDetails.setRestructuringDate(formatDates(restructuringDate));
					consCreditFacilityDetails.setEndDate(formatDates(endDate));
					consCreditFacilityDetails.setRepayType(repayType);
					consCreditFacilityDetails.setPurpose(purpose);
					consCreditFacilityDetails.setCoverage(coverage);				
					consCreditFacilityDetails.setRelationId(formatInts(relationId));
					consCreditFacilityDetails.setAccountStatus(accountStatus);
					consCreditFacilityDetails.setDispute(dispute);
					consCreditFacilityDetails.setBureauGuaranteeCoverage(guaranteeCoverage);
					consCreditFacilityDetails.setBureauSecurityCoverage(securityCoverage);
					consCreditFacilityDetails.setInterestOutstanding(formatAmounts(interestOutstnding));
					consCreditFacilityDetails.setMaxNumDaysDue(formatInts(numDaysDue));
					consCreditFacilityDetails.setLoanType(loanType);
					consCreditFacilityDetails.setSanctionDate(formatDates(sanctionDate));
					consCreditFacilityDetails.setOwnershipIndicator(ownershipIndicator);
					consCreditFacilityDetails.setRepaymentType(repaymentType);
					consCreditFacilityDetails.setPriority(formatInts(priority));
					consCreditFacilityDetails.setPriority2(formatInts(priority2));
					consCreditFacilityDetails.setLegalAction(legalAction);
					consCreditFacilityDetails.setNumberOfInstallments(formatInts(numOfInstallments));
					consCreditFacilityDetails.setPrimaryRootId(formatLongs(primaryRoot));
					consCreditFacilityDetails.setActiveRootId(formatLongs(activeRoot));
					consCreditFacilityDetails.setRuId(formatLongs(ruId));
					consCreditFacilityDetails.setProviderBranch(providerBranch);
					consCreditFacilityDetails.setProviderSource(providerSource);
					consCreditFacilityDetails.setCategoryDesc(categoryDesc);
					consCreditFacilityDetails.setSiInstName(siInstName);
					consCreditFacilityDetails.setSiBrnhName(siBranchName);
					consCreditFacilityDetails.setDisputeId(disputeId);
					consCreditFacilityDetails.setRank(formatInts(rank));
					consCreditFacilityDetails.setRowNum(formatInts(rowNum));
					consCreditFacilityDetails.setSecurType(securType);
					consCreditFacilityDetails.setBlockFlag(formatShorts(blockFlag));
					consCreditFacilityDetails.setIsActive(1);				
					consCreditFacilityDetails.setCreatedBy(username);
					consCreditFacilityDetails.setCreated(created);
					
					consCreditFacilityDetailsList.add(consCreditFacilityDetails);
				}
			}
			return consCreditFacilityDetailsList;
		}
		
		//================ Last 24 Months ================//
		public List<ConsLast24Months> insrtLast24Months(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			  
			List<ConsLast24Months> consLast24MonthsList = new ArrayList<ConsLast24Months>();
			NodeList nodeList = (NodeList) xPath.compile(s_last24Months).evaluate(doc, XPathConstants.NODESET);

			for(int x=0; x<nodeList.getLength(); x++) {
				
				Element last24Months = (Element) nodeList.item(x);
				if(last24Months.hasChildNodes()) {	
					String relationId      = (last24Months.getElementsByTagName("RELATION_ID").getLength() > 0) ? last24Months.getElementsByTagName("RELATION_ID").item(0).getTextContent() : null;
					String sNo             = (last24Months.getElementsByTagName("SNO").getLength() > 0) ? last24Months.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String rowNum          = (last24Months.getElementsByTagName("ROWNUM").getLength() > 0) ? last24Months.getElementsByTagName("ROWNUM").item(0).getTextContent() : null;
					String activeRootId    = (last24Months.getElementsByTagName("ACTIVE_ROOT_ID").getLength() > 0) ? last24Months.getElementsByTagName("ACTIVE_ROOT_ID").item(0).getTextContent() : null;
					String toMonthYear     = (last24Months.getElementsByTagName("TO_MONTH_YEAR").getLength() > 0) ? last24Months.getElementsByTagName("TO_MONTH_YEAR").item(0).getTextContent() : "-";
					String fromMonthYear   = (last24Months.getElementsByTagName("FROM_MONTH_YEAR").getLength() > 0) ? last24Months.getElementsByTagName("FROM_MONTH_YEAR").item(0).getTextContent() : "-";
					String bureauCurrency  = (last24Months.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? last24Months.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
					String bureauAccStatus = (last24Months.getElementsByTagName("BUREAU_ACC_STATUS").getLength() > 0) ? last24Months.getElementsByTagName("BUREAU_ACC_STATUS").item(0).getTextContent() : "-";
					String rank            = (last24Months.getElementsByTagName("RANK").getLength() > 0) ? last24Months.getElementsByTagName("RANK").item(0).getTextContent() : null;
															    
				    ConsLast24Months consLast24Months = new ConsLast24Months();
				    consLast24Months.setRequestDetailId(requestDetailId);
				    consLast24Months.setRelationId(formatInts(relationId));
				    consLast24Months.setSNo(formatInts(sNo));
				    consLast24Months.setRowNum(formatInts(rowNum));
				    consLast24Months.setActiveRootId(formatLongs(activeRootId));
				    consLast24Months.setToMonthYear(toMonthYear);
				    consLast24Months.setFromMonthYear(fromMonthYear);
				    consLast24Months.setBureauCurrency(bureauCurrency);
				    consLast24Months.setBureauAccStatus(bureauAccStatus);
				    consLast24Months.setRank(formatInts(rank));
				    consLast24Months.setIsActive(1);				    
				    consLast24Months.setCreatedBy(username);
				    consLast24Months.setCreated(created);
				    
				    consLast24MonthsList.add(consLast24Months);
				}			
			}
			return consLast24MonthsList;
		}
		
		//================ Setting Credit Facility For Last 24 Months ================//
		public List<ConsCreditFacilityDetailsLast24Months> insrtCFForLast24Months(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    			
			List<ConsCreditFacilityDetailsLast24Months> consCreditFacilityDetailsLast24MonthsList = new ArrayList<ConsCreditFacilityDetailsLast24Months>();
		    NodeList nodeList = (NodeList) xPath.compile(s_cfFor24Months).evaluate(doc, XPathConstants.NODESET);
		    	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element cfFor24Months = (Element) nodeList.item(x);
				if(cfFor24Months.hasChildNodes()) {	
					String activeRootId        = (cfFor24Months.getElementsByTagName("ACTIVE_ROOT_ID").getLength() > 0) ? cfFor24Months.getElementsByTagName("ACTIVE_ROOT_ID").item(0).getTextContent() : null;
					String toMonthYear         = (cfFor24Months.getElementsByTagName("to_month_year").getLength() > 0) ? cfFor24Months.getElementsByTagName("to_month_year").item(0).getTextContent() : "-";
					String fromMonthYear       = (cfFor24Months.getElementsByTagName("from_month_year").getLength() > 0) ? cfFor24Months.getElementsByTagName("from_month_year").item(0).getTextContent() : "-";
					String month               = (cfFor24Months.getElementsByTagName("MONTH").getLength() > 0) ? cfFor24Months.getElementsByTagName("MONTH").item(0).getTextContent() : "-";
					String currentBalance      = (cfFor24Months.getElementsByTagName("CURRENT_BALANCE").getLength() > 0) ? cfFor24Months.getElementsByTagName("CURRENT_BALANCE").item(0).getTextContent() : null;
					String amountOverdue       = (cfFor24Months.getElementsByTagName("AMOUNT_OVERDUE").getLength() > 0) ? cfFor24Months.getElementsByTagName("AMOUNT_OVERDUE").item(0).getTextContent() : null;
					String assetClassification = (cfFor24Months.getElementsByTagName("ASSET_CLASSIFICATION").getLength() > 0) ? cfFor24Months.getElementsByTagName("ASSET_CLASSIFICATION").item(0).getTextContent() : "-";
					String maxDaysOverdue      = (cfFor24Months.getElementsByTagName("MAXIMUM_NUMBER_OF_DAYS_OVERDUE").getLength() > 0) ? cfFor24Months.getElementsByTagName("MAXIMUM_NUMBER_OF_DAYS_OVERDUE").item(0).getTextContent() : "";
											
					ConsCreditFacilityDetailsLast24Months consCreditFacilityDetailsLast24Months = new ConsCreditFacilityDetailsLast24Months();
					consCreditFacilityDetailsLast24Months.setRequestDetailId(requestDetailId);
					consCreditFacilityDetailsLast24Months.setActiveRootId(formatLongs(activeRootId));
					consCreditFacilityDetailsLast24Months.setToMonthYear(toMonthYear);
					consCreditFacilityDetailsLast24Months.setFromMonthYear(fromMonthYear);
					consCreditFacilityDetailsLast24Months.setMonth(month);
					consCreditFacilityDetailsLast24Months.setCurrentBalance(formatAmounts(currentBalance));
					consCreditFacilityDetailsLast24Months.setAmountOverdue(formatAmounts(amountOverdue));
					consCreditFacilityDetailsLast24Months.setAssetClassification(assetClassification);
					consCreditFacilityDetailsLast24Months.setMaximumNumberOfDaysOverdue(maxDaysOverdue);
					consCreditFacilityDetailsLast24Months.setIsActive(1);					
					consCreditFacilityDetailsLast24Months.setCreatedBy(username);
					consCreditFacilityDetailsLast24Months.setCreated(created);
					
					consCreditFacilityDetailsLast24MonthsList.add(consCreditFacilityDetailsLast24Months);
				}
			}
			return consCreditFacilityDetailsLast24MonthsList;
		}
		
		//================ Setting Dispute Details ================//
		public List<ConsDisputeDetails> insrtDisputeDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsDisputeDetails> consDisputeDetailsList = new ArrayList<ConsDisputeDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_disputeDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element disputeDetails = (Element) nodeList.item(x);
				if(disputeDetails.hasChildNodes()) {	
					String description = (disputeDetails.getElementsByTagName("DISPUTE_DETAILS").getLength() > 0) ? disputeDetails.getElementsByTagName("DISPUTE_DETAILS").item(0).getTextContent() : "-";
					  						
					ConsDisputeDetails consDisputeDetails = new ConsDisputeDetails();
					consDisputeDetails.setRequestDetailId(requestDetailId);
					consDisputeDetails.setDescription(description);
					consDisputeDetails.setIsActive(1);					
					consDisputeDetails.setCreatedBy(username);
					consDisputeDetails.setCreated(created);
					
					consDisputeDetailsList.add(consDisputeDetails);
				}
			}
			return consDisputeDetailsList;
		}
		
		//================ Setting Potential And Current Liabilities Header ================//
		public List<ConsPotentialAndCurrentLiabilitiesHeader> insrtPotAndCurrLiabilitiesHeader(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
							    
			List<ConsPotentialAndCurrentLiabilitiesHeader> consPotentialAndCurrentLiabilitiesHeaderList = new ArrayList<ConsPotentialAndCurrentLiabilitiesHeader>();				
			NodeList nodeList = (NodeList) xPath.compile(s_potAndCurrLiabilitiesHeader).evaluate(doc, XPathConstants.NODESET);
					
			for(int x=0; x<nodeList.getLength(); x++) {
								  			
				Element potAndCurrLiabilitiesHeader = (Element) nodeList.item(x);
				if(potAndCurrLiabilitiesHeader.hasChildNodes()) {	
					String currency  = (potAndCurrLiabilitiesHeader.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
					String priority  = (potAndCurrLiabilitiesHeader.getElementsByTagName("PRIORITY").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("PRIORITY").item(0).getTextContent() : null;	
					String monthYear = (potAndCurrLiabilitiesHeader.getElementsByTagName("MONTHYEAR").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("MONTHYEAR").item(0).getTextContent() : "-";
														
					ConsPotentialAndCurrentLiabilitiesHeader consPotentialAndCurrentLiabilitiesHeader = new ConsPotentialAndCurrentLiabilitiesHeader();
					consPotentialAndCurrentLiabilitiesHeader.setRequestDetailId(requestDetailId);
					consPotentialAndCurrentLiabilitiesHeader.setBureauCurrency(currency);
					consPotentialAndCurrentLiabilitiesHeader.setPriority(formatInts(priority));
					consPotentialAndCurrentLiabilitiesHeader.setMonthyear(monthYear);
					consPotentialAndCurrentLiabilitiesHeader.setIsActive(1);					
					consPotentialAndCurrentLiabilitiesHeader.setCreatedBy(username);
					consPotentialAndCurrentLiabilitiesHeader.setCreated(created);
					
					consPotentialAndCurrentLiabilitiesHeaderList.add(consPotentialAndCurrentLiabilitiesHeader);
				}
			}
			return consPotentialAndCurrentLiabilitiesHeaderList;
		}
		
		//================ Setting Potential And Current Liabilities ================//
		public List<ConsPotentialAndCurrentLiabilities> insrtPotAndCurrLiabilities(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsPotentialAndCurrentLiabilities> consPotentialAndCurrentLiabilitiesList = new ArrayList<ConsPotentialAndCurrentLiabilities>();
			NodeList nodeList = (NodeList) xPath.compile(s_potAndCurrLiabilities).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element potAndCurrLiabilities = (Element) nodeList.item(x);
				if(potAndCurrLiabilities.hasChildNodes()) {	
					String currency         = (potAndCurrLiabilities.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
					String ownership        = (potAndCurrLiabilities.getElementsByTagName("OWNERSHIP_TYPE").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("OWNERSHIP_TYPE").item(0).getTextContent() : "-";
					String numOfacilities   = (potAndCurrLiabilities.getElementsByTagName("TOTAL_NO_OF_CREDITFACILITIES").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("TOTAL_NO_OF_CREDITFACILITIES").item(0).getTextContent() : null;
					String grantedAmt       = (potAndCurrLiabilities.getElementsByTagName("SANCTIONED_AMOUNT").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("SANCTIONED_AMOUNT").item(0).getTextContent() : null;
					String totalOutstanding = (potAndCurrLiabilities.getElementsByTagName("TOTAL_OUTSTANDING").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("TOTAL_OUTSTANDING").item(0).getTextContent() : null;
					  						
					ConsPotentialAndCurrentLiabilities consPotentialAndCurrentLiabilities = new ConsPotentialAndCurrentLiabilities();
					consPotentialAndCurrentLiabilities.setRequestDetailId(requestDetailId);
					consPotentialAndCurrentLiabilities.setBureauCurrency(currency);
					consPotentialAndCurrentLiabilities.setOwnership(ownership);
					consPotentialAndCurrentLiabilities.setNoOfCreditFacilities(formatInts(numOfacilities));
					consPotentialAndCurrentLiabilities.setTotalAmountGranted(formatAmounts(grantedAmt));
					consPotentialAndCurrentLiabilities.setTotalOutstanding(formatAmounts(totalOutstanding));
					consPotentialAndCurrentLiabilities.setIsActive(1);					
					consPotentialAndCurrentLiabilities.setCreatedBy(username);
					consPotentialAndCurrentLiabilities.setCreated(created);
					
					consPotentialAndCurrentLiabilitiesList.add(consPotentialAndCurrentLiabilities);
				}
			}
			return consPotentialAndCurrentLiabilitiesList;
		}
		
		//================ Setting Credit Facilities Of Glance Status ================//
		public List<ConsCreditFacilityOfGlanceStatus> insrtCFOfGlanceStatus(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsCreditFacilityOfGlanceStatus> consCreditFacilityOfGlanceStatusList = new ArrayList<ConsCreditFacilityOfGlanceStatus>();
			NodeList nodeList = (NodeList) xPath.compile(s_cFOfGlanceStatus).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element cFOfGlanceStatus = (Element) nodeList.item(x);
				if(cFOfGlanceStatus.hasChildNodes()) {
					String catalogueCode    = (cFOfGlanceStatus.getElementsByTagName("CATALOGUE_CODE").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("CATALOGUE_CODE").item(0).getTextContent() : "-";
					String catalogueVal     = (cFOfGlanceStatus.getElementsByTagName("CATALOGUE_VAL_ENGLISH").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("CATALOGUE_VAL_ENGLISH").item(0).getTextContent() : "-";
					String monthYear        = (cFOfGlanceStatus.getElementsByTagName("MONTHYEAR").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("MONTHYEAR").item(0).getTextContent() : "-";
					String status           = (cFOfGlanceStatus.getElementsByTagName("BUREAU_ACC_STATUS").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("BUREAU_ACC_STATUS").item(0).getTextContent() : "-";
					String arreasDays0      = (cFOfGlanceStatus.getElementsByTagName("ZERO").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("ZERO").item(0).getTextContent() : null;
					String arreasDays1_30   = (cFOfGlanceStatus.getElementsByTagName("ONETO30").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("ONETO30").item(0).getTextContent() : null;
					String arreasDays31_60  = (cFOfGlanceStatus.getElementsByTagName("THIRTYONETO60").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("THIRTYONETO60").item(0).getTextContent() : null;
					String arreasDays61_90  = (cFOfGlanceStatus.getElementsByTagName("SIXTYONETO90").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("SIXTYONETO90").item(0).getTextContent() : null;
					String arreasDaysOver90 = (cFOfGlanceStatus.getElementsByTagName("NINETY").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("NINETY").item(0).getTextContent() : null;
					  						
					ConsCreditFacilityOfGlanceStatus consCreditFacilityOfGlanceStatus = new ConsCreditFacilityOfGlanceStatus();
					consCreditFacilityOfGlanceStatus.setRequestDetailId(requestDetailId);
					consCreditFacilityOfGlanceStatus.setCatalogueCode(catalogueCode);
					consCreditFacilityOfGlanceStatus.setCatalogueValEnglish(catalogueVal);
					consCreditFacilityOfGlanceStatus.setMonthyear(monthYear);
					consCreditFacilityOfGlanceStatus.setStatus(status);
					consCreditFacilityOfGlanceStatus.setArrearsDays0(formatInts(arreasDays0));
					consCreditFacilityOfGlanceStatus.setArrearsDays1_30(formatInts(arreasDays1_30));
					consCreditFacilityOfGlanceStatus.setArrearsDays31_60(formatInts(arreasDays31_60));
					consCreditFacilityOfGlanceStatus.setArrearsDays61_90(formatInts(arreasDays61_90));
					consCreditFacilityOfGlanceStatus.setArrearsDays90(formatInts(arreasDaysOver90));
					consCreditFacilityOfGlanceStatus.setIsActive(1);					
					consCreditFacilityOfGlanceStatus.setCreatedBy(username);
					consCreditFacilityOfGlanceStatus.setCreated(created);
					
					consCreditFacilityOfGlanceStatusList.add(consCreditFacilityOfGlanceStatus);
				}
			}
			return consCreditFacilityOfGlanceStatusList;
		}
		
		//================ Setting Dishonoured Cheque Summary ================//
		public List<ConsDishonouredChequeSummary> insrtDishonChequeSummary(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsDishonouredChequeSummary> consDishonouredChequeSummaryList = new ArrayList<ConsDishonouredChequeSummary>();
			NodeList nodeList = (NodeList) xPath.compile(s_dishonChequeSummary).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element dishonChequeSummary = (Element) nodeList.item(x);
				if(dishonChequeSummary.hasChildNodes()) {	
					String numberOfCheques = (dishonChequeSummary.getElementsByTagName("NO_OF_DC").getLength() > 0) ? dishonChequeSummary.getElementsByTagName("NO_OF_DC").item(0).getTextContent() : null;
					String chequeValue     = (dishonChequeSummary.getElementsByTagName("TOT_AMT_OF_DC").getLength() > 0) ? dishonChequeSummary.getElementsByTagName("TOT_AMT_OF_DC").item(0).getTextContent() : null;
					String ruid            = (dishonChequeSummary.getElementsByTagName("RUID").getLength() > 0) ? dishonChequeSummary.getElementsByTagName("RUID").item(0).getTextContent() : null;
					  						
					ConsDishonouredChequeSummary consDishonouredChequeSummary = new ConsDishonouredChequeSummary();
					consDishonouredChequeSummary.setRequestDetailId(requestDetailId);
					consDishonouredChequeSummary.setNumberOfCheques(formatInts(numberOfCheques));
					consDishonouredChequeSummary.setChequeValue(formatAmounts(chequeValue));
					consDishonouredChequeSummary.setRuId(formatLongs(ruid));
					consDishonouredChequeSummary.setIsActive(1);				
					consDishonouredChequeSummary.setCreatedBy(username);
					consDishonouredChequeSummary.setCreated(created);
					
					consDishonouredChequeSummaryList.add(consDishonouredChequeSummary);
				}
			}
			return consDishonouredChequeSummaryList;
		}
		
		//================ Setting Dishonoured Cheque Details ================//
		public List<ConsDishonouredChequeDetails> insrtDishonChequeDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsDishonouredChequeDetails> consDishonouredChequeDetailsList = new ArrayList<ConsDishonouredChequeDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_dishonChequeDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element dishonChequeDetails = (Element) nodeList.item(x);
				if(dishonChequeDetails.hasChildNodes()) {
					String sno 			= (dishonChequeDetails.getElementsByTagName("SNO").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String instAndBranch = (dishonChequeDetails.getElementsByTagName("INSTBRANCH").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("INSTBRANCH").item(0).getTextContent() : "-";
					String chequeNumber = (dishonChequeDetails.getElementsByTagName("CHEQUE_NUMBER").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("CHEQUE_NUMBER").item(0).getTextContent() : "-";
					String chequeAmount = (dishonChequeDetails.getElementsByTagName("CHEQUE_AMOUNT").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("CHEQUE_AMOUNT").item(0).getTextContent() : null;
					String disHonDate   = (dishonChequeDetails.getElementsByTagName("DATE_DISHONOURED").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("DATE_DISHONOURED").item(0).getTextContent() : null;
					String reason       = (dishonChequeDetails.getElementsByTagName("REASON_FOR_DISHONOUR").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("REASON_FOR_DISHONOUR").item(0).getTextContent() : "-";
					String accountNo 	= (dishonChequeDetails.getElementsByTagName("ACCOUNT_NUMBER").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("ACCOUNT_NUMBER").item(0).getTextContent() : "-";
					String blockFlag    = (dishonChequeDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					String ruid         = (dishonChequeDetails.getElementsByTagName("RUID").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;
					  						
					ConsDishonouredChequeDetails consDishonouredChequeDetails = new ConsDishonouredChequeDetails();
					consDishonouredChequeDetails.setRequestDetailId(requestDetailId);
					consDishonouredChequeDetails.setSNo(formatInts(sno));
					consDishonouredChequeDetails.setInstitutionAndBranch(instAndBranch);
					consDishonouredChequeDetails.setChequeNumber(chequeNumber);
					consDishonouredChequeDetails.setChequeAmount(formatAmounts(chequeAmount));
					consDishonouredChequeDetails.setDateDishonoured(formatDates(disHonDate));
					consDishonouredChequeDetails.setReason(reason);
					consDishonouredChequeDetails.setAccountNumber(accountNo);
					consDishonouredChequeDetails.setBlockFlag(formatShorts(blockFlag));
					consDishonouredChequeDetails.setRuId(formatLongs(ruid));
					consDishonouredChequeDetails.setIsActive(1);					
					consDishonouredChequeDetails.setCreatedBy(username);
					consDishonouredChequeDetails.setCreated(created);
					
					consDishonouredChequeDetailsList.add(consDishonouredChequeDetails);
				}
			}
			return consDishonouredChequeDetailsList;
		}
		
		//================ Setting Catalogue Description ================//
		public List<ConsCatalogueDescription> insrtCatalogueDescription(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<ConsCatalogueDescription> consCatalogueDescriptionList = new ArrayList<ConsCatalogueDescription>();
			NodeList nodeList = (NodeList) xPath.compile(s_catalogueDescription).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element catalogueDescription = (Element) nodeList.item(x);
				if(catalogueDescription.hasChildNodes()) {	
					String sno 	 = (catalogueDescription.getElementsByTagName("SerialNumber").getLength() > 0) ? catalogueDescription.getElementsByTagName("SerialNumber").item(0).getTextContent() : null;
					String label = (catalogueDescription.getElementsByTagName("Label").getLength() > 0) ? catalogueDescription.getElementsByTagName("Label").item(0).getTextContent() : "-";
					String value = (catalogueDescription.getElementsByTagName("Values").getLength() > 0) ? catalogueDescription.getElementsByTagName("Values").item(0).getTextContent().replace("'", "`") : "-";
					  						
					ConsCatalogueDescription consCatalogueDescription = new ConsCatalogueDescription();
					consCatalogueDescription.setRequestDetailId(requestDetailId);
					consCatalogueDescription.setSNo(formatInts(sno));
					consCatalogueDescription.setCatgLabel(label);
					consCatalogueDescription.setCatgValue(value);
					consCatalogueDescription.setIsActive(1);				
					consCatalogueDescription.setCreatedBy(username);
					consCatalogueDescription.setCreated(created);
					
					consCatalogueDescriptionList.add(consCatalogueDescription);
				}
			}
			return consCatalogueDescriptionList;
		}
		
		//================ Setting Search Results ================//
		public List<ConsSearchResults> insrtSearchResults(Document doc, Long requestDetailId, String username) {
			    
			List<ConsSearchResults> consSearchResultsList = new ArrayList<ConsSearchResults>();
			for(int x=0; x<doc.getElementsByTagName("SEARCH-RESULT-ITEM").getLength(); x++) {
					
				Element searchResultItem = (Element) doc.getElementsByTagName("SEARCH-RESULT-ITEM").item(x);
				String name = searchResultItem.getAttribute("NAME");
				String bureauId = searchResultItem.getAttribute("BUREAU-ID");
						
				if(searchResultItem.hasChildNodes()) {
					Element identifier = (Element) searchResultItem.getElementsByTagName("IDENTIFIER").item(0);
					String identifierSource = identifier.getAttribute("IDSOURCE");
					String identifierValue = identifier.getAttribute("IDVALUE");
					String identifierMatched = identifier.getAttribute("MATCHED");	
					Element surrogate = (Element) searchResultItem.getElementsByTagName("SURROGATE").item(0);
					String surrogateId = surrogate.getAttribute("ID");
					String surrogateMatched = surrogate.getAttribute("MATCHED");
											
					ConsSearchResults consSearchResults = new ConsSearchResults();
					consSearchResults.setRequestDetailId(requestDetailId);
					consSearchResults.setName(name);
					consSearchResults.setBureauId(bureauId);
					consSearchResults.setIdentifierIdSource(identifierSource);
					consSearchResults.setIdentifierValue(identifierValue);
					consSearchResults.setIdentifierMatched(identifierMatched);
					consSearchResults.setSurrogateId(surrogateId);
					consSearchResults.setSurrogateMatched(surrogateMatched);
					consSearchResults.setIsActive(1);					
					consSearchResults.setCreatedBy(username);
					consSearchResults.setCreated(created);
					
					consSearchResultsList.add(consSearchResults);
				}		
			}
			return consSearchResultsList;
		}
				
		//================ Get SEARCH-RESULT-LIST ================//
		public SearchResults getSearchResults(Document doc) {
			
			SearchResults searchResults = new SearchResults();

			List<SearchResultItem> searchResultItemList = new ArrayList<SearchResultItem>();
			for(int x=0; x<doc.getElementsByTagName("SEARCH-RESULT-ITEM").getLength(); x++) {
				
				SearchResultItem srchRsltItem = new SearchResultItem();
				Element searchResultItem = (Element) doc.getElementsByTagName("SEARCH-RESULT-ITEM").item(x);
				srchRsltItem.setName(searchResultItem.getAttribute("NAME"));
				srchRsltItem.setBureauId(searchResultItem.getAttribute("BUREAU-ID"));	
					
				if(searchResultItem.hasChildNodes()) {
					Element identifier = (Element) searchResultItem.getElementsByTagName("IDENTIFIER").item(0);
					srchRsltItem.setIdentifierSource(identifier.getAttribute("IDSOURCE"));
					srchRsltItem.setIdentifierValue(identifier.getAttribute("IDVALUE"));
					srchRsltItem.setIdentifierMatched(identifier.getAttribute("MATCHED"));	
					Element surrogate = (Element) searchResultItem.getElementsByTagName("SURROGATE").item(0);
					srchRsltItem.setSurrogate(surrogate.getAttribute("ID"));
					srchRsltItem.setSurrogateMatched(surrogate.getAttribute("MATCHED"));					
				}
				searchResultItemList.add(srchRsltItem);		
			}
			searchResults.setSearchResultItem(searchResultItemList);	
		
			return searchResults;
		}
			
		//================ Get MultiHit response ================//
		public MultiHit getMultiHitResponse(Document doc) {
			
			MultiHit multiHit = new MultiHit();
			multiHit.setReferenceNumber(doc.getDocumentElement().getAttribute("REFERENCE-NO"));

			List<SearchResultItem> searchResultItemList = new ArrayList<SearchResultItem>();
			for(int x=0; x<doc.getElementsByTagName("SEARCH-RESULT-ITEM").getLength(); x++) {
				
				SearchResultItem srchRsltItem = new SearchResultItem();
				Element searchResultItem = (Element) doc.getElementsByTagName("SEARCH-RESULT-ITEM").item(x);
				srchRsltItem.setName(searchResultItem.getAttribute("NAME"));
				srchRsltItem.setBureauId(searchResultItem.getAttribute("BUREAU-ID"));	
					
				if(searchResultItem.hasChildNodes()) {
					Element identifier = (Element) searchResultItem.getElementsByTagName("IDENTIFIER").item(0);
					srchRsltItem.setIdentifierSource(identifier.getAttribute("IDSOURCE"));
					srchRsltItem.setIdentifierValue(identifier.getAttribute("IDVALUE"));
					srchRsltItem.setIdentifierMatched(identifier.getAttribute("MATCHED"));	
					Element surrogate = (Element) searchResultItem.getElementsByTagName("SURROGATE").item(0);
					srchRsltItem.setSurrogate(surrogate.getAttribute("ID"));
					srchRsltItem.setSurrogateMatched(surrogate.getAttribute("MATCHED"));					
				}
				searchResultItemList.add(srchRsltItem);		
			}
			multiHit.setSearchResultItem(searchResultItemList);	
		
			return multiHit;
		}
	
		// Format amounts to insert DB
		public Double formatAmounts(String amount) {
			Double amt = null;
            if(amount != null && amount.trim().length() != 0){
            	amt = Double.parseDouble(amount.replaceAll(",", "").trim());           	    	
            }   
			return amt;
		}
		
		// Format dates to insert DB
		public Date formatDates(String dateVal) {
			List<String> formatStrings = Arrays.asList("yyyy-MM-dd", "dd-MMM-yyyy");
            if(dateVal != null && dateVal.trim().length() != 0){           	
            	for (String formatString : formatStrings) {
                    try {
                        return new SimpleDateFormat(formatString).parse(dateVal);
                    } catch (ParseException e) {}
                }  
            	return null;
            }   
			return null;
		}
		
		// Format Integers to insert DB
		public Integer formatInts(String number) {
			Integer no = null;
			if(number != null && number.trim().length() != 0){
				no = Integer.parseInt(number.trim());           	    	
			}   
			return no;
		}
		
		// Format Longs to insert DB
		public Long formatLongs(String number) {
			Long no = null;
			if(number != null && number.trim().length() != 0){
				no = Long.parseLong(number.trim());           	    	
			}   
			return no;
		}
		
		// Format Shorts to insert DB
		public Short formatShorts(String number) {
			Short no = null;
			if(number != null && number.trim().length() != 0){
				no = Short.parseShort(number.trim());           	    	
			}   
			return no;
		}

}
