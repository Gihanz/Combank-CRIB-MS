package com.nable.crib.corp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiInvokeRequestBean {
	
	private String requestId;
	private String reportId;
	private SubjectType subjectType;	
	private String responseType;
	private String reasonCode;
	private String product;
	private String number;
	private String date;
	private String amount;
	private String currency;	
	private String searchValue;
	private PerHitCost perHitCost;
	private IsApplicationProvided isApplicationProvided;	
	
}
