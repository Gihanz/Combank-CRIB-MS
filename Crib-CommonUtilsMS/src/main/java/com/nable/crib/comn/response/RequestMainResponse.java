/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 9:15:29 PM
 *  ***************************************
 */

package com.nable.crib.comn.response;

import com.nable.crib.comn.model.RequestMain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RequestMainResponse extends CommonResponse {

	public RequestMainResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public RequestMain requestMain;
}
