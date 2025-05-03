package com.nable.crib.pdf.bean.crib;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SettledCFDetails {

	private String currency;
	private String cfType;
	private String noOfCreditFacilitiesAsBorrower;
	private String amountGrantedAsBorrower;
	private String noOfCreditFacilitiesAsGuarantor;
	private String amountGrantedAsGuarantor;
	 
}
