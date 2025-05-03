package com.nable.crib.pdf.bean.crib;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsumerCribObject {
	
	private RequestMain requestMain;
	private RequestMaster requestMaster;
	private RequestDetail requestDetail;
	private DemographicDetails demographicDetails;
	private List<MailingAddresses> mailingAddresses;
	private List<PermanentAddresses> permanentAddresses;
	private List<ReportedNames> reportedNames;
	private List<EmploymentDetails> employmentDetails;
	private List<RelationshipDetails> relationshipDetails;
	private List<SettledCFDetails> settledCFDetails;
	private List<SettledCFSummary> settledCFSummary;
	private List<LendingInstInquiries> lendingInstInquiries;
	private List<InquiriesBySubject> inquiriesBySubject;
	private List<CreditFacilityDetails> creditFacilityDetails;
	private List<CFForLast24Months> cFForLast24Months;
	private List<OwnershipDetails> ownershipDetails;
	private List<DisputeDetails> disputeDetails;
	private List<PotAndCurrLiabilities> potAndCurrLiabilities;
	private List<CFOfGlanceStatus> cFOfGlanceStatus;
	private List<DishonChequeSummary> dishonChequeSummary;
	private List<DishonChequeDetails> dishonChequeDetails;
	private List<CatalogueDescription> catalogueDescription;
	private Last24Months last24Months;
	private Last5Years last5Years;
	
}