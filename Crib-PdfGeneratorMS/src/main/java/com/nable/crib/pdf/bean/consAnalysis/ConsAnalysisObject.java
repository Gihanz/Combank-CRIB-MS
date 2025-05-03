package com.nable.crib.pdf.bean.consAnalysis;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConsAnalysisObject {
	
	private int requestDetailId;
	private String requestNumber;
	private String CRIBReportNumber;
	private String CRIBOrderDate;
	private String fullName;
	private String NIC;
	private String passport;
	private String drivingLicense;
	private String profession;
	private String employerName;
	private String permenantAddress;
	private List<RelationshipDetails> relationshipDetails;
	private List<DishonoredChequesSummary> dishonoredChequesSummary;
	private List<CFSummary> CFSummary;
	private List<SummaryOfCreditFacilityAtGlance> summaryOfCreditFacilityAtGlance;
	private List<CFDetails> CFDetails;
	
}
