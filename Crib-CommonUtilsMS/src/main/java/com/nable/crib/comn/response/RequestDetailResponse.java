/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 21, 2022 - 10:43:39 PM
 *  ***************************************
 */

package com.nable.crib.comn.response;

import com.nable.crib.comn.model.RequestDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestDetailResponse extends CommonResponse {

	public RequestDetailResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public RequestDetail requestDetail;

}
