package com.nable.crib.pdf.bean.crib;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateCribObject {
	
	private RequestMain requestMain;
	private RequestMaster requestMaster;
	private RequestDetail requestDetail;
	private FirmographicDetails firmographicDetails;
	private List<MailingAddresses> mailingAddresses;
	private List<PermanentAddresses> permanentAddresses;
	private List<ReportedNames> reportedNames;
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
	private List<EconActivityHistory> econActivityHistory;
	private List<CatalogueDescription> catalogueDescription;
	private Last24Months last24Months;
	private Last5Years last5Years;

}