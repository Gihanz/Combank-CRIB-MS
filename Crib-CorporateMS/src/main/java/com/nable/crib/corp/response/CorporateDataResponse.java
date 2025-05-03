/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 22, 2022 - 10:54:52 AM
 *  ***************************************
 */

package com.nable.crib.corp.response;

import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CorporateDataResponse extends CommonResponse {

	public CorporateDataResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public CorpFirmographicDetails corpFirmographicDetails;
	public List<CorpIdentificationDetails> corpIdentificationDetailsList;
	public List<CorpMailingAddresses> corpMailingAddressesList;
	public List<CorpPermanentAddresses> corpPermanentAddressesList;
	public List<CorpReportedNames> corpReportedNamesList;
	public List<CorpRelationshipDetails> corpRelationshipDetailsList;
	public List<CorpRelationshipAddressDetails> corpRelationshipAddressDetailsList;
	public List<CorpSettledCreditFacilitiesDetails> corpSettledCreditFacilitiesDetailsList;
	public List<CorpSettledCreditFacilitiesSummary> corpSettledCreditFacilitiesSummaryList;
	public List<CorpLendingInstutionsInquiries> corpLendingInstutionsInquiriesList;
	public List<CorpInquiriesBySubject> corpInquiriesBySubjectList;
	public List<CorpCreditFacility> corpCreditFacilityList;
	public List<CorpCreditFacilityDetails> corpCreditFacilityDetailsList;
	public List<CorpLast24Months> corpLast24MonthsList;
	public List<CorpCreditFacilityDetailsLast24Months> corpCreditFacilityDetailsLast24MonthsList;
	public List<CorpDisputeDetails> corpDisputeDetailsList;
	public List<CorpPotentialAndCurrentLiabilitiesHeader> corpPotentialAndCurrentLiabilitiesHeaderList;
	public List<CorpPotentialAndCurrentLiabilities> corpPotentialAndCurrentLiabilitiesList;
	public List<CorpCreditFacilityOfGlanceStatus> corpCreditFacilityOfGlanceStatusList;
	public List<CorpDishonouredChequeSummary> corpDishonouredChequeSummaryList;
	public List<CorpDishonouredChequeDetails> corpDishonouredChequeDetailsList;
	public List<CorpEconomicActivityHistory> corpEconomicActivityHistoryList;
	public List<CorpCatalogueDescription> corpCatalogueDescriptionList;
	public List<CorpSearchResults> corpSearchResultsList;
	
}
