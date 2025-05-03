package com.nable.crib.pdf.bean.crib;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DishonChequeDetails {
	
	private String institutionAndBranch;
	private String chequeNumber;
	private String chequeAmount;
	private String dishonouredDate;
	private String reason;
	private String disputed;
	
}
