package com.nable.crib.pdf.bean.corpAnalysis;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorpAnalysisObject {
	
	private int requestDetailId;
	private String requestNumber;
	private String CRIBReportNumber;
	private String CRIBOrderDate;
	private String fullName;
	private String BR;
	private List<DishonoredChequesSummary> dishonoredChequesSummary;
	private List<CreditFacilitySummary> creditFacilitySummary;
	private List<CreditFacilitySummaryBasedLendingIns> creditFacilitySummaryBasedLendingIns;
	private List<CreditFacilityAtAGlanceCondensed> creditFacilityAtAGlanceCondensed;
	private List<CreditFacilityAtAGlance> creditFacilityAtAGlance;
	private List<OverdueFacilities1> overdueFacilities1;
	private List<OverdueFacilities1> overdueFacilities2;
	private List<OverdueFacilities1> overdueFacilities3;
	private List<OverdueFacilities1> overdueFacilities4;
	private List<DetailsOfProblematicFacilities> detailsOfProblematicFacilities;
	
}
