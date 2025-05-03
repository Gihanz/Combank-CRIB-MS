package com.nable.crib.corp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleHitRequestBean extends ApiInvokeRequestBean {
	
	private String name;
	private String accountNumber;
	private String dataProviderBrnId;	
	private String brNumber;
	
}
