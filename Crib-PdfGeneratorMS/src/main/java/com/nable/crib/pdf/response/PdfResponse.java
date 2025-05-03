/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 2:33:02 PM
 *  ***************************************
 */

package com.nable.crib.pdf.response;

import com.nable.crib.pdf.response.CommonResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PdfResponse extends CommonResponse {

	public PdfResponse(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public String pdf;
	
}
