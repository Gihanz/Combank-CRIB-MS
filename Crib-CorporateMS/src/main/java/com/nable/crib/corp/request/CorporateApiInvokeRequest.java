/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 12, 2022 - 2:25:40 PM
 *  ***************************************
 */

package com.nable.crib.corp.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CorporateApiInvokeRequest {
	
	private Long requestDetailId;
	private RequestType requestType;
	private String username;
	private SingleHitRequestBean singleHitRequestBean;
	private MultiHitRequestBean multiHitRequestBean;

}
