/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 9:16:07 PM
 *  ***************************************
 */

package com.nable.crib.comn.response;

import com.nable.crib.comn.model.RequestMaster;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestMasterResponse extends CommonResponse {

	public RequestMasterResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public RequestMaster requestMaster;
	
}
