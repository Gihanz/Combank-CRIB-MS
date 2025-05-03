/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 22, 2022 - 10:33:04 AM
 *  ***************************************
 */

package com.nable.crib.cons.response;

import java.util.List;

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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ConsumerDataResponse extends CommonResponse {
	
	public ConsumerDataResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public ConsDemographicDetails consDemographicDetails;
	public List<ConsIdentificationDetails> consIdentificationDetailsList;					
	public List<ConsMailingAddresses> consMailingAddressesList;
	public List<ConsPermanentAddresses> consPermanentAddressesList;
	public List<ConsReportedNames> consReportedNamesList;
	public List<ConsEmploymentDetails> consEmploymentDetailsList;
	public List<ConsRelationshipDetails> consRelationshipDetailsList;
	public List<ConsRelationshipAddressDetails> consRelationshipAddressDetailsList;
	public List<ConsSettledCreditFacilitiesDetails> consSettledCreditFacilitiesDetailsList;
	public List<ConsSettledCreditFacilitiesSummary> consSettledCreditFacilitiesSummaryList;
	public List<ConsLendingInstutionsInquiries> consLendingInstutionsInquiriesList;
	public List<ConsInquiriesBySubject> consInquiriesBySubjectList;
	public List<ConsCreditFacility> consCreditFacilityList;
	public List<ConsCreditFacilityDetails> consCreditFacilityDetailsList;
	public List<ConsLast24Months> consLast24MonthsList;
	public List<ConsCreditFacilityDetailsLast24Months> consCreditFacilityDetailsLast24MonthsList;
	public List<ConsDisputeDetails> consDisputeDetailsList;
	public List<ConsPotentialAndCurrentLiabilitiesHeader> consPotentialAndCurrentLiabilitiesHeaderList;
	public List<ConsPotentialAndCurrentLiabilities> consPotentialAndCurrentLiabilitiesList;
	public List<ConsCreditFacilityOfGlanceStatus> consCreditFacilityOfGlanceStatusList;
	public List<ConsDishonouredChequeSummary> consDishonouredChequeSummaryList;
	public List<ConsDishonouredChequeDetails> consDishonouredChequeDetailsList;
	public List<ConsCatalogueDescription> consCatalogueDescriptionList;
	public List<ConsSearchResults> consSearchResultsList;

}
