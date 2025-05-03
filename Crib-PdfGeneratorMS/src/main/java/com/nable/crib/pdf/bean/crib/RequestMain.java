/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 29, 2022 - 1:40:19 PM
 *  ***************************************
 */

package com.nable.crib.pdf.bean.crib;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestMain {

	private String sourceType;
	private String requestType;
	private String requestNumber;
	private String initiatorName;
	private String initiatorDesignation;
	private String reportValidityPeriod;
	
}
