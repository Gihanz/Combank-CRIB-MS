package com.nable.crib.corp.util;

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

import com.nable.crib.corp.model.CorpCatalogueDescription;
import com.nable.crib.corp.model.CorpCreditFacility;
import com.nable.crib.corp.model.CorpCreditFacilityDetails;
import com.nable.crib.corp.model.CorpCreditFacilityDetailsLast24Months;
import com.nable.crib.corp.model.CorpCreditFacilityOfGlanceStatus;
import com.nable.crib.corp.model.CorpDishonouredChequeDetails;
import com.nable.crib.corp.model.CorpDishonouredChequeSummary;
import com.nable.crib.corp.model.CorpDisputeDetails;
import com.nable.crib.corp.model.CorpEconomicActivityHistory;
import com.nable.crib.corp.model.CorpFirmographicDetails;
import com.nable.crib.corp.model.CorpIdentificationDetails;
import com.nable.crib.corp.model.CorpInquiriesBySubject;
import com.nable.crib.corp.model.CorpLast24Months;
import com.nable.crib.corp.model.CorpLendingInstutionsInquiries;
import com.nable.crib.corp.model.CorpMailingAddresses;
import com.nable.crib.corp.model.CorpPermanentAddresses;
import com.nable.crib.corp.model.CorpPotentialAndCurrentLiabilities;
import com.nable.crib.corp.model.CorpPotentialAndCurrentLiabilitiesHeader;
import com.nable.crib.corp.model.CorpRelationshipAddressDetails;
import com.nable.crib.corp.model.CorpRelationshipDetails;
import com.nable.crib.corp.model.CorpReportedNames;
import com.nable.crib.corp.model.CorpSearchResults;
import com.nable.crib.corp.model.CorpSettledCreditFacilitiesDetails;
import com.nable.crib.corp.model.CorpSettledCreditFacilitiesSummary;
import com.nable.crib.corp.response.MultiHit;
import com.nable.crib.corp.response.SearchResultItem;
import com.nable.crib.corp.response.SearchResults;

@Component
@PropertySource("classpath:application.properties")
public class CorporateResponseReader {
	
	static Logger log = LoggerFactory.getLogger(CorporateResponseReader.class);
	
	@Value("${crib.corp.s_commercialDetails}")
	private String s_commercialDetails;
	@Value("${crib.corp.s_identificationDetails}")
	private String s_identificationDetails;
	@Value("${crib.corp.s_mailingAddresses}")
	private String s_mailingAddresses;
	@Value("${crib.corp.s_permanentAddresses}")
	private String s_permanentAddresses;
	@Value("${crib.corp.s_reportedNames}")
	private String s_reportedNames;
	@Value("${crib.corp.s_relationships}")
	private String s_relationships;
	@Value("${crib.corp.s_relationshipAddresses}")
	private String s_relationshipAddresses;
	@Value("${crib.corp.s_settledCFDetails}")
	private String s_settledCFDetails;
	@Value("${crib.corp.s_settledCFSummary}")
	private String s_settledCFSummary;
	@Value("${crib.corp.s_lendingInstInquiries}")
	private String s_lendingInstInquiries;
	@Value("${crib.corp.s_inqBySubject}")
	private String s_inqBySubject;
	@Value("${crib.corp.s_creditFacility}")
	private String s_creditFacility;
	@Value("${crib.corp.s_cFDetails}")
	private String s_cFDetails;
	@Value("${crib.corp.s_last24Months}")
	private String s_last24Months;
	@Value("${crib.corp.s_cfFor24Months}")
	private String s_cfFor24Months;
	@Value("${crib.corp.s_disputeDetails}")
	private String s_disputeDetails;
	@Value("${crib.corp.s_potAndCurrLiabilitiesHeader}")
	private String s_potAndCurrLiabilitiesHeader;
	@Value("${crib.corp.s_potAndCurrLiabilities}")
	private String s_potAndCurrLiabilities;
	@Value("${crib.corp.s_cFOfGlanceStatus}")
	private String s_cFOfGlanceStatus;
	@Value("${crib.corp.s_dishonChequeSummary}")
	private String s_dishonChequeSummary;
	@Value("${crib.corp.s_dishonChequeDetails}")
	private String s_dishonChequeDetails;
	@Value("${crib.corp.s_econActivityHistory}")
	private String s_econActivityHistory;
	@Value("${crib.corp.s_catalogueDescription}")
	private String s_catalogueDescription;
	
	Date created = new Date(System.currentTimeMillis());
	XPath xPath = XPathFactory.newInstance().newXPath();
			
		//================ Setting Firmographic Details ================//
		public CorpFirmographicDetails insrtFirmographicDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
						
			NodeList nodeList = (NodeList) xPath.compile(s_commercialDetails).evaluate(doc, XPathConstants.NODESET);
				
			Element commercialDetails = (Element) nodeList.item(0);
			String name              = commercialDetails.getElementsByTagName("NAME").item(0).getTextContent();
			String dateOfReg         = (commercialDetails.getElementsByTagName("REGISTRATION_DATE").getLength() > 0) ? commercialDetails.getElementsByTagName("REGISTRATION_DATE").item(0).getTextContent() : null;
			String areaCode          = (commercialDetails.getElementsByTagName("AREA_CODE").getLength() > 0) ? commercialDetails.getElementsByTagName("AREA_CODE").item(0).getTextContent() : "-";
			String telephoneNumber   = (commercialDetails.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? commercialDetails.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
			String faxNumber          = (commercialDetails.getElementsByTagName("FAX_NUMBER").getLength() > 0) ? commercialDetails.getElementsByTagName("FAX_NUMBER").item(0).getTextContent() : "-";
			String legalConstitution = (commercialDetails.getElementsByTagName("LEGAL_CONSTITUTION").getLength() > 0) ? commercialDetails.getElementsByTagName("LEGAL_CONSTITUTION").item(0).getTextContent() : "-";
			String econActivity1     = (commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY1").getLength() > 0) ? commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY1").item(0).getTextContent() : "-";
			String econActivity2     = (commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY2").getLength() > 0) ? commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY2").item(0).getTextContent() : "-";
			String econActivity3     = (commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY3").getLength() > 0) ? commercialDetails.getElementsByTagName("ECONOMIC_ACTIVITY3").item(0).getTextContent() : "-";
			String url               = (commercialDetails.getElementsByTagName("URL").getLength() > 0) ? commercialDetails.getElementsByTagName("URL").item(0).getTextContent() : "-";
			String cra               = (commercialDetails.getElementsByTagName("CRA").getLength() > 0) ? commercialDetails.getElementsByTagName("CRA").item(0).getTextContent().trim() : "-";
			String craRuid           = (commercialDetails.getElementsByTagName("CRA_RUID").getLength() > 0) ? commercialDetails.getElementsByTagName("CRA_RUID").item(0).getTextContent() : null;
			String vat               = (commercialDetails.getElementsByTagName("VAT").getLength() > 0) ? commercialDetails.getElementsByTagName("VAT").item(0).getTextContent().trim() : "-";
			String vatRuid           = (commercialDetails.getElementsByTagName("VAT_RUID").getLength() > 0) ? commercialDetails.getElementsByTagName("VAT_RUID").item(0).getTextContent() : null;
			String ruid              = (commercialDetails.getElementsByTagName("RUID").getLength() > 0) ? commercialDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;
			String lastReportedDate  = (commercialDetails.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? commercialDetails.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
			String blockFlag         = (commercialDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? commercialDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
						
			CorpFirmographicDetails corpFirmographicDetails = new CorpFirmographicDetails();
			corpFirmographicDetails.setRequestDetailId(requestDetailId);
			corpFirmographicDetails.setName(name);
			corpFirmographicDetails.setDateOfRegistration(formatDates(dateOfReg));
			corpFirmographicDetails.setAreaCode(areaCode);
			corpFirmographicDetails.setTelephoneNumber(telephoneNumber);
			corpFirmographicDetails.setFaxNumber(faxNumber);
			corpFirmographicDetails.setLegalConstitution(legalConstitution);
			corpFirmographicDetails.setEconomicActivity1(econActivity1);
			corpFirmographicDetails.setEconomicActivity2(econActivity2);
			corpFirmographicDetails.setEconomicActivity3(econActivity3);
			corpFirmographicDetails.setUrl(url);
			corpFirmographicDetails.setCra(cra);
			corpFirmographicDetails.setCraRuId(formatLongs(craRuid));
			corpFirmographicDetails.setVat(vat);
			corpFirmographicDetails.setVatRuId(formatLongs(vatRuid));
			corpFirmographicDetails.setRuId(formatLongs(ruid));
			corpFirmographicDetails.setLastReportedDate(formatDates(lastReportedDate));
			corpFirmographicDetails.setBlockFlag(formatShorts(blockFlag));
			corpFirmographicDetails.setIsActive(1);			
			corpFirmographicDetails.setCreatedBy(username);
			corpFirmographicDetails.setCreated(created);
			
			return corpFirmographicDetails;
		}
		
		//================ Setting Identification Details ================//
		public List<CorpIdentificationDetails> insrtIdentificationDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
							
			List<CorpIdentificationDetails> corpIdentificationDetailsList = new ArrayList<CorpIdentificationDetails>();		
			NodeList nodeList = (NodeList) xPath.compile(s_identificationDetails).evaluate(doc, XPathConstants.NODESET);
							
			for(int x=0; x<nodeList.getLength(); x++) {
									
				Element identificationDetails = (Element) nodeList.item(x);
				if(identificationDetails.hasChildNodes()) {	
					String sourceId      = (identificationDetails.getElementsByTagName("SOURCE_ID").getLength() > 0) ? identificationDetails.getElementsByTagName("SOURCE_ID").item(0).getTextContent() : "-";
					String idValue       = (identificationDetails.getElementsByTagName("ID_VALUE").getLength() > 0) ? identificationDetails.getElementsByTagName("ID_VALUE").item(0).getTextContent() : "-";
					String idDisplayName = (identificationDetails.getElementsByTagName("ID_DISPLAY_NAME").getLength() > 0) ? identificationDetails.getElementsByTagName("ID_DISPLAY_NAME").item(0).getTextContent() : "-";
					String ruid          = (identificationDetails.getElementsByTagName("RUID").getLength() > 0) ? identificationDetails.getElementsByTagName("RUID").item(0).getTextContent() : null;			
															
					CorpIdentificationDetails corpIdentificationDetails = new CorpIdentificationDetails();
					corpIdentificationDetails.setRequestDetailId(requestDetailId);
					corpIdentificationDetails.setSourceId(sourceId);
					corpIdentificationDetails.setIdValue(idValue);
					corpIdentificationDetails.setIdDisplayName(idDisplayName);
					corpIdentificationDetails.setRuId(formatLongs(ruid));
					corpIdentificationDetails.setIsActive(1);					
					corpIdentificationDetails.setCreatedBy(username);
					corpIdentificationDetails.setCreated(created);
					
					corpIdentificationDetailsList.add(corpIdentificationDetails);
				}
			}
			return corpIdentificationDetailsList;
		} 
		
		//================ Setting Mailing Details ================//
		public List<CorpMailingAddresses> insrtMailingAddresses(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			
			List<CorpMailingAddresses> corpMailingAddressesList = new ArrayList<CorpMailingAddresses>();			
			NodeList nodeList = (NodeList) xPath.compile(s_mailingAddresses).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
					
				Element mailingAddresses = (Element) nodeList.item(x);
				if(mailingAddresses.hasChildNodes()) {	
					String sNo          = (mailingAddresses.getElementsByTagName("SERIAL_NO").getLength() > 0) ? mailingAddresses.getElementsByTagName("SERIAL_NO").item(0).getTextContent() : null;
					String addressType  = (mailingAddresses.getElementsByTagName("ADDRESS_TYPE").getLength() > 0) ? mailingAddresses.getElementsByTagName("ADDRESS_TYPE").item(0).getTextContent() : "-";
					String buildingNo   = (mailingAddresses.getElementsByTagName("BUILDING_NUMBER").getLength() > 0) ? mailingAddresses.getElementsByTagName("BUILDING_NUMBER").item(0).getTextContent() : "-";
					String country      = (mailingAddresses.getElementsByTagName("COUNTRY").getLength() > 0) ? mailingAddresses.getElementsByTagName("COUNTRY").item(0).getTextContent() : "-";
					String road			= (mailingAddresses.getElementsByTagName("ROAD").getLength() > 0) ? mailingAddresses.getElementsByTagName("ROAD").item(0).getTextContent() : "-";
					String city			= (mailingAddresses.getElementsByTagName("CITY").getLength() > 0) ? mailingAddresses.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String reportedDate = (mailingAddresses.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? mailingAddresses.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String area         = (mailingAddresses.getElementsByTagName("AREA").getLength() > 0) ? mailingAddresses.getElementsByTagName("AREA").item(0).getTextContent() : "-";
					String district     = (mailingAddresses.getElementsByTagName("DISTRICT").getLength() > 0) ? mailingAddresses.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String province     = (mailingAddresses.getElementsByTagName("PROVINCE").getLength() > 0) ? mailingAddresses.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String doorNumber   = (mailingAddresses.getElementsByTagName("DOOR_NUMBER").getLength() > 0) ? mailingAddresses.getElementsByTagName("DOOR_NUMBER").item(0).getTextContent() : "-";
					String address      = (mailingAddresses.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? mailingAddresses.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent().replace("'", "`") : "-";
					String blockFlag    = (mailingAddresses.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? mailingAddresses.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
						
					CorpMailingAddresses corpMailingAddresses = new CorpMailingAddresses();
					corpMailingAddresses.setRequestDetailId(requestDetailId);
					corpMailingAddresses.setSNo(formatInts(sNo));
					corpMailingAddresses.setAddressType(addressType);
					corpMailingAddresses.setBuildingNumber(buildingNo);
					corpMailingAddresses.setCountry(country);
					corpMailingAddresses.setRoad(road);
					corpMailingAddresses.setCity(city);
					corpMailingAddresses.setLastReportedDate(formatDates(reportedDate));
					corpMailingAddresses.setArea(area);
					corpMailingAddresses.setDistrict(district);
					corpMailingAddresses.setProvince(province);
					corpMailingAddresses.setDoorNumber(doorNumber);				
					corpMailingAddresses.setAddressValue(address);
					corpMailingAddresses.setBlockFlag(formatShorts(blockFlag));
					corpMailingAddresses.setIsActive(1);				
					corpMailingAddresses.setCreatedBy(username);	
					corpMailingAddresses.setCreated(created);
					
					corpMailingAddressesList.add(corpMailingAddresses);
				}
			}
			return corpMailingAddressesList;
		}    
		
		//================ Setting Permanent Details ================//
		public List<CorpPermanentAddresses> insrtPermanentAddresses(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
		    
			List<CorpPermanentAddresses> corpPermanentAddressesList = new ArrayList<CorpPermanentAddresses>();			
			NodeList nodeList = (NodeList) xPath.compile(s_permanentAddresses).evaluate(doc, XPathConstants.NODESET);
		
			for(int x=0; x<nodeList.getLength(); x++) {
			  			
				Element permanentAddresses = (Element) nodeList.item(x);
				if(permanentAddresses.hasChildNodes()) {		
					String sNo          = (permanentAddresses.getElementsByTagName("SERIAL_NO").getLength() > 0) ? permanentAddresses.getElementsByTagName("SERIAL_NO").item(0).getTextContent() : null;
					String addressType  = (permanentAddresses.getElementsByTagName("ADDRESS_TYPE").getLength() > 0) ? permanentAddresses.getElementsByTagName("ADDRESS_TYPE").item(0).getTextContent() : "-";
					String reportedDate = (permanentAddresses.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? permanentAddresses.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String buildingNo   = (permanentAddresses.getElementsByTagName("BUILDING_NUMBER").getLength() > 0) ? permanentAddresses.getElementsByTagName("BUILDING_NUMBER").item(0).getTextContent() : "-";
					String country      = (permanentAddresses.getElementsByTagName("COUNTRY").getLength() > 0) ? permanentAddresses.getElementsByTagName("COUNTRY").item(0).getTextContent() : "-";
					String road			= (permanentAddresses.getElementsByTagName("ROAD").getLength() > 0) ? permanentAddresses.getElementsByTagName("ROAD").item(0).getTextContent() : "-";
					String city			= (permanentAddresses.getElementsByTagName("CITY").getLength() > 0) ? permanentAddresses.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String district     = (permanentAddresses.getElementsByTagName("DISTRICT").getLength() > 0) ? permanentAddresses.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String province     = (permanentAddresses.getElementsByTagName("PROVINCE").getLength() > 0) ? permanentAddresses.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String area         = (permanentAddresses.getElementsByTagName("AREA").getLength() > 0) ? permanentAddresses.getElementsByTagName("AREA").item(0).getTextContent() : "-";
					String doorNumber   = (permanentAddresses.getElementsByTagName("DOOR_NUMBER").getLength() > 0) ? permanentAddresses.getElementsByTagName("DOOR_NUMBER").item(0).getTextContent() : "-";
					String address      = (permanentAddresses.getElementsByTagName("ADDRESS_VALUE").getLength() > 0) ? permanentAddresses.getElementsByTagName("ADDRESS_VALUE").item(0).getTextContent().replace("'", "`") : "-";
					String blockFlag    = (permanentAddresses.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? permanentAddresses.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
				  							
					CorpPermanentAddresses corpPermanentAddresses = new CorpPermanentAddresses();
					corpPermanentAddresses.setRequestDetailId(requestDetailId);
					corpPermanentAddresses.setSNo(formatInts(sNo));
					corpPermanentAddresses.setAddressType(addressType);
					corpPermanentAddresses.setLastReportedDate(formatDates(reportedDate));
					corpPermanentAddresses.setBuildingNumber(buildingNo);
					corpPermanentAddresses.setCountry(country);
					corpPermanentAddresses.setRoad(road);
					corpPermanentAddresses.setCity(city);
					corpPermanentAddresses.setDistrict(district);
					corpPermanentAddresses.setProvince(province);
					corpPermanentAddresses.setArea(area);
					corpPermanentAddresses.setDoorNumber(doorNumber);
					corpPermanentAddresses.setAddressValue(address);
					corpPermanentAddresses.setBlockFlag(formatShorts(blockFlag));
					corpPermanentAddresses.setIsActive(1);					
					corpPermanentAddresses.setCreatedBy(username);
					corpPermanentAddresses.setCreated(created);
					
					corpPermanentAddressesList.add(corpPermanentAddresses);
				}
			}
			return corpPermanentAddressesList;
		}
		
		//================ Setting Reported Names ================//
		public List<CorpReportedNames> insrtReportedNames(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpReportedNames> corpReportedNamesList = new ArrayList<CorpReportedNames>();
			NodeList nodeList = (NodeList) xPath.compile(s_reportedNames).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element reportedNames = (Element) nodeList.item(x);
				if(reportedNames.hasChildNodes()) {			
					String sNo                 = (reportedNames.getElementsByTagName("SNO").getLength() > 0) ? reportedNames.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String name                = (reportedNames.getElementsByTagName("NAME").getLength() > 0) ? reportedNames.getElementsByTagName("NAME").item(0).getTextContent() : "-";
					String reportedInstitution = (reportedNames.getElementsByTagName("REPORTED_INST").getLength() > 0) ? reportedNames.getElementsByTagName("REPORTED_INST").item(0).getTextContent().replace("'", "`") : "-";				
					String reportedDate        = (reportedNames.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? reportedNames.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String blockFlag           = (reportedNames.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? reportedNames.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					  						
					CorpReportedNames corpReportedNames = new CorpReportedNames();
					corpReportedNames.setRequestDetailId(requestDetailId);
					corpReportedNames.setSNo(formatInts(sNo));
					corpReportedNames.setName(name);
					corpReportedNames.setReportedInstitution(reportedInstitution);
					corpReportedNames.setReportedDate(formatDates(reportedDate));
					corpReportedNames.setBlockFlag(formatShorts(blockFlag));
					corpReportedNames.setIsActive(1);					
					corpReportedNames.setCreatedBy(username);
					corpReportedNames.setCreated(created);
					
					corpReportedNamesList.add(corpReportedNames);
				}
			}
			return corpReportedNamesList;
		}	
		
		//================ Setting Relationship Details ================//
		public List<CorpRelationshipDetails> insrtRelationshipDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpRelationshipDetails> corpRelationshipDetailsList = new ArrayList<CorpRelationshipDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_relationships).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element relationships = (Element) nodeList.item(x);
				if(relationships.hasChildNodes()) {		
					String sNo                  = (relationships.getElementsByTagName("SNO").getLength() > 0) ? relationships.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String relationRuid         = (relationships.getElementsByTagName("RELATION_RUID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_RUID").item(0).getTextContent() : null;
					String entityId             = (relationships.getElementsByTagName("ID_VALUE").getLength() > 0) ? relationships.getElementsByTagName("ID_VALUE").item(0).getTextContent() : "-";
					String natureOfRelationship = (relationships.getElementsByTagName("NATURE").getLength() > 0) ? relationships.getElementsByTagName("NATURE").item(0).getTextContent() : "-";
					String entityName           = (relationships.getElementsByTagName("NAME").getLength() > 0) ? relationships.getElementsByTagName("NAME").item(0).getTextContent() : "-";
					String fax                  = (relationships.getElementsByTagName("FAX_NUMBER").getLength() > 0) ? relationships.getElementsByTagName("FAX_NUMBER").item(0).getTextContent() : "-";
					String phone                = (relationships.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? relationships.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
					String address              = (relationships.getElementsByTagName("ADDRESS").getLength() > 0) ? relationships.getElementsByTagName("ADDRESS").item(0).getTextContent() : "-";				
					String province             = (relationships.getElementsByTagName("PROVINCE").getLength() > 0) ? relationships.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String district             = (relationships.getElementsByTagName("DISTRICT").getLength() > 0) ? relationships.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String city                 = (relationships.getElementsByTagName("CITY").getLength() > 0) ? relationships.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String postalCode           = (relationships.getElementsByTagName("POSTAL_CODE").getLength() > 0) ? relationships.getElementsByTagName("POSTAL_CODE").item(0).getTextContent() : "-";
					String relationshipType     = (relationships.getElementsByTagName("RELATIONSHIP_TYPE").getLength() > 0) ? relationships.getElementsByTagName("RELATIONSHIP_TYPE").item(0).getTextContent() : "-";
					String relationTypeId       = (relationships.getElementsByTagName("RELATION_TYPE_ID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_TYPE_ID").item(0).getTextContent() : "";
					String relationNatureId     = (relationships.getElementsByTagName("RELATION_NATURE_ID").getLength() > 0) ? relationships.getElementsByTagName("RELATION_NATURE_ID").item(0).getTextContent() : "";
					String blockFlag            = (relationships.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? relationships.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					  						
					CorpRelationshipDetails corpRelationshipDetails = new CorpRelationshipDetails();
					corpRelationshipDetails.setRequestDetailId(requestDetailId);
					corpRelationshipDetails.setSNo(formatInts(sNo));
					corpRelationshipDetails.setRelationRuId(formatLongs(relationRuid));
					corpRelationshipDetails.setEntityId(entityId);
					corpRelationshipDetails.setNatureOfRelationship(natureOfRelationship);
					corpRelationshipDetails.setEntityName(entityName);
					corpRelationshipDetails.setFaxNo(fax);
					corpRelationshipDetails.setPhoneNo(phone);
					corpRelationshipDetails.setAddress(address);
					corpRelationshipDetails.setProvince(province);
					corpRelationshipDetails.setDistrict(district);
					corpRelationshipDetails.setCity(city);
					corpRelationshipDetails.setPostalCode(postalCode);					
					corpRelationshipDetails.setRelationshipType(relationshipType);						
					corpRelationshipDetails.setRelationTypeId(relationTypeId);
					corpRelationshipDetails.setRelationNatureId(relationNatureId);
					corpRelationshipDetails.setBlockFlag(formatShorts(blockFlag));
					corpRelationshipDetails.setIsActive(1);					
					corpRelationshipDetails.setCreatedBy(username);
					corpRelationshipDetails.setCreated(created);
					
					corpRelationshipDetailsList.add(corpRelationshipDetails);
				}
			}
			return corpRelationshipDetailsList;
		}
		
		//================ Setting Relationship Address Details ================//
		public List<CorpRelationshipAddressDetails> insrtRelationshipAddressDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
							    
			List<CorpRelationshipAddressDetails> corpRelationshipAddressDetailsList = new ArrayList<CorpRelationshipAddressDetails>();				
			NodeList nodeList = (NodeList) xPath.compile(s_relationshipAddresses).evaluate(doc, XPathConstants.NODESET);
					
			for(int x=0; x<nodeList.getLength(); x++) {
								  			
				Element relationshipAddresses = (Element) nodeList.item(x);
				if(relationshipAddresses.hasChildNodes()) {	
					String sNo          = (relationshipAddresses.getElementsByTagName("SNO").getLength() > 0) ? relationshipAddresses.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String relationRuid = (relationshipAddresses.getElementsByTagName("RELATION_RUID").getLength() > 0) ? relationshipAddresses.getElementsByTagName("RELATION_RUID").item(0).getTextContent() : null;
					String address      = (relationshipAddresses.getElementsByTagName("ADDRESS").getLength() > 0) ? relationshipAddresses.getElementsByTagName("ADDRESS").item(0).getTextContent() : "-";
					String province     = (relationshipAddresses.getElementsByTagName("PROVINCE").getLength() > 0) ? relationshipAddresses.getElementsByTagName("PROVINCE").item(0).getTextContent() : "-";
					String district     = (relationshipAddresses.getElementsByTagName("DISTRICT").getLength() > 0) ? relationshipAddresses.getElementsByTagName("DISTRICT").item(0).getTextContent() : "-";
					String city         = (relationshipAddresses.getElementsByTagName("CITY").getLength() > 0) ? relationshipAddresses.getElementsByTagName("CITY").item(0).getTextContent() : "-";
					String phone        = (relationshipAddresses.getElementsByTagName("PHONE_NUMBER").getLength() > 0) ? relationshipAddresses.getElementsByTagName("PHONE_NUMBER").item(0).getTextContent() : "-";
					String fax          = (relationshipAddresses.getElementsByTagName("FAX_NUMBER").getLength() > 0) ? relationshipAddresses.getElementsByTagName("FAX_NUMBER").item(0).getTextContent() : "-";					
					String postalCode   = (relationshipAddresses.getElementsByTagName("POSTAL_CODE").getLength() > 0) ? relationshipAddresses.getElementsByTagName("POSTAL_CODE").item(0).getTextContent() : "-";
														 					  						
					CorpRelationshipAddressDetails corpRelationshipAddressDetails = new CorpRelationshipAddressDetails();
					corpRelationshipAddressDetails.setRequestDetailId(requestDetailId);
					corpRelationshipAddressDetails.setSNo(formatInts(sNo));
					corpRelationshipAddressDetails.setRelationRuId(formatLongs(relationRuid));
					corpRelationshipAddressDetails.setAddress(address);
					corpRelationshipAddressDetails.setProvince(province);
					corpRelationshipAddressDetails.setDistrict(district);
					corpRelationshipAddressDetails.setCity(city);
					corpRelationshipAddressDetails.setPhoneNumber(phone);
					corpRelationshipAddressDetails.setFaxNumber(fax);
					corpRelationshipAddressDetails.setPostalCode(postalCode);
					corpRelationshipAddressDetails.setIsActive(1);					
					corpRelationshipAddressDetails.setCreatedBy(username);
					corpRelationshipAddressDetails.setCreated(created);
					
					corpRelationshipAddressDetailsList.add(corpRelationshipAddressDetails);
				}
			}
			return corpRelationshipAddressDetailsList;
		}
	
		//================ Setting Settled Credit Facilities Details ================//
		public List<CorpSettledCreditFacilitiesDetails> insrtSettledCFDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpSettledCreditFacilitiesDetails> corpSettledCreditFacilitiesDetailsList = new ArrayList<CorpSettledCreditFacilitiesDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_settledCFDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element settledCFDetails = (Element) nodeList.item(x);
				if(settledCFDetails.hasChildNodes()) {	
					String currency              = (settledCFDetails.getElementsByTagName("Currency").getLength() > 0) ? settledCFDetails.getElementsByTagName("Currency").item(0).getTextContent() : "-";
					String cfType                = (settledCFDetails.getElementsByTagName("CF_Type").getLength() > 0) ? settledCFDetails.getElementsByTagName("CF_Type").item(0).getTextContent() : "-";
					String noOfCfasBorrower      = (settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_BRW").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_BRW").item(0).getTextContent() : null;
					String amtGrantedasBorrower  = (settledCFDetails.getElementsByTagName("Total_Amt_Granted_BRW").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_Amt_Granted_BRW").item(0).getTextContent() : null;
					String noOfCfasGuarantor     = (settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_GRT").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_no_of_credit_facilities_GRT").item(0).getTextContent() : null;
					String amtGrantedasGuarantor = (settledCFDetails.getElementsByTagName("Total_Amt_Granted_GRT").getLength() > 0) ? settledCFDetails.getElementsByTagName("Total_Amt_Granted_GRT").item(0).getTextContent() : null;
					  						
					CorpSettledCreditFacilitiesDetails corpSettledCreditFacilitiesDetails = new CorpSettledCreditFacilitiesDetails();
					corpSettledCreditFacilitiesDetails.setRequestDetailId(requestDetailId);
					corpSettledCreditFacilitiesDetails.setCurrency(currency);
					corpSettledCreditFacilitiesDetails.setCfType(cfType);
					corpSettledCreditFacilitiesDetails.setNoOfCreditFacilitiesAsBorrower(formatInts(noOfCfasBorrower));
					corpSettledCreditFacilitiesDetails.setAmountGrantedAsBorrower(formatAmounts(amtGrantedasBorrower));
					corpSettledCreditFacilitiesDetails.setNoOfCreditFacilitiesAsGuarantor(formatInts(noOfCfasGuarantor));
					corpSettledCreditFacilitiesDetails.setAmountGrantedAsGuarantor(formatAmounts(amtGrantedasGuarantor));
					corpSettledCreditFacilitiesDetails.setIsActive(1);					
					corpSettledCreditFacilitiesDetails.setCreatedBy(username);
					corpSettledCreditFacilitiesDetails.setCreated(created);
					
					corpSettledCreditFacilitiesDetailsList.add(corpSettledCreditFacilitiesDetails);
				}
			}
			return corpSettledCreditFacilitiesDetailsList;
		}		

		//================ Setting Settled Credit Facilities Summary ================//
		public List<CorpSettledCreditFacilitiesSummary> insrtSettledCFSummary(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpSettledCreditFacilitiesSummary> corpSettledCreditFacilitiesSummaryList = new ArrayList<CorpSettledCreditFacilitiesSummary>();
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
					  						
					CorpSettledCreditFacilitiesSummary corpSettledCreditFacilitiesSummary = new CorpSettledCreditFacilitiesSummary();
					corpSettledCreditFacilitiesSummary.setRequestDetailId(requestDetailId);
					corpSettledCreditFacilitiesSummary.setCurrency(currency);
					corpSettledCreditFacilitiesSummary.setOwnership(ownership);
					corpSettledCreditFacilitiesSummary.setNoOfCreditFacilities1(formatInts(noOfCF_1));
					corpSettledCreditFacilitiesSummary.setAmountGranted1(formatAmounts(amtGranted_1));
					corpSettledCreditFacilitiesSummary.setNoOfCreditFacilities2(formatInts(noOfCF_2));
					corpSettledCreditFacilitiesSummary.setAmountGranted2(formatAmounts(amtGranted_2));
					corpSettledCreditFacilitiesSummary.setNoOfCreditFacilities3(formatInts(noOfCF_3));
					corpSettledCreditFacilitiesSummary.setAmountGranted3(formatAmounts(amtGranted_3));
					corpSettledCreditFacilitiesSummary.setNoOfCreditFacilities4(formatInts(noOfCF_4));
					corpSettledCreditFacilitiesSummary.setAmountGranted4(formatAmounts(amtGranted_4));
					corpSettledCreditFacilitiesSummary.setNoOfCreditFacilities5(formatInts(noOfCF_5));
					corpSettledCreditFacilitiesSummary.setAmountGranted5(formatAmounts(amtGranted_5));
					corpSettledCreditFacilitiesSummary.setRp(rp);
					corpSettledCreditFacilitiesSummary.setYear1(year1);
					corpSettledCreditFacilitiesSummary.setYear2(year2);
					corpSettledCreditFacilitiesSummary.setYear3(year3);
					corpSettledCreditFacilitiesSummary.setYear4(year4);
					corpSettledCreditFacilitiesSummary.setYear5(year5);
					corpSettledCreditFacilitiesSummary.setIsColoringNeeded(isColorNeed);
					corpSettledCreditFacilitiesSummary.setIsActive(1);					
					corpSettledCreditFacilitiesSummary.setCreatedBy(username);
					corpSettledCreditFacilitiesSummary.setCreated(created);
					
					corpSettledCreditFacilitiesSummaryList.add(corpSettledCreditFacilitiesSummary);
				}
			}
			return corpSettledCreditFacilitiesSummaryList;
		}
		
		//================ Setting Lending Institutions Inquiries ================//
		public List<CorpLendingInstutionsInquiries> insrtLendingInstInquiries(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpLendingInstutionsInquiries> corpLendingInstutionsInquiriesList = new ArrayList<CorpLendingInstutionsInquiries>();
			NodeList nodeList = (NodeList) xPath.compile(s_lendingInstInquiries).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element lendingInstInquiries = (Element) nodeList.item(x);
				if(lendingInstInquiries.hasChildNodes()) {	
					String sNo         = (lendingInstInquiries.getElementsByTagName("SLNO").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("SLNO").item(0).getTextContent() : null;
					String inquiryDate = (lendingInstInquiries.getElementsByTagName("INQUIRY_DATE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INQUIRY_DATE").item(0).getTextContent() : null;
					String reasonId    = (lendingInstInquiries.getElementsByTagName("REASON_ID").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("REASON_ID").item(0).getTextContent() : "-";
					String reason      = (lendingInstInquiries.getElementsByTagName("REASON").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("REASON").item(0).getTextContent() : "-";
					String instName    = (lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").item(0).getTextContent() : "-";
					String product     = (lendingInstInquiries.getElementsByTagName("PRODUCT_NAME").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("PRODUCT_NAME").item(0).getTextContent() : "-";
					String currency    = (lendingInstInquiries.getElementsByTagName("CURRENCY").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("CURRENCY").item(0).getTextContent() : "-";
					String amount      = (lendingInstInquiries.getElementsByTagName("AMOUNT").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("AMOUNT").item(0).getTextContent() : null;
					String cfType      = (lendingInstInquiries.getElementsByTagName("CF_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("CF_TYPE").item(0).getTextContent() : "-";
					String instCategory= (lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").getLength() > 0) ? lendingInstInquiries.getElementsByTagName("INSTITUTION_TYPE").item(0).getTextContent() : "-";
	  						
					CorpLendingInstutionsInquiries corpLendingInstutionsInquiries = new CorpLendingInstutionsInquiries();
					corpLendingInstutionsInquiries.setRequestDetailId(requestDetailId);
					corpLendingInstutionsInquiries.setSNo(formatInts(sNo));
					corpLendingInstutionsInquiries.setInquiryDate(formatDates(inquiryDate));		
					corpLendingInstutionsInquiries.setReasonId(formatInts(reasonId));
					corpLendingInstutionsInquiries.setReason(reason);
					corpLendingInstutionsInquiries.setInstitutionName(instName);
					corpLendingInstutionsInquiries.setProductName(product);
					corpLendingInstutionsInquiries.setCurrency(currency);
					corpLendingInstutionsInquiries.setAmount(formatAmounts(amount));
					corpLendingInstutionsInquiries.setCfType(cfType);
					corpLendingInstutionsInquiries.setInstituionCategory(instCategory);
					corpLendingInstutionsInquiries.setIsActive(1);					
					corpLendingInstutionsInquiries.setCreatedBy(username);
					corpLendingInstutionsInquiries.setCreated(created);
					
					corpLendingInstutionsInquiriesList.add(corpLendingInstutionsInquiries);
				}
			}
			return corpLendingInstutionsInquiriesList;
		}
		
		//================ Setting Inquiries By Subject ================//
		public List<CorpInquiriesBySubject> insrtInquiriesBySubject(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpInquiriesBySubject> corpInquiriesBySubjectList = new ArrayList<CorpInquiriesBySubject>();
			NodeList nodeList = (NodeList) xPath.compile(s_inqBySubject).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element inqBySubject = (Element) nodeList.item(x);
				if(inqBySubject.hasChildNodes()) {	
					String sNo         = (inqBySubject.getElementsByTagName("SLNO").getLength() > 0) ? inqBySubject.getElementsByTagName("SLNO").item(0).getTextContent() : null;
					String inquiryDate = (inqBySubject.getElementsByTagName("INQUIRY_DATE").getLength() > 0) ? inqBySubject.getElementsByTagName("INQUIRY_DATE").item(0).getTextContent() : null;
					String reasonId    = (inqBySubject.getElementsByTagName("REASON_ID").getLength() > 0) ? inqBySubject.getElementsByTagName("REASON_ID").item(0).getTextContent() : null;
					String reason      = (inqBySubject.getElementsByTagName("REASON").getLength() > 0) ? inqBySubject.getElementsByTagName("REASON").item(0).getTextContent() : "-";				  
					  						
					CorpInquiriesBySubject corpInquiriesBySubject = new CorpInquiriesBySubject();
					corpInquiriesBySubject.setRequestDetailId(requestDetailId);
					corpInquiriesBySubject.setSNo(formatInts(sNo));
					corpInquiriesBySubject.setInquiryDate(formatDates(inquiryDate));
					corpInquiriesBySubject.setReasonId(formatInts(reasonId));
					corpInquiriesBySubject.setReason(reason);					
					corpInquiriesBySubject.setIsActive(1);				
					corpInquiriesBySubject.setCreatedBy(username);
					corpInquiriesBySubject.setCreated(created);
					
					corpInquiriesBySubjectList.add(corpInquiriesBySubject);
				}
			}
			return corpInquiriesBySubjectList;
		}
		
		//================ Setting Credit Facility ================//
		public List<CorpCreditFacility> insrtCreditFacility(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
									    
			List<CorpCreditFacility> corpCreditFacilityList = new ArrayList<CorpCreditFacility>();							
			NodeList nodeList = (NodeList) xPath.compile(s_creditFacility).evaluate(doc, XPathConstants.NODESET);
							
			for(int x=0; x<nodeList.getLength(); x++) {
										  			
				Element creditFacility = (Element) nodeList.item(x);
				if(creditFacility.hasChildNodes()) {	
					String relationId   = (creditFacility.getElementsByTagName("relation_id").getLength() > 0) ? creditFacility.getElementsByTagName("relation_id").item(0).getTextContent() : null;
					String serialNumber = (creditFacility.getElementsByTagName("SerialNumber").getLength() > 0) ? creditFacility.getElementsByTagName("SerialNumber").item(0).getTextContent() : null;					
																
					CorpCreditFacility corpCreditFacility = new CorpCreditFacility();
					corpCreditFacility.setRequestDetailId(requestDetailId);
					corpCreditFacility.setRelationId(formatInts(relationId));
					corpCreditFacility.setSerialNumber(formatInts(serialNumber));
					corpCreditFacility.setIsActive(1);					
					corpCreditFacility.setCreatedBy(username);
					corpCreditFacility.setCreated(created);
					
					corpCreditFacilityList.add(corpCreditFacility);
				}
			}
			return corpCreditFacilityList;
		}
		
		//================ Setting Credit Facility Details ================//		
		public List<CorpCreditFacilityDetails> insrtCreditFacilityDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpCreditFacilityDetails> corpCreditFacilityDetailsList = new ArrayList<CorpCreditFacilityDetails>();
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
					String guaranteeCoverage  = (cFDetails.getElementsByTagName("BUREAU_GUARANTOR_COVERAGE").getLength() > 0) ? cFDetails.getElementsByTagName("BUREAU_GUARANTOR_COVERAGE").item(0).getTextContent() : "-";
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
					  						
					CorpCreditFacilityDetails corpCreditFacilityDetails = new CorpCreditFacilityDetails();
					corpCreditFacilityDetails.setRequestDetailId(requestDetailId);
					corpCreditFacilityDetails.setSNo(formatInts(sNo));
					corpCreditFacilityDetails.setInstitutionCategory(institutionCatg);
					corpCreditFacilityDetails.setInstitutionBranch(institutionBranch);
					corpCreditFacilityDetails.setCfType(cfType);
					corpCreditFacilityDetails.setCfStatus(cfStatus);
					corpCreditFacilityDetails.setOwnership(ownership);
					corpCreditFacilityDetails.setCurrency(currency);
					corpCreditFacilityDetails.setAmountGranted(formatAmounts(amtGranted));
					corpCreditFacilityDetails.setCurrentBalance(formatAmounts(currentBalance));
					corpCreditFacilityDetails.setArrearsAmount(formatAmounts(arrearsAmt));
					corpCreditFacilityDetails.setInstallmentAmount(formatAmounts(installmentAmt));
					corpCreditFacilityDetails.setAmountWrittenOff(formatAmounts(amtWrittenOff));
					corpCreditFacilityDetails.setReportedDate(formatDates(reportedDate));
					corpCreditFacilityDetails.setFirstDisburseDate(formatDates(firstDisburseDate));
					corpCreditFacilityDetails.setLatestPaymentDate(formatDates(lastPaymentDate));
					corpCreditFacilityDetails.setRestructuringDate(formatDates(restructuringDate));
					corpCreditFacilityDetails.setEndDate(formatDates(endDate));
					corpCreditFacilityDetails.setRepayType(repayType);
					corpCreditFacilityDetails.setPurpose(purpose);
					corpCreditFacilityDetails.setCoverage(coverage);				
					corpCreditFacilityDetails.setRelationId(formatInts(relationId));
					corpCreditFacilityDetails.setAccountStatus(accountStatus);
					corpCreditFacilityDetails.setDispute(dispute);
					corpCreditFacilityDetails.setBureauGuaranteeCoverage(guaranteeCoverage);
					corpCreditFacilityDetails.setBureauSecurityCoverage(securityCoverage);
					corpCreditFacilityDetails.setInterestOutstanding(formatAmounts(interestOutstnding));
					corpCreditFacilityDetails.setMaxNumDaysDue(formatInts(numDaysDue));
					corpCreditFacilityDetails.setLoanType(loanType);
					corpCreditFacilityDetails.setSanctionDate(formatDates(sanctionDate));
					corpCreditFacilityDetails.setOwnershipIndicator(ownershipIndicator);
					corpCreditFacilityDetails.setRepaymentType(repaymentType);
					corpCreditFacilityDetails.setPriority(formatInts(priority));
					corpCreditFacilityDetails.setPriority2(formatInts(priority2));
					corpCreditFacilityDetails.setLegalAction(legalAction);
					corpCreditFacilityDetails.setNumberOfInstallments(formatInts(numOfInstallments));
					corpCreditFacilityDetails.setPrimaryRootId(formatLongs(primaryRoot));
					corpCreditFacilityDetails.setActiveRootId(formatLongs(activeRoot));
					corpCreditFacilityDetails.setRuId(formatLongs(ruId));
					corpCreditFacilityDetails.setProviderBranch(providerBranch);
					corpCreditFacilityDetails.setProviderSource(providerSource);
					corpCreditFacilityDetails.setCategoryDesc(categoryDesc);
					corpCreditFacilityDetails.setSiInstName(siInstName);
					corpCreditFacilityDetails.setSiBrnhName(siBranchName);
					corpCreditFacilityDetails.setDisputeId(disputeId);
					corpCreditFacilityDetails.setRank(formatInts(rank));
					corpCreditFacilityDetails.setRowNum(formatInts(rowNum));
					corpCreditFacilityDetails.setSecurType(securType);
					corpCreditFacilityDetails.setBlockFlag(formatShorts(blockFlag));
					corpCreditFacilityDetails.setIsActive(1);					
					corpCreditFacilityDetails.setCreatedBy(username);
					corpCreditFacilityDetails.setCreated(created);
					
					corpCreditFacilityDetailsList.add(corpCreditFacilityDetails);
				}
			}
			return corpCreditFacilityDetailsList;
		}
		
		//================ Last 24 Months ================//
		public List<CorpLast24Months> insrtLast24Months(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			  
			List<CorpLast24Months> corpLast24MonthsList = new ArrayList<CorpLast24Months>();
			NodeList nodeList = (NodeList) xPath.compile(s_last24Months).evaluate(doc, XPathConstants.NODESET);

			for(int x=0; x<nodeList.getLength(); x++) {	
				
				Element last24Months = (Element) nodeList.item(x);				
				if(last24Months.hasChildNodes()) {	
					String relationId      = (last24Months.getElementsByTagName("RELATION_ID").getLength() > 0) ? last24Months.getElementsByTagName("RELATION_ID").item(0).getTextContent() : null;
					String sNo             = (last24Months.getElementsByTagName("SNO").getLength() > 0) ? last24Months.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String rowNum          = (last24Months.getElementsByTagName("ROWNUM").getLength() > 0) ? last24Months.getElementsByTagName("ROWNUM").item(0).getTextContent() : null;
					String fromMonthYear   = (last24Months.getElementsByTagName("FROM_MONTH_YEAR").getLength() > 0) ? last24Months.getElementsByTagName("FROM_MONTH_YEAR").item(0).getTextContent() : "-";
					String toMonthYear     = (last24Months.getElementsByTagName("TO_MONTH_YEAR").getLength() > 0) ? last24Months.getElementsByTagName("TO_MONTH_YEAR").item(0).getTextContent() : "-";
					String activeRootId    = (last24Months.getElementsByTagName("ACTIVE_ROOT_ID").getLength() > 0) ? last24Months.getElementsByTagName("ACTIVE_ROOT_ID").item(0).getTextContent() : null;
					String bureauCurrency  = (last24Months.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? last24Months.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
					String bureauAccStatus = (last24Months.getElementsByTagName("BUREAU_ACC_STATUS").getLength() > 0) ? last24Months.getElementsByTagName("BUREAU_ACC_STATUS").item(0).getTextContent() : "-";
					String rank            = (last24Months.getElementsByTagName("RANK").getLength() > 0) ? last24Months.getElementsByTagName("RANK").item(0).getTextContent() : null;															
									    
				    CorpLast24Months corpLast24Months = new CorpLast24Months();
				    corpLast24Months.setRequestDetailId(requestDetailId);
				    corpLast24Months.setRelationId(formatInts(relationId));
				    corpLast24Months.setSNo(formatInts(sNo));
				    corpLast24Months.setRowNum(formatInts(rowNum));				    
				    corpLast24Months.setToMonthYear(toMonthYear);
				    corpLast24Months.setFromMonthYear(fromMonthYear);
				    corpLast24Months.setActiveRootId(formatLongs(activeRootId));
				    corpLast24Months.setBureauCurrency(bureauCurrency);
				    corpLast24Months.setBureauAccStatus(bureauAccStatus);
				    corpLast24Months.setRank(formatInts(rank));
				    corpLast24Months.setIsActive(1);			    
				    corpLast24Months.setCreatedBy(username);
				    corpLast24Months.setCreated(created);
				    
				    corpLast24MonthsList.add(corpLast24Months);
				}			
			}
			return corpLast24MonthsList;
		}
		
		//================ Setting Credit Facility For Last 24 Months ================//
		public List<CorpCreditFacilityDetailsLast24Months> insrtCFForLast24Months(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpCreditFacilityDetailsLast24Months> corpCreditFacilityDetailsLast24MonthsList = new ArrayList<CorpCreditFacilityDetailsLast24Months>();
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
					String accountStatus       = (cfFor24Months.getElementsByTagName("ACCOUNT_STATUS").getLength() > 0) ? cfFor24Months.getElementsByTagName("ACCOUNT_STATUS").item(0).getTextContent() : "-"; 
					
					CorpCreditFacilityDetailsLast24Months corpCreditFacilityDetailsLast24Months = new CorpCreditFacilityDetailsLast24Months();
					corpCreditFacilityDetailsLast24Months.setRequestDetailId(requestDetailId);
					corpCreditFacilityDetailsLast24Months.setActiveRootId(formatLongs(activeRootId));
					corpCreditFacilityDetailsLast24Months.setToMonthYear(toMonthYear);
					corpCreditFacilityDetailsLast24Months.setFromMonthYear(fromMonthYear);
					corpCreditFacilityDetailsLast24Months.setMonth(month);
					corpCreditFacilityDetailsLast24Months.setCurrentBalance(formatAmounts(currentBalance));
					corpCreditFacilityDetailsLast24Months.setAmountOverdue(formatAmounts(amountOverdue));
					corpCreditFacilityDetailsLast24Months.setAssetClassification(assetClassification);
					corpCreditFacilityDetailsLast24Months.setMaximumNumberOfDaysOverdue(maxDaysOverdue);
					corpCreditFacilityDetailsLast24Months.setAccountStatus(accountStatus);
					corpCreditFacilityDetailsLast24Months.setIsActive(1);				
					corpCreditFacilityDetailsLast24Months.setCreatedBy(username);
					corpCreditFacilityDetailsLast24Months.setCreated(created);
					
					corpCreditFacilityDetailsLast24MonthsList.add(corpCreditFacilityDetailsLast24Months);
				}
			}
			return corpCreditFacilityDetailsLast24MonthsList;
		}				
		
		//================ Setting Dispute Details ================//
		public List<CorpDisputeDetails> insrtDisputeDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpDisputeDetails> corpDisputeDetailsList = new ArrayList<CorpDisputeDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_disputeDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element disputeDetails = (Element) nodeList.item(x);
				if(disputeDetails.hasChildNodes()) {	
					String description = (disputeDetails.getElementsByTagName("DISPUTE_DETAILS").getLength() > 0) ? disputeDetails.getElementsByTagName("DISPUTE_DETAILS").item(0).getTextContent() : "-";			 
					
					CorpDisputeDetails corpDisputeDetails = new CorpDisputeDetails();
					corpDisputeDetails.setRequestDetailId(requestDetailId);
					corpDisputeDetails.setDescription(description);
					corpDisputeDetails.setIsActive(1);					
					corpDisputeDetails.setCreatedBy(username);
					corpDisputeDetails.setCreated(created);
					
					corpDisputeDetailsList.add(corpDisputeDetails);
				}
			}
			return corpDisputeDetailsList;
		}
		
		//================ Setting Potential And Current Liabilities Header ================//
		public List<CorpPotentialAndCurrentLiabilitiesHeader> insrtPotAndCurrLiabilitiesHeader(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
									    
			List<CorpPotentialAndCurrentLiabilitiesHeader> corpPotentialAndCurrentLiabilitiesHeaderList = new ArrayList<CorpPotentialAndCurrentLiabilitiesHeader>();						
			NodeList nodeList = (NodeList) xPath.compile(s_potAndCurrLiabilitiesHeader).evaluate(doc, XPathConstants.NODESET);
							
			for(int x=0; x<nodeList.getLength(); x++) {
										  			
				Element potAndCurrLiabilitiesHeader = (Element) nodeList.item(x);
				if(potAndCurrLiabilitiesHeader.hasChildNodes()) {	
					String currency  = (potAndCurrLiabilitiesHeader.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
					String priority  = (potAndCurrLiabilitiesHeader.getElementsByTagName("PRIORITY").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("PRIORITY").item(0).getTextContent() : null;	
					String monthYear = (potAndCurrLiabilitiesHeader.getElementsByTagName("MONTHYEAR").getLength() > 0) ? potAndCurrLiabilitiesHeader.getElementsByTagName("MONTHYEAR").item(0).getTextContent() : "-";
					
					CorpPotentialAndCurrentLiabilitiesHeader corpPotentialAndCurrentLiabilitiesHeader = new CorpPotentialAndCurrentLiabilitiesHeader();
					corpPotentialAndCurrentLiabilitiesHeader.setRequestDetailId(requestDetailId);
					corpPotentialAndCurrentLiabilitiesHeader.setBureauCurrency(currency);
					corpPotentialAndCurrentLiabilitiesHeader.setPriority(formatInts(priority));
					corpPotentialAndCurrentLiabilitiesHeader.setMonthyear(monthYear);
					corpPotentialAndCurrentLiabilitiesHeader.setIsActive(1);					
					corpPotentialAndCurrentLiabilitiesHeader.setCreatedBy(username);
					corpPotentialAndCurrentLiabilitiesHeader.setCreated(created);
					
					corpPotentialAndCurrentLiabilitiesHeaderList.add(corpPotentialAndCurrentLiabilitiesHeader);
				}
			}
			return corpPotentialAndCurrentLiabilitiesHeaderList;
		}
		
		//================ Setting Potential And Current Liabilities ================//
		public List<CorpPotentialAndCurrentLiabilities> insrtPotAndCurrLiabilities(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpPotentialAndCurrentLiabilities> corpPotentialAndCurrentLiabilitiesList = new ArrayList<CorpPotentialAndCurrentLiabilities>();
			NodeList nodeList = (NodeList) xPath.compile(s_potAndCurrLiabilities).evaluate(doc, XPathConstants.NODESET);
	
				for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element potAndCurrLiabilities = (Element) nodeList.item(x);
				if(potAndCurrLiabilities.hasChildNodes()) {	
					String ownership        = (potAndCurrLiabilities.getElementsByTagName("OWNERSHIP_TYPE").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("OWNERSHIP_TYPE").item(0).getTextContent() : "-";
					String numOfacilities   = (potAndCurrLiabilities.getElementsByTagName("TOTAL_NO_OF_CREDITFACILITIES").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("TOTAL_NO_OF_CREDITFACILITIES").item(0).getTextContent() : null;
					String grantedAmt       = (potAndCurrLiabilities.getElementsByTagName("SANCTIONED_AMOUNT").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("SANCTIONED_AMOUNT").item(0).getTextContent() : null;
					String totalOutstanding = (potAndCurrLiabilities.getElementsByTagName("TOTAL_OUTSTANDING").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("TOTAL_OUTSTANDING").item(0).getTextContent() : null;
					String cfStatus         = (potAndCurrLiabilities.getElementsByTagName("CREDIT_FACILITY_STATUS").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("CREDIT_FACILITY_STATUS").item(0).getTextContent() : "-";
					String currency         = (potAndCurrLiabilities.getElementsByTagName("BUREAU_CURRENCY").getLength() > 0) ? potAndCurrLiabilities.getElementsByTagName("BUREAU_CURRENCY").item(0).getTextContent() : "-";
				  						
					CorpPotentialAndCurrentLiabilities corpPotentialAndCurrentLiabilities = new CorpPotentialAndCurrentLiabilities();
					corpPotentialAndCurrentLiabilities.setRequestDetailId(requestDetailId);					
					corpPotentialAndCurrentLiabilities.setOwnership(ownership);
					corpPotentialAndCurrentLiabilities.setNoOfCreditFacilities(formatInts(numOfacilities));
					corpPotentialAndCurrentLiabilities.setTotalAmountGranted(formatAmounts(grantedAmt));
					corpPotentialAndCurrentLiabilities.setTotalOutstanding(formatAmounts(totalOutstanding));
					corpPotentialAndCurrentLiabilities.setCreditFacilityStatus(cfStatus);
					corpPotentialAndCurrentLiabilities.setBureauCurrency(currency);
					corpPotentialAndCurrentLiabilities.setIsActive(1);					
					corpPotentialAndCurrentLiabilities.setCreatedBy(username);
					corpPotentialAndCurrentLiabilities.setCreated(created);
					
					corpPotentialAndCurrentLiabilitiesList.add(corpPotentialAndCurrentLiabilities);
				}
			}
			return corpPotentialAndCurrentLiabilitiesList;
		}
		
		//================ Setting Credit Facilities Of Glance Status ================//
		public List<CorpCreditFacilityOfGlanceStatus> insrtCFOfGlanceStatus(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpCreditFacilityOfGlanceStatus> corpCreditFacilityOfGlanceStatusList = new ArrayList<CorpCreditFacilityOfGlanceStatus>();
			NodeList nodeList = (NodeList) xPath.compile(s_cFOfGlanceStatus).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element cFOfGlanceStatus = (Element) nodeList.item(x);
				if(cFOfGlanceStatus.hasChildNodes()) {	
					String catalogueCode    = (cFOfGlanceStatus.getElementsByTagName("CATALOGUE_CODE").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("CATALOGUE_CODE").item(0).getTextContent() : "-";
					String catalogueVal     = (cFOfGlanceStatus.getElementsByTagName("CATALOGUE_VAL_ENGLISH").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("CATALOGUE_VAL_ENGLISH").item(0).getTextContent() : "-";
					String status           = (cFOfGlanceStatus.getElementsByTagName("BUREAU_ACC_STATUS").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("BUREAU_ACC_STATUS").item(0).getTextContent() : "-";
					String arreasDays0      = (cFOfGlanceStatus.getElementsByTagName("ZERO").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("ZERO").item(0).getTextContent() : null;
					String arreasDays1_30   = (cFOfGlanceStatus.getElementsByTagName("ONETO30").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("ONETO30").item(0).getTextContent() : null;
					String arreasDays31_60  = (cFOfGlanceStatus.getElementsByTagName("THIRTYONETO60").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("THIRTYONETO60").item(0).getTextContent() : null;
					String arreasDays61_90  = (cFOfGlanceStatus.getElementsByTagName("SIXTYONETO90").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("SIXTYONETO90").item(0).getTextContent() : null;
					String arreasDaysOver90 = (cFOfGlanceStatus.getElementsByTagName("NINETY").getLength() > 0) ? cFOfGlanceStatus.getElementsByTagName("NINETY").item(0).getTextContent() : null;
					
					CorpCreditFacilityOfGlanceStatus corpCreditFacilityOfGlanceStatus = new CorpCreditFacilityOfGlanceStatus();
					corpCreditFacilityOfGlanceStatus.setRequestDetailId(requestDetailId);
					corpCreditFacilityOfGlanceStatus.setCatalogueCode(catalogueCode);
					corpCreditFacilityOfGlanceStatus.setCatalogueValEnglish(catalogueVal);
					corpCreditFacilityOfGlanceStatus.setStatus(status);
					corpCreditFacilityOfGlanceStatus.setArrearsDays0(formatInts(arreasDays0));
					corpCreditFacilityOfGlanceStatus.setArrearsDays1_30(formatInts(arreasDays1_30));
					corpCreditFacilityOfGlanceStatus.setArrearsDays31_60(formatInts(arreasDays31_60));
					corpCreditFacilityOfGlanceStatus.setArrearsDays61_90(formatInts(arreasDays61_90));
					corpCreditFacilityOfGlanceStatus.setArrearsDays90(formatInts(arreasDaysOver90));
					corpCreditFacilityOfGlanceStatus.setIsActive(1);					
					corpCreditFacilityOfGlanceStatus.setCreatedBy(username);
					corpCreditFacilityOfGlanceStatus.setCreated(created);
					
					corpCreditFacilityOfGlanceStatusList.add(corpCreditFacilityOfGlanceStatus);
				}
			}
			return corpCreditFacilityOfGlanceStatusList;
		}
		
		//================ Setting Dishonoured Cheque Summary ================//
		public List<CorpDishonouredChequeSummary> insrtDishonChequeSummary(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpDishonouredChequeSummary> corpDishonouredChequeSummaryList = new ArrayList<CorpDishonouredChequeSummary>();
			NodeList nodeList = (NodeList) xPath.compile(s_dishonChequeSummary).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element dishonChequeSummary = (Element) nodeList.item(x);
				if(dishonChequeSummary.hasChildNodes()) {	
					String numberOfCheques = (dishonChequeSummary.getElementsByTagName("NO_OF_DC").getLength() > 0) ? dishonChequeSummary.getElementsByTagName("NO_OF_DC").item(0).getTextContent() : null;
					String chequeValue     = (dishonChequeSummary.getElementsByTagName("TOT_AMT_OF_DC").getLength() > 0) ? dishonChequeSummary.getElementsByTagName("TOT_AMT_OF_DC").item(0).getTextContent() : null;		  	
					
					CorpDishonouredChequeSummary corpDishonouredChequeSummary = new CorpDishonouredChequeSummary();
					corpDishonouredChequeSummary.setRequestDetailId(requestDetailId);
					corpDishonouredChequeSummary.setNumberOfCheques(formatInts(numberOfCheques));
					corpDishonouredChequeSummary.setChequeValue(formatAmounts(chequeValue));
					corpDishonouredChequeSummary.setIsActive(1);					
					corpDishonouredChequeSummary.setCreatedBy(username);
					corpDishonouredChequeSummary.setCreated(created);
					
					corpDishonouredChequeSummaryList.add(corpDishonouredChequeSummary);
				}
			}
			return corpDishonouredChequeSummaryList;
		}
		
		//================ Setting Dishonoured Cheque Details ================//
		public List<CorpDishonouredChequeDetails> insrtDishonChequeDetails(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpDishonouredChequeDetails> corpDishonouredChequeDetailsList = new ArrayList<CorpDishonouredChequeDetails>();
			NodeList nodeList = (NodeList) xPath.compile(s_dishonChequeDetails).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element dishonChequeDetails = (Element) nodeList.item(x);
				if(dishonChequeDetails.hasChildNodes()) {
					String sno 			 = (dishonChequeDetails.getElementsByTagName("SNO").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("SNO").item(0).getTextContent() : null;
					String instId        = (dishonChequeDetails.getElementsByTagName("INSTITUTION_ID").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("INSTITUTION_ID").item(0).getTextContent() : "-";
					String instAndBranch = (dishonChequeDetails.getElementsByTagName("INSTBRANCH").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("INSTBRANCH").item(0).getTextContent() : "-";
					String accountNo 	 = (dishonChequeDetails.getElementsByTagName("ACCOUNT_NUMBER").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("ACCOUNT_NUMBER").item(0).getTextContent() : "-";	
					String chequeNumber  = (dishonChequeDetails.getElementsByTagName("CHEQUE_NUMBER").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("CHEQUE_NUMBER").item(0).getTextContent() : "-";
					String chequeAmount  = (dishonChequeDetails.getElementsByTagName("CHEQUE_AMOUNT").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("CHEQUE_AMOUNT").item(0).getTextContent() : null;
					String disHonDate    = (dishonChequeDetails.getElementsByTagName("DATE_DISHONOURED").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("DATE_DISHONOURED").item(0).getTextContent() : null;
					String reason        = (dishonChequeDetails.getElementsByTagName("REASON_FOR_DISHONOUR").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("REASON_FOR_DISHONOUR").item(0).getTextContent() : "-";
					String blockFlag     = (dishonChequeDetails.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? dishonChequeDetails.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					  						
					CorpDishonouredChequeDetails corpDishonouredChequeDetails = new CorpDishonouredChequeDetails();
					corpDishonouredChequeDetails.setRequestDetailId(requestDetailId);
					corpDishonouredChequeDetails.setSNo(formatInts(sno));
					corpDishonouredChequeDetails.setInstitutionId(instId);
					corpDishonouredChequeDetails.setInstitutionAndBranch(instAndBranch);
					corpDishonouredChequeDetails.setAccountNumber(accountNo);
					corpDishonouredChequeDetails.setChequeNumber(chequeNumber);
					corpDishonouredChequeDetails.setChequeAmount(formatAmounts(chequeAmount));
					corpDishonouredChequeDetails.setDateDishonoured(formatDates(disHonDate));
					corpDishonouredChequeDetails.setReason(reason);					
					corpDishonouredChequeDetails.setBlockFlag(formatShorts(blockFlag));
					corpDishonouredChequeDetails.setIsActive(1);					
					corpDishonouredChequeDetails.setCreatedBy(username);
					corpDishonouredChequeDetails.setCreated(created);
					
					corpDishonouredChequeDetailsList.add(corpDishonouredChequeDetails);
				}
			}
			return corpDishonouredChequeDetailsList;
		}

		//================ Setting Economic Activity History ================//
		public List<CorpEconomicActivityHistory> insrtEconActivityHistory(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpEconomicActivityHistory> CorpEconomicActivityHistoryList = new ArrayList<CorpEconomicActivityHistory>();
			NodeList nodeList = (NodeList) xPath.compile(s_econActivityHistory).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element econActivityHistory = (Element) nodeList.item(x);
				if(econActivityHistory.hasChildNodes()) {	
					String sNo          = (econActivityHistory.getElementsByTagName("SLNO").getLength() > 0) ? econActivityHistory.getElementsByTagName("SLNO").item(0).getTextContent() : null;
					String econActivity = (econActivityHistory.getElementsByTagName("ECONOMIC_ACTIVITY").getLength() > 0) ? econActivityHistory.getElementsByTagName("ECONOMIC_ACTIVITY").item(0).getTextContent() : "-";
					String reportedDate = (econActivityHistory.getElementsByTagName("LAST_REPORTED_DATE").getLength() > 0) ? econActivityHistory.getElementsByTagName("LAST_REPORTED_DATE").item(0).getTextContent() : null;
					String blockFlag    = (econActivityHistory.getElementsByTagName("BLOCK_FLAG").getLength() > 0) ? econActivityHistory.getElementsByTagName("BLOCK_FLAG").item(0).getTextContent() : null;
					String ruid         = (econActivityHistory.getElementsByTagName("RUID").getLength() > 0) ? econActivityHistory.getElementsByTagName("RUID").item(0).getTextContent() : null;
					  						
					CorpEconomicActivityHistory corpEconomicActivityHistory = new CorpEconomicActivityHistory();
					corpEconomicActivityHistory.setRequestDetailId(requestDetailId);
					corpEconomicActivityHistory.setSNo(formatInts(sNo));
					corpEconomicActivityHistory.setEconomicActivity(econActivity);
					corpEconomicActivityHistory.setReportedDate(formatDates(reportedDate));
					corpEconomicActivityHistory.setBlockFlag(formatShorts(blockFlag));
					corpEconomicActivityHistory.setRuId(formatLongs(ruid));
					corpEconomicActivityHistory.setIsActive(1);					
					corpEconomicActivityHistory.setCreatedBy(username);
					corpEconomicActivityHistory.setCreated(created);
					
					CorpEconomicActivityHistoryList.add(corpEconomicActivityHistory);
				}
			}
			return CorpEconomicActivityHistoryList;
		}
		
		//================ Setting Catalogue Description ================//
		public List<CorpCatalogueDescription> insrtCatalogueDescription(Document doc, Long requestDetailId, String username) throws XPathExpressionException {
			    
			List<CorpCatalogueDescription> corpCatalogueDescriptionList = new ArrayList<CorpCatalogueDescription>();
			NodeList nodeList = (NodeList) xPath.compile(s_catalogueDescription).evaluate(doc, XPathConstants.NODESET);
	
			for(int x=0; x<nodeList.getLength(); x++) {
				  			
				Element catalogueDescription = (Element) nodeList.item(x);
				if(catalogueDescription.hasChildNodes()) {	
					String sno 	 = (catalogueDescription.getElementsByTagName("SerialNumber").getLength() > 0) ? catalogueDescription.getElementsByTagName("SerialNumber").item(0).getTextContent() : null;
					String label = (catalogueDescription.getElementsByTagName("Label").getLength() > 0) ? catalogueDescription.getElementsByTagName("Label").item(0).getTextContent() : "-";
					String value = (catalogueDescription.getElementsByTagName("Values").getLength() > 0) ? catalogueDescription.getElementsByTagName("Values").item(0).getTextContent().replace("'", "`") : "-";
					  						
					CorpCatalogueDescription corpCatalogueDescription = new CorpCatalogueDescription();
					corpCatalogueDescription.setRequestDetailId(requestDetailId);
					corpCatalogueDescription.setSNo(formatInts(sno));
					corpCatalogueDescription.setCatgLabel(label);
					corpCatalogueDescription.setCatgValue(value);
					corpCatalogueDescription.setIsActive(1);					
					corpCatalogueDescription.setCreatedBy(username);
					corpCatalogueDescription.setCreated(created);
					
					corpCatalogueDescriptionList.add(corpCatalogueDescription);
				}
			}
			return corpCatalogueDescriptionList;
		}
		
		//================ Setting Search Results ================//
		public List<CorpSearchResults> insrtSearchResults(Document doc, Long requestDetailId, String username) {
			    
			List<CorpSearchResults> corpSearchResultsList = new ArrayList<CorpSearchResults>();
			for(int x=0; x<doc.getElementsByTagName("SEARCH-RESULT-ITEM").getLength(); x++) {
					
				Element searchResultItem = (Element) doc.getElementsByTagName("SEARCH-RESULT-ITEM").item(x);
				String name = searchResultItem.getAttribute("NAME");
				String bureauId = searchResultItem.getAttribute("BUREAU-ID");
						
				if(searchResultItem.hasChildNodes()) {
					Element identifier = (Element) searchResultItem.getElementsByTagName("IDENTIFIER").item(0);
					String identifierSource = identifier.getAttribute("IDSOURCE");
					String identifierValue = identifier.getAttribute("IDVALUE");
					String identifierMatched = identifier.getAttribute("MATCHED");
											
					CorpSearchResults corpSearchResults = new CorpSearchResults();
					corpSearchResults.setRequestDetailId(requestDetailId);
					corpSearchResults.setName(name);
					corpSearchResults.setBureauId(bureauId);
					corpSearchResults.setIdentifierIdSource(identifierSource);
					corpSearchResults.setIdentifierValue(identifierValue);
					corpSearchResults.setIdentifierMatched(identifierMatched);
					corpSearchResults.setIsActive(1);					
					corpSearchResults.setCreatedBy(username);
					corpSearchResults.setCreated(created);
					
					corpSearchResultsList.add(corpSearchResults);
				}		
			}
			return corpSearchResultsList;
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
