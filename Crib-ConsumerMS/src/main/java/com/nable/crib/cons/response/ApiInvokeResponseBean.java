package com.nable.crib.cons.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiInvokeResponseBean extends CommonResponse{

	public ApiInvokeResponseBean(String statusCode, String status, String message) {
        super(statusCode, status, message);
    }
	
	public Double perHitCost;
	public String errorCode;
	public String error;
	public MultiHit multiHit;
	public SearchResults searchResults;
	
}
