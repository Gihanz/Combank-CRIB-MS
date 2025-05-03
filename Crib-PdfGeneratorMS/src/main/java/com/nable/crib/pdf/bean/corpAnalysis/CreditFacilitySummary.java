package com.nable.crib.pdf.bean.corpAnalysis;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditFacilitySummary {
	
	private String label;
	private String currency;
	private String bType;
	private String gType;
	private String bNoOfFacilities;
	private String gNoOfFacilities;
	private String bAmountGranted;
	private String gAmountGranted;
	private String bOutstanding;
	private String gOutstanding;
	private String bMonthlyCommitment;
	private String gMonthlyCommitment;
	
}
