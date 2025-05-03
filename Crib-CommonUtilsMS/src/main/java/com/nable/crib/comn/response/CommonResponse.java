/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 21, 2022 - 3:37:59 PM
 *  ***************************************
 */

package com.nable.crib.comn.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse {
	
	public String statusCode;
	public String status;
	public String message;		

}
