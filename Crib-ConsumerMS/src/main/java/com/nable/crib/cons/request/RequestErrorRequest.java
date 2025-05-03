/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 24, 2022 - 11:32:36 AM
 *  ***************************************
 */

package com.nable.crib.cons.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestErrorRequest {

	private Long requestDetailId;
	private String errorCode;
	private String errorDescription;
	
}
