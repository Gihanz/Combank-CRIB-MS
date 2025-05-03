package com.nable.crib.cons.request;

import com.nable.crib.cons.request.ApiInvokeRequestBean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleHitRequestBean extends ApiInvokeRequestBean {
	
	private String name;
	private String accountNumber;
	private String dataProviderBrnId;
	private String citizenship;
	private String nic;
	private String passportNumber;	
	private String gender;
	private String dob;
	
}
