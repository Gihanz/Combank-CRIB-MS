package com.nable.crib.pdf.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nable.crib.pdf.bean.crib.CFForLast24Months;
import com.nable.crib.pdf.bean.crib.CFOfGlanceStatus;
import com.nable.crib.pdf.bean.crib.CatalogueDescription;
import com.nable.crib.pdf.bean.crib.CorporateCribObject;
import com.nable.crib.pdf.bean.crib.CreditFacilityDetails;
import com.nable.crib.pdf.bean.crib.DishonChequeDetails;
import com.nable.crib.pdf.bean.crib.DishonChequeSummary;
import com.nable.crib.pdf.bean.crib.DisputeDetails;
import com.nable.crib.pdf.bean.crib.EconActivityHistory;
import com.nable.crib.pdf.bean.crib.FirmographicDetails;
import com.nable.crib.pdf.bean.crib.InquiriesBySubject;
import com.nable.crib.pdf.bean.crib.Last24Months;
import com.nable.crib.pdf.bean.crib.Last5Years;
import com.nable.crib.pdf.bean.crib.LendingInstInquiries;
import com.nable.crib.pdf.bean.crib.MailingAddresses;
import com.nable.crib.pdf.bean.crib.PermanentAddresses;
import com.nable.crib.pdf.bean.crib.PotAndCurrLiabilities;
import com.nable.crib.pdf.bean.crib.RelationshipDetails;
import com.nable.crib.pdf.bean.crib.ReportedNames;
import com.nable.crib.pdf.bean.crib.RequestDetail;
import com.nable.crib.pdf.bean.crib.RequestMain;
import com.nable.crib.pdf.bean.crib.RequestMaster;
import com.nable.crib.pdf.bean.crib.SettledCFDetails;
import com.nable.crib.pdf.bean.crib.SettledCFSummary;

@Component
public class GetCorporateCribData {
	
	static Logger log = LoggerFactory.getLogger(GetCorporateCribData.class);
	
	@Autowired
	CorporateMicroservice corporateMS;
	
	@Autowired
	CommonUtilsMicroservice comnUtilsMS;
	
	RequestMain requestMain;
	RequestMaster requestMaster;
	RequestDetail requestDetail;
	FirmographicDetails firmographicDetails;
	List<MailingAddresses> mailingAddressesList;
	List<PermanentAddresses> permanentAddressesList;
	List<ReportedNames> reportedNamesList;
	List<RelationshipDetails> relationshipDetailsList;
	List<SettledCFDetails> settledCFDetailsList;
	List<SettledCFSummary> settledCFSummaryList;
	List<LendingInstInquiries> lendingInstInquiriesList;
	List<InquiriesBySubject> inquiriesBySubjectList;
	List<CreditFacilityDetails> creditFacilityDetailsList;
	List<CFForLast24Months> cFForLast24MonthsList;
	List<DisputeDetails> disputeDetailsList;
	List<PotAndCurrLiabilities> potAndCurrLiabilitiesList;
	List<CFOfGlanceStatus> cFOfGlanceStatusList;
	List<DishonChequeSummary> dishonChequeSummaryList;
	List<DishonChequeDetails> dishonChequeDetailsList;
	List<EconActivityHistory> econActivityHistoryList;
	List<CatalogueDescription> catalogueDescriptionList;
	Last24Months last24Months;
	Last5Years last5Years;
		
	// Get corporate data
	public CorporateCribObject getCorporateCribDataByDetailId(Long requestDetailId) throws Exception {
	 	
		CorporateCribObject corporateObj = new CorporateCribObject();
		
		JSONObject requestDetailData = comnUtilsMS.getRequestDetail(requestDetailId).getJSONObject("requestDetail");
		JSONObject requestMasterData = comnUtilsMS.getRequestMaster(requestDetailData.getLong("requestMasterId")).getJSONObject("requestMaster");
		JSONObject requestMainData = comnUtilsMS.getRequestMain(requestMasterData.getLong("requestMainId")).getJSONObject("requestMain");
		
		JSONObject corpCribData = corporateMS.getCorporateCribData(requestDetailId);
		JSONObject corpFirmographicDetailsData = corpCribData.getJSONObject("corpFirmographicDetails");
		JSONArray corpMailingAddressesDataList = corpCribData.getJSONArray("corpMailingAddressesList");
		JSONArray corpPermanentAddressesDataList = corpCribData.getJSONArray("corpPermanentAddressesList");	
		JSONArray corpReportedNamesDataList = corpCribData.getJSONArray("corpReportedNamesList");
		JSONArray corpRelationshipDetailsDataList = corpCribData.getJSONArray("corpRelationshipDetailsList");
		JSONArray corpSettledCreditFacilitiesDetailsDataList = corpCribData.getJSONArray("corpSettledCreditFacilitiesDetailsList");
		JSONArray corpSettledCreditFacilitiesSummaryDataList = corpCribData.getJSONArray("corpSettledCreditFacilitiesSummaryList");
		JSONArray corpLendingInstutionsInquiriesDataList = corpCribData.getJSONArray("corpLendingInstutionsInquiriesList");
		JSONArray corpInquiriesBySubjectDataList = corpCribData.getJSONArray("corpInquiriesBySubjectList");
		JSONArray corpCreditFacilityDetailsDataList = corpCribData.getJSONArray("corpCreditFacilityDetailsList");
		JSONArray corpCreditFacilityDetailsLast24MonthsDataList = corpCribData.getJSONArray("corpCreditFacilityDetailsLast24MonthsList");
		JSONArray corpDisputeDetailsDataList = corpCribData.getJSONArray("corpDisputeDetailsList");
		JSONArray corpPotentialAndCurrentLiabilitiesDataList = corpCribData.getJSONArray("corpPotentialAndCurrentLiabilitiesList");
		JSONArray corpCreditFacilityOfGlanceStatusDataList = corpCribData.getJSONArray("corpCreditFacilityOfGlanceStatusList");
		JSONArray corpDishonouredChequeSummaryDataList = corpCribData.getJSONArray("corpDishonouredChequeSummaryList");
		JSONArray corpDishonouredChequeDetailsDataList = corpCribData.getJSONArray("corpDishonouredChequeDetailsList");
		JSONArray corpEconomicActivityHistoryDataList = corpCribData.getJSONArray("corpEconomicActivityHistoryList");
		JSONArray corpCatalogueDescriptionDataList = corpCribData.getJSONArray("corpCatalogueDescriptionList");
		
		if(requestDetailData != null) {
			requestDetail = new RequestDetail();
			requestDetail.setReportNumber(formatValue(requestDetailData.get("reportId"), "NUMBER"));
			requestDetail.setReportOrderDate(formatValue(requestDetailData.get("created"), "DATE"));
			corporateObj.setRequestDetail(requestDetail);
		}
		
		if(requestMasterData != null) {
			requestMaster = new RequestMaster();
			requestMaster.setProductName(requestMasterData.get("productType") != null ? requestMasterData.getString("productType") : "-");
			
			Map<String, String> reasonsMap = new HashMap<String, String>() {{
		        put("59", "Evaluating of a borrower for a new credit facility");
		        put("60", "Review as a Guarantor for a new credit facility");
		        put("61", "Review as a partner/proprietor for a new credit facility");
		        put("62", "Review as a director for a new credit facility");
		        put("63", "Opening of a Current Account");
		        put("64", "Monitoring and reviewing of an existing borrower");
		        put("999", "-");
		    }};
		    
		    requestMaster.setReason(reasonsMap.get(requestMasterData.get("reasonForReporting") != null ? requestMasterData.getString("reasonForReporting") : "999"));
		    requestMaster.setName(requestMasterData.get("name") != null ? requestMasterData.getString("name") : "");
		    requestMaster.setCustomerId(requestMasterData.get("customerId") != null ? requestMasterData.getString("customerId") : "");
		    requestMaster.setGender(requestMasterData.get("gender") != null ? (requestMasterData.getString("gender").toString().equalsIgnoreCase("001") ? "Male" : "Female") : "");
		    corporateObj.setRequestMaster(requestMaster);
		}
		
		if(requestMainData != null) {
			requestMain = new RequestMain();
			requestMain.setRequestNumber(requestMainData.getString("requestNumber") != null ? requestMainData.getString("requestNumber") : "");
			requestMain.setInitiatorName(requestMainData.getString("initiatorName") != null ? requestMainData.getString("initiatorName") : "");
			corporateObj.setRequestMain(requestMain);
		}
		
		if(corpFirmographicDetailsData != null) {
			firmographicDetails = new FirmographicDetails();
			firmographicDetails.setName(corpFirmographicDetailsData.getString("name") != null ? corpFirmographicDetailsData.getString("name") : "");
			firmographicDetails.setBrNumber(corpFirmographicDetailsData.getString("cra") != null ? corpFirmographicDetailsData.getString("cra") : "");
			firmographicDetails.setVatRegistrationNumber(corpFirmographicDetailsData.getString("vat") != null ? corpFirmographicDetailsData.getString("vat") : "");
			firmographicDetails.setDateOfRegistration(formatValue(corpFirmographicDetailsData.get("dateOfRegistration"), "DATE"));
			firmographicDetails.setLegalConstitution(corpFirmographicDetailsData.getString("legalConstitution") != null ? corpFirmographicDetailsData.getString("legalConstitution") : "");
			firmographicDetails.setTelephoneNumber(corpFirmographicDetailsData.getString("telephoneNumber") != null ? corpFirmographicDetailsData.getString("telephoneNumber") : "");
			firmographicDetails.setFaxNumber(corpFirmographicDetailsData.getString("faxNumber") != null ? corpFirmographicDetailsData.getString("faxNumber") : "");
			firmographicDetails.setUrl(corpFirmographicDetailsData.getString("url") != null ? corpFirmographicDetailsData.getString("url") : "");
		}

		if(corpMailingAddressesDataList != null) {
			mailingAddressesList = new ArrayList<MailingAddresses>();
			for(int x=0; x<corpMailingAddressesDataList.length(); x++) {
				MailingAddresses mailingAddress = new MailingAddresses();
				JSONObject corpMailingAddressesData = corpMailingAddressesDataList.getJSONObject(x);
				mailingAddress.setMNo(formatValue(corpMailingAddressesData.get("sno"), "NUMBER"));
				mailingAddress.setAddress(corpMailingAddressesData.getString("address") != null ? corpMailingAddressesData.getString("address") : "");
				mailingAddress.setReportedDate(formatValue(corpMailingAddressesData.get("reportedDate"), "DATE"));
				mailingAddressesList.add(mailingAddress);	
			}
			corporateObj.setMailingAddresses(mailingAddressesList);
		}
		
		if(corpPermanentAddressesDataList != null) {
			permanentAddressesList = new ArrayList<PermanentAddresses>();
			for(int x=0; x<corpPermanentAddressesDataList.length(); x++) {
				PermanentAddresses permanentAddresses = new PermanentAddresses();
				JSONObject corpPermanentAddressesData = corpPermanentAddressesDataList.getJSONObject(x);
				permanentAddresses.setPNo(formatValue(corpPermanentAddressesData.get("sno"), "NUMBER"));
				permanentAddresses.setAddress(corpPermanentAddressesData.getString("address") != null ? corpPermanentAddressesData.getString("address") : "");
				permanentAddresses.setReportedDate(formatValue(corpPermanentAddressesData.get("reportedDate"), "DATE"));
				permanentAddressesList.add(permanentAddresses);	
			}
			corporateObj.setPermanentAddresses(permanentAddressesList);
		}
		
		if(corpReportedNamesDataList != null) {
			reportedNamesList = new ArrayList<ReportedNames>();
			for(int x=0; x<corpReportedNamesDataList.length(); x++) {
				ReportedNames reportedNames = new ReportedNames();
				JSONObject corpReportedNamesData = corpReportedNamesDataList.getJSONObject(x);
				reportedNames.setSNo(formatValue(corpReportedNamesData.get("sno"), "NUMBER"));
				reportedNames.setReportedInstitution(corpReportedNamesData.getString("reportedInstitution") != null ? corpReportedNamesData.getString("reportedInstitution") : "");
				reportedNames.setName(corpReportedNamesData.getString("name") != null ? corpReportedNamesData.getString("name") : "");
				reportedNames.setReportedDate(formatValue(corpReportedNamesData.get("reportedDate"), "DATE"));
				reportedNamesList.add(reportedNames);	
			}
			corporateObj.setReportedNames(reportedNamesList);
		}
		
		if(corpRelationshipDetailsDataList != null) {
			relationshipDetailsList = new ArrayList<RelationshipDetails>();
			for(int x=0; x<corpRelationshipDetailsDataList.length(); x++) {
				RelationshipDetails relationshipDetail = new RelationshipDetails();
				JSONObject corpRelationshipDetailsData = corpRelationshipDetailsDataList.getJSONObject(x);
				relationshipDetail.setRNo(formatValue(corpRelationshipDetailsData.get("sno"), "NUMBER"));
				relationshipDetail.setEntityId(corpRelationshipDetailsData.getString("entityId") != null ? corpRelationshipDetailsData.getString("entityId") : "");
				relationshipDetail.setEntityName(corpRelationshipDetailsData.getString("entityName") != null ? corpRelationshipDetailsData.getString("entityName") : "");
				relationshipDetail.setNatureOfRelationship(corpRelationshipDetailsData.getString("natureOfRelationship") != null ? corpRelationshipDetailsData.getString("natureOfRelationship") : "");
				relationshipDetailsList.add(relationshipDetail);	
			}
			corporateObj.setRelationshipDetails(relationshipDetailsList);
		}

		if(corpSettledCreditFacilitiesDetailsDataList != null) {
			settledCFDetailsList = new ArrayList<SettledCFDetails>();
			for(int x=0; x<corpSettledCreditFacilitiesDetailsDataList.length(); x++) {
				SettledCFDetails settledCFDetail = new SettledCFDetails();
				JSONObject corpSettledCreditFacilitiesDetailsData = corpSettledCreditFacilitiesDetailsDataList.getJSONObject(x);
				settledCFDetail.setCurrency(corpSettledCreditFacilitiesDetailsData.getString("currency") != null ? corpSettledCreditFacilitiesDetailsData.getString("currency") : "");
				settledCFDetail.setCfType(corpSettledCreditFacilitiesDetailsData.getString("cfType") != null ? corpSettledCreditFacilitiesDetailsData.getString("cfType") : "");
				settledCFDetail.setNoOfCreditFacilitiesAsBorrower(formatValue(corpSettledCreditFacilitiesDetailsData.get("noOfCreditFacilitiesAsBorrower"), "NUMBER"));
				settledCFDetail.setAmountGrantedAsBorrower(formatValue(corpSettledCreditFacilitiesDetailsData.get("amountGrantedAsBorrower"), "AMOUNT"));
				settledCFDetail.setNoOfCreditFacilitiesAsGuarantor(formatValue(corpSettledCreditFacilitiesDetailsData.get("noOfCreditFacilitiesAsGuarantor"), "NUMBER"));
				settledCFDetail.setAmountGrantedAsGuarantor(formatValue(corpSettledCreditFacilitiesDetailsData.get("amountGrantedAsGuarantor"), "AMOUNT"));
				settledCFDetailsList.add(settledCFDetail);	
			}
			corporateObj.setSettledCFDetails(settledCFDetailsList);
		}

		if(corpLendingInstutionsInquiriesDataList != null) {
			lendingInstInquiriesList = new ArrayList<LendingInstInquiries>();
			for(int x=0; x<corpLendingInstutionsInquiriesDataList.length(); x++) {
				LendingInstInquiries lendingInstInquiry = new LendingInstInquiries();
				JSONObject corpLendingInstutionsInquiriesData = corpLendingInstutionsInquiriesDataList.getJSONObject(x);
				lendingInstInquiry.setINo(formatValue(corpLendingInstutionsInquiriesData.get("sno"), "NUMBER"));
				lendingInstInquiry.setInquiryDate(formatValue(corpLendingInstutionsInquiriesData.get("inquiryDate"), "DATE"));
				lendingInstInquiry.setInstitutionName(corpLendingInstutionsInquiriesData.getString("institutionName") != null ? corpLendingInstutionsInquiriesData.getString("institutionName") : "");
				lendingInstInquiry.setBranchName(corpLendingInstutionsInquiriesData.getString("instituionCategory") != null ? corpLendingInstutionsInquiriesData.getString("instituionCategory") : "");
				lendingInstInquiry.setReason(corpLendingInstutionsInquiriesData.getString("reason") != null ? corpLendingInstutionsInquiriesData.getString("reason") : "");
				lendingInstInquiry.setCfType(corpLendingInstutionsInquiriesData.getString("cfType") != null ? corpLendingInstutionsInquiriesData.getString("cfType") : "");
				lendingInstInquiry.setCurrency(corpLendingInstutionsInquiriesData.getString("currency") != null ? corpLendingInstutionsInquiriesData.getString("currency") : "");
				lendingInstInquiry.setAmount(formatValue(corpLendingInstutionsInquiriesData.get("amount"), "AMOUNT"));
				lendingInstInquiriesList.add(lendingInstInquiry);	
			}
			corporateObj.setLendingInstInquiries(lendingInstInquiriesList);
		}

		if(corpInquiriesBySubjectDataList != null) {
			inquiriesBySubjectList = new ArrayList<InquiriesBySubject>();
			for(int x=0; x<corpInquiriesBySubjectDataList.length(); x++) {
				InquiriesBySubject inquiriesBySubject = new InquiriesBySubject();
				JSONObject corpInquiriesBySubjectData = corpInquiriesBySubjectDataList.getJSONObject(x);
				inquiriesBySubject.setINo(formatValue(corpInquiriesBySubjectData.get("sno"), "NUMBER"));
				inquiriesBySubject.setInquiryDate(formatValue(corpInquiriesBySubjectData.get("inquiryDate"), "DATE"));
				inquiriesBySubject.setReason(corpInquiriesBySubjectData.getString("reason") != null ? corpInquiriesBySubjectData.getString("reason") : "");
				inquiriesBySubjectList.add(inquiriesBySubject);	
			}
			corporateObj.setInquiriesBySubject(inquiriesBySubjectList);
		}

		if(corpCreditFacilityDetailsDataList != null) {
			creditFacilityDetailsList = new ArrayList<CreditFacilityDetails>();
			for(int x=0; x<corpCreditFacilityDetailsDataList.length(); x++) {
				CreditFacilityDetails creditFacilityDetail = new CreditFacilityDetails();
				JSONObject corpCreditFacilityDetailsData = corpCreditFacilityDetailsDataList.getJSONObject(x);
				creditFacilityDetail.setCNo(formatValue(corpCreditFacilityDetailsData.get("sno"), "NUMBER"));
				creditFacilityDetail.setInstitutionCatg(corpCreditFacilityDetailsData.getString("institutionCategory") != null ? corpCreditFacilityDetailsData.getString("institutionCategory") : "");
				creditFacilityDetail.setInstitutionBranch(corpCreditFacilityDetailsData.getString("institutionBranch") != null ? corpCreditFacilityDetailsData.getString("institutionBranch") : "");				
				creditFacilityDetail.setCfType(corpCreditFacilityDetailsData.getString("cfType") != null ? corpCreditFacilityDetailsData.getString("cfType") : "");
				creditFacilityDetail.setCfStatus(corpCreditFacilityDetailsData.getString("cfStatus") != null ? corpCreditFacilityDetailsData.getString("cfStatus") : "");
				creditFacilityDetail.setOwnership(corpCreditFacilityDetailsData.getString("ownership") != null ? corpCreditFacilityDetailsData.getString("ownership") : "");
				creditFacilityDetail.setCurrency(corpCreditFacilityDetailsData.getString("currency") != null ? corpCreditFacilityDetailsData.getString("currency") : "");
				creditFacilityDetail.setAmountGranted(formatValue(corpCreditFacilityDetailsData.get("amountGranted"), "AMOUNT"));
				creditFacilityDetail.setCurrentBalance(formatValue(corpCreditFacilityDetailsData.get("currentBalance"), "AMOUNT"));
				creditFacilityDetail.setArreasAmount(formatValue(corpCreditFacilityDetailsData.get("arrearsAmount"), "AMOUNT"));
				creditFacilityDetail.setInstallmentAmount(formatValue(corpCreditFacilityDetailsData.get("installmentAmount"), "AMOUNT"));
				creditFacilityDetail.setAmountWrittenOff(formatValue(corpCreditFacilityDetailsData.get("amountWrittenOff"), "AMOUNT"));
				creditFacilityDetail.setReportedDate(formatValue(corpCreditFacilityDetailsData.get("reportedDate"), "DATE"));
				creditFacilityDetail.setFirstDisburseDate(formatValue(corpCreditFacilityDetailsData.get("firstDisburseDate"), "DATE"));
				creditFacilityDetail.setLatestPaymentDate(formatValue(corpCreditFacilityDetailsData.get("latestPaymentDate"), "DATE"));
				creditFacilityDetail.setRestructuringDate(formatValue(corpCreditFacilityDetailsData.get("restructuringDate"), "DATE"));
				creditFacilityDetail.setEndDate(formatValue(corpCreditFacilityDetailsData.get("endDate"), "DATE"));
				creditFacilityDetail.setRepayType(corpCreditFacilityDetailsData.getString("repayType") != null ? corpCreditFacilityDetailsData.getString("repayType") : "");
				creditFacilityDetail.setPurpose(corpCreditFacilityDetailsData.getString("purpose") != null ? corpCreditFacilityDetailsData.getString("purpose") : "");
				creditFacilityDetail.setCoverage(corpCreditFacilityDetailsData.getString("coverage") != null ? corpCreditFacilityDetailsData.getString("coverage") : "");
				creditFacilityDetailsList.add(creditFacilityDetail);	
			}
			corporateObj.setCreditFacilityDetails(creditFacilityDetailsList);
		}

		if(corpCreditFacilityDetailsLast24MonthsDataList != null) {
			cFForLast24MonthsList = new ArrayList<CFForLast24Months>();
			int i = 1;
			JSONObject corpCreditFacilityDetailsLast24MonthsData;
			for(int x=0; x<corpCreditFacilityDetailsLast24MonthsDataList.length(); x++) {
				CFForLast24Months cFForLast24Months = new CFForLast24Months();				
				cFForLast24Months.setCNo(String.valueOf(i));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(x);
				cFForLast24Months.setMonth1(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth1_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth2(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth2_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth3(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth3_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth4(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth4_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth5(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth5_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth6(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth6_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth7(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth7_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth8(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth8_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth9(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth9_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth10(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth10_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth11(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth11_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth12(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth12_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth13(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth13_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth14(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth14_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth15(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth15_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth16(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth16_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth17(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth17_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth18(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth18_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth19(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth19_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth20(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth20_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth21(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth21_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth22(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth22_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth23(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth23_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(++x);
				cFForLast24Months.setMonth24(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : "");
				cFForLast24Months.setMonth24_Color(getCellColor(corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") != null ? corpCreditFacilityDetailsLast24MonthsData.getString("maximumNumberOfDaysOverdue") : ""));
				cFForLast24MonthsList.add(cFForLast24Months);
				i++;
			}
			corporateObj.setCFForLast24Months(cFForLast24MonthsList);
		}

		if(corpCreditFacilityOfGlanceStatusDataList != null) {
			cFOfGlanceStatusList = new ArrayList<CFOfGlanceStatus>();
			for(int x=0; x<corpCreditFacilityOfGlanceStatusDataList.length(); x++) {
				CFOfGlanceStatus cFOfGlanceStatus = new CFOfGlanceStatus();
				JSONObject corpCreditFacilityOfGlanceStatusData = corpCreditFacilityOfGlanceStatusDataList.getJSONObject(x);
				cFOfGlanceStatus.setStatus(corpCreditFacilityOfGlanceStatusData.getString("status") != null ? corpCreditFacilityOfGlanceStatusData.getString("status") : "");				
				cFOfGlanceStatus.setArrearsDays0(formatValue(corpCreditFacilityOfGlanceStatusData.get("arrearsDays0"), "NUMBER"));
				cFOfGlanceStatus.setArrearsDays1_30(formatValue(corpCreditFacilityOfGlanceStatusData.get("arrearsDays1_30"), "NUMBER"));
				cFOfGlanceStatus.setArrearsDays31_60(formatValue(corpCreditFacilityOfGlanceStatusData.get("arrearsDays31_60"), "NUMBER"));
				cFOfGlanceStatus.setArrearsDays61_90(formatValue(corpCreditFacilityOfGlanceStatusData.get("arrearsDays61_90"), "NUMBER"));
				cFOfGlanceStatus.setArrearsDaysOver90(formatValue(corpCreditFacilityOfGlanceStatusData.get("arrearsDaysOver90"), "NUMBER"));
				cFOfGlanceStatusList.add(cFOfGlanceStatus);	
			}
			corporateObj.setCFOfGlanceStatus(cFOfGlanceStatusList);
		}

		if(corpDishonouredChequeDetailsDataList != null) {
			dishonChequeDetailsList = new ArrayList<DishonChequeDetails>();
			for(int x=0; x<corpDishonouredChequeDetailsDataList.length(); x++) {
				DishonChequeDetails dishonChequeDetail = new DishonChequeDetails();
				JSONObject corpDishonouredChequeDetailsData = corpDishonouredChequeDetailsDataList.getJSONObject(x);
				dishonChequeDetail.setInstitutionAndBranch(corpDishonouredChequeDetailsData.getString("institutionAndBranch") != null ? corpDishonouredChequeDetailsData.getString("institutionAndBranch") : "");
				dishonChequeDetail.setChequeNumber(corpDishonouredChequeDetailsData.getString("chequeNumber") != null ? corpDishonouredChequeDetailsData.getString("chequeNumber") : "");
				dishonChequeDetail.setChequeAmount(formatValue(corpDishonouredChequeDetailsData.get("chequeAmount"), "AMOUNT"));
				dishonChequeDetail.setDishonouredDate(formatValue(corpDishonouredChequeDetailsData.get("dateDishonoured"), "DATE"));
				dishonChequeDetail.setReason(corpDishonouredChequeDetailsData.getString("reason") != null ? corpDishonouredChequeDetailsData.getString("reason") : "");
				dishonChequeDetailsList.add(dishonChequeDetail);	
			}
			corporateObj.setDishonChequeDetails(dishonChequeDetailsList);
		}
		
		if(corpDishonouredChequeSummaryDataList != null) {
			dishonChequeSummaryList = new ArrayList<DishonChequeSummary>();
			for(int x=0; x<corpDishonouredChequeSummaryDataList.length(); x++) {
				DishonChequeSummary dishonChequeSummary = new DishonChequeSummary();
				JSONObject corpDishonouredChequeSummaryData = corpDishonouredChequeSummaryDataList.getJSONObject(x);
				dishonChequeSummary.setNumberOfCheques(formatValue(corpDishonouredChequeSummaryData.get("numberOfCheques"), "NUMBER"));
				dishonChequeSummary.setChequeValue(formatValue(corpDishonouredChequeSummaryData.get("chequeValue"), "AMOUNT"));
				dishonChequeSummaryList.add(dishonChequeSummary);	
			}
			corporateObj.setDishonChequeSummary(dishonChequeSummaryList);
		}

		if(corpPotentialAndCurrentLiabilitiesDataList != null) {
			potAndCurrLiabilitiesList = new ArrayList<PotAndCurrLiabilities>();
			for(int x=0; x<corpPotentialAndCurrentLiabilitiesDataList.length(); x++) {
				PotAndCurrLiabilities potAndCurrLiability = new PotAndCurrLiabilities();
				JSONObject corpPotentialAndCurrentLiabilitiesData = corpPotentialAndCurrentLiabilitiesDataList.getJSONObject(x);
				potAndCurrLiability.setCurrency(corpPotentialAndCurrentLiabilitiesData.getString("bureauCurrency") != null ? corpPotentialAndCurrentLiabilitiesData.getString("bureauCurrency") : "");
				potAndCurrLiability.setOwnership(corpPotentialAndCurrentLiabilitiesData.getString("ownership") != null ? corpPotentialAndCurrentLiabilitiesData.getString("ownership") : "");
				potAndCurrLiability.setNoOfCreditFacilities(formatValue(corpPotentialAndCurrentLiabilitiesData.get("noOfCreditFacilities"), "NUMBER"));
				potAndCurrLiability.setTotalAmountGrantedLimit(formatValue(corpPotentialAndCurrentLiabilitiesData.get("totalAmountGranted"), "AMOUNT"));
				potAndCurrLiability.setTotalOutstanding(formatValue(corpPotentialAndCurrentLiabilitiesData.get("totalOutstanding"), "AMOUNT"));
				potAndCurrLiabilitiesList.add(potAndCurrLiability);	
			}
			corporateObj.setPotAndCurrLiabilities(potAndCurrLiabilitiesList);
		}

		if(corpSettledCreditFacilitiesSummaryDataList != null) {
			settledCFSummaryList = new ArrayList<SettledCFSummary>();
			for(int x=0; x<corpSettledCreditFacilitiesSummaryDataList.length(); x++) {
				SettledCFSummary settledCFSummary = new SettledCFSummary();
				JSONObject corpSettledCreditFacilitiesSummaryData = corpSettledCreditFacilitiesSummaryDataList.getJSONObject(x);
				settledCFSummary.setCurrency(corpSettledCreditFacilitiesSummaryData.getString("currency") != null ? corpSettledCreditFacilitiesSummaryData.getString("currency") : "");
				settledCFSummary.setOwnership(corpSettledCreditFacilitiesSummaryData.getString("ownership") != null ? corpSettledCreditFacilitiesSummaryData.getString("ownership") : "");
				settledCFSummary.setNoOfCreditFacilities1(formatValue(corpSettledCreditFacilitiesSummaryData.get("noOfCreditFacilities1"), "NUMBER"));
				settledCFSummary.setAmountGranted1(formatValue(corpSettledCreditFacilitiesSummaryData.get("amountGranted1"), "AMOUNT"));
				settledCFSummary.setNoOfCreditFacilities2(formatValue(corpSettledCreditFacilitiesSummaryData.get("noOfCreditFacilities2"), "NUMBER"));
				settledCFSummary.setAmountGranted2(formatValue(corpSettledCreditFacilitiesSummaryData.get("amountGranted2"), "AMOUNT"));
				settledCFSummary.setNoOfCreditFacilities3(formatValue(corpSettledCreditFacilitiesSummaryData.get("noOfCreditFacilities3"), "NUMBER"));
				settledCFSummary.setAmountGranted3(formatValue(corpSettledCreditFacilitiesSummaryData.get("amountGranted3"), "AMOUNT"));
				settledCFSummary.setNoOfCreditFacilities4(formatValue(corpSettledCreditFacilitiesSummaryData.get("noOfCreditFacilities4"), "NUMBER"));
				settledCFSummary.setAmountGranted4(formatValue(corpSettledCreditFacilitiesSummaryData.get("amountGranted4"), "AMOUNT"));
				settledCFSummary.setNoOfCreditFacilities5(formatValue(corpSettledCreditFacilitiesSummaryData.get("noOfCreditFacilities5"), "NUMBER"));
				settledCFSummary.setAmountGranted5(formatValue(corpSettledCreditFacilitiesSummaryData.get("amountGranted5"), "AMOUNT"));
				settledCFSummaryList.add(settledCFSummary);	
			}
			corporateObj.setSettledCFSummary(settledCFSummaryList);
		}

		if(corpEconomicActivityHistoryDataList != null) {
			econActivityHistoryList = new ArrayList<EconActivityHistory>();
			for(int x=0; x<corpEconomicActivityHistoryDataList.length(); x++) {
				EconActivityHistory econActivityHistory = new EconActivityHistory();
				JSONObject corpEconomicActivityHistoryData = corpEconomicActivityHistoryDataList.getJSONObject(x);				
				econActivityHistory.setENo(formatValue(corpEconomicActivityHistoryData.get("sno"), "NUMBER"));
				econActivityHistory.setEconomicActivity(corpEconomicActivityHistoryData.getString("economicActivity") != null ? corpEconomicActivityHistoryData.getString("economicActivity") : "");
				econActivityHistory.setReportedDate(formatValue(corpEconomicActivityHistoryData.get("dateDishonoured"), "DATE"));
				econActivityHistoryList.add(econActivityHistory);	
			}
			corporateObj.setEconActivityHistory(econActivityHistoryList);
		}
	
		if(corpCatalogueDescriptionDataList != null) {
			catalogueDescriptionList = new ArrayList<CatalogueDescription>();
			for(int x=0; x<corpCatalogueDescriptionDataList.length(); x++) {
				CatalogueDescription catalogueDescription = new CatalogueDescription();
				JSONObject corpCatalogueDescriptionData = corpCatalogueDescriptionDataList.getJSONObject(x);
				catalogueDescription.setCatgLabel(corpCatalogueDescriptionData.getString("catgLabel") != null ? corpCatalogueDescriptionData.getString("catgLabel") : "");
				catalogueDescription.setCatgValue(corpCatalogueDescriptionData.getString("catgValue") != null ? corpCatalogueDescriptionData.getString("catgValue") : "");
				catalogueDescriptionList.add(catalogueDescription);	
			}
			corporateObj.setCatalogueDescription(catalogueDescriptionList);
		}

		if(corpCreditFacilityDetailsLast24MonthsDataList != null) {
			List<String> last24MonthList = new ArrayList<String>();
			last24Months = new Last24Months();
			for(int x=0; x<corpCreditFacilityDetailsLast24MonthsDataList.length(); x++) {
				JSONObject corpCreditFacilityDetailsLast24MonthsData = corpCreditFacilityDetailsLast24MonthsDataList.getJSONObject(x);
				if(!(last24MonthList.contains(corpCreditFacilityDetailsLast24MonthsData.getString("month")))) {
					last24MonthList.add(corpCreditFacilityDetailsLast24MonthsData.getString("month"));					
				}
			}
			
			if(last24MonthList.size() > 23) {
				last24Months.setMonth1(last24MonthList.get(0) != null ? last24MonthList.get(0) : "");
				last24Months.setMonth2(last24MonthList.get(1) != null ? last24MonthList.get(1) : "");
				last24Months.setMonth3(last24MonthList.get(2) != null ? last24MonthList.get(2) : "");
				last24Months.setMonth4(last24MonthList.get(3) != null ? last24MonthList.get(3) : "");
				last24Months.setMonth5(last24MonthList.get(4) != null ? last24MonthList.get(4) : "");
				last24Months.setMonth6(last24MonthList.get(5) != null ? last24MonthList.get(5) : "");
				last24Months.setMonth7(last24MonthList.get(6) != null ? last24MonthList.get(6) : "");
				last24Months.setMonth8(last24MonthList.get(7) != null ? last24MonthList.get(7) : "");
				last24Months.setMonth9(last24MonthList.get(8) != null ? last24MonthList.get(8) : "");
				last24Months.setMonth10(last24MonthList.get(9) != null ? last24MonthList.get(9) : "");
				last24Months.setMonth11(last24MonthList.get(10) != null ? last24MonthList.get(10) : "");
				last24Months.setMonth12(last24MonthList.get(11) != null ? last24MonthList.get(11) : "");
				last24Months.setMonth13(last24MonthList.get(12) != null ? last24MonthList.get(12) : "");
				last24Months.setMonth14(last24MonthList.get(13) != null ? last24MonthList.get(13) : "");
				last24Months.setMonth15(last24MonthList.get(14) != null ? last24MonthList.get(14) : "");
				last24Months.setMonth16(last24MonthList.get(15) != null ? last24MonthList.get(15) : "");
				last24Months.setMonth17(last24MonthList.get(16) != null ? last24MonthList.get(16) : "");
				last24Months.setMonth18(last24MonthList.get(17) != null ? last24MonthList.get(17) : "");
				last24Months.setMonth19(last24MonthList.get(18) != null ? last24MonthList.get(18) : "");
				last24Months.setMonth20(last24MonthList.get(19) != null ? last24MonthList.get(19) : "");
				last24Months.setMonth21(last24MonthList.get(20) != null ? last24MonthList.get(20) : "");
				last24Months.setMonth22(last24MonthList.get(21) != null ? last24MonthList.get(21) : "");
				last24Months.setMonth23(last24MonthList.get(22) != null ? last24MonthList.get(22) : "");
				last24Months.setMonth24(last24MonthList.get(23) != null ? last24MonthList.get(23) : "");
			}													
			corporateObj.setLast24Months(last24Months);
		}

		if(corpSettledCreditFacilitiesSummaryDataList != null) {
			last5Years = new Last5Years();
			JSONObject corpSettledCreditFacilitiesSummary1Data = corpSettledCreditFacilitiesSummaryDataList.getJSONObject(1);
			last5Years.setYear1(corpSettledCreditFacilitiesSummary1Data.getString("year1") != null ? corpSettledCreditFacilitiesSummary1Data.getString("year1") : "");
			last5Years.setYear2(corpSettledCreditFacilitiesSummary1Data.getString("year2") != null ? corpSettledCreditFacilitiesSummary1Data.getString("year2") : "");
			last5Years.setYear3(corpSettledCreditFacilitiesSummary1Data.getString("year3") != null ? corpSettledCreditFacilitiesSummary1Data.getString("year3") : "");
			last5Years.setYear4(corpSettledCreditFacilitiesSummary1Data.getString("year4") != null ? corpSettledCreditFacilitiesSummary1Data.getString("year4") : "");
			last5Years.setYear5(corpSettledCreditFacilitiesSummary1Data.getString("year5") != null ? corpSettledCreditFacilitiesSummary1Data.getString("year5") : "");	
			corporateObj.setLast5Years(last5Years);
		}

		if(corpDisputeDetailsDataList != null) {
			disputeDetailsList = new ArrayList<DisputeDetails>();
			for(int x=0; x<corpDisputeDetailsDataList.length(); x++) {	
				DisputeDetails disputeDetail = new DisputeDetails();
				JSONObject corpDisputeDetailsData = corpDisputeDetailsDataList.getJSONObject(x);
				disputeDetail.setDescription(corpDisputeDetailsData.getString("description") != null ? corpDisputeDetailsData.getString("description") : "");
				disputeDetailsList.add(disputeDetail);
			}
			corporateObj.setDisputeDetails(disputeDetailsList);
		}
				 		 			
		return corporateObj;
	 }
	 
	 public String getCellColor(String value){
		 String color = "background:white";
		 if(value != null  && !value.equals("") && !value.equals("--") && !value.equals("ND") && !value.equals("Cls")){
			 if(!value.matches("-?\\d+(\\.\\d+)?")) {
				 value = value.trim();
				 if(value.equalsIgnoreCase("OK")){
					 color = "background:#88cc00";
				 }else{	
					 //color = "background:darkcyan; color:white";
					 color = "background:white";
				 }			 
			 }else{
				 int number = Integer.parseInt(value);
				 if(number > 90){        
					 color = "background:#e60000; color:white";
				 }else if (number <= 90){
					 color = "background:yellow";
				 }
			 }
		 }	 
		return color + "; text-align: center";		
	}
	 
	 public String formatValue(Object value, String type) {	
		String returnVal = "";
		if(!value.equals(null)){
			if(type.equals("DATE")) {
				String date = value.toString().substring(0,10);
	        	String day = date.split("-")[2];
	        	String month = date.split("-")[1];
	        	String year = date.split("-")[0];
	        	returnVal = day+"/"+month+"/"+year;
	        	
			}else if(type.equals("AMOUNT")) {
				NumberFormat nf = NumberFormat.getCurrencyInstance();
				DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
				// show hide currency symbol
				decimalFormatSymbols.setCurrencySymbol("");
				((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
				// show hide decimal points
				nf.setMaximumFractionDigits(0);
				returnVal = nf.format(value).trim();
				
			}else if(type.equals("NUMBER")) {
				returnVal = value.toString();				
			} 
		}
		return returnVal;
	}
	
}
