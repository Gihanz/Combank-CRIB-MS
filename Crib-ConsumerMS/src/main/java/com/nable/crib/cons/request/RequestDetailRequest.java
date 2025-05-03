/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 12, 2022 - 2:25:40 PM
 *  ***************************************
 */

package com.nable.crib.cons.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDetailRequest {
	
	private int isCribAvailable;
	private int isHit;
	private String sourceName;
	private String reportPath;
	private Double perHitCost;
	private String bureauReferenceNo;
	private Long reportId;
	private String statusCode;
	private String status;
	private String statusDescription;
	private String errorCode;
	private String error;
	private String username;	

}
