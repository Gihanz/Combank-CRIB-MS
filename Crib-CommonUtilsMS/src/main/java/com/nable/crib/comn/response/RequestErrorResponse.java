/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 22, 2022 - 12:17:00 AM
 *  ***************************************
 */

package com.nable.crib.comn.response;

import java.util.List;

import com.nable.crib.comn.model.RequestError;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestErrorResponse extends CommonResponse {
	
	public RequestErrorResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public List<RequestError> requestError;

}
