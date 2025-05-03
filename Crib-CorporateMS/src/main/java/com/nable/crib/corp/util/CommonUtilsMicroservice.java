/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 23, 2022 - 6:16:17 PM
 *  ***************************************
 */

package com.nable.crib.corp.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nable.crib.corp.request.RequestDetailRequest;
import com.nable.crib.corp.request.RequestErrorRequest;
import com.nable.crib.corp.response.CommonResponse;
import com.nable.crib.corp.response.Status;

@Component
@PropertySource("classpath:application.properties")
public class CommonUtilsMicroservice {

	static Logger log = LoggerFactory.getLogger(CommonUtilsMicroservice.class);
	
	@Autowired
    private RestTemplate restTemplate;
    
    @Value("${microservice.commonUtils.url:http://CRIB-CommonUtilsMS}")
    private String commonUtilsUrl;
    
    public void updateRequestDetails(Long requestDetailId, String bureauReferenceNo, int isCribAvailable, int isHit, Status status, String errorCode, String error, Double perHitCost, String username) throws Exception {
    	
    	String baseUrl = commonUtilsUrl + "/updateRequestDetail/"+requestDetailId;
                
        HttpHeaders headers = new HttpHeaders();
        RequestDetailRequest requestDetailRequest = new RequestDetailRequest();
        requestDetailRequest.setBureauReferenceNo(bureauReferenceNo);
        requestDetailRequest.setIsCribAvailable(isCribAvailable);
        requestDetailRequest.setIsHit(isHit);
        requestDetailRequest.setStatusCode(status.getStatusCode());
        requestDetailRequest.setStatus(status.getStatus());
        requestDetailRequest.setStatusDescription("CRIB_COMPLETED");
        requestDetailRequest.setErrorCode(errorCode);
        requestDetailRequest.setError(error);
        requestDetailRequest.setSourceName("API");
        requestDetailRequest.setReportPath("N/A");
        requestDetailRequest.setPerHitCost(perHitCost);
        requestDetailRequest.setReportId(requestDetailId);
        requestDetailRequest.setUsername(username);
        HttpEntity<RequestDetailRequest> requestHttpEntity = new HttpEntity<RequestDetailRequest>(requestDetailRequest, headers);

        ResponseEntity<CommonResponse> commRsp = restTemplate.exchange(baseUrl, HttpMethod.PUT, requestHttpEntity, CommonResponse.class);

        if(!commRsp.getBody().getStatusCode().equals("S200")) {       	
        	throw new Exception("Update Request Details Failed");            	
        }       
    }
    
    public void addRequestError(Long requestDetailId, List<RequestErrorRequest> requestErrorRequestList, String username) throws Exception {
    	
    	String baseUrl = commonUtilsUrl + "/addRequestError";
        
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<List<RequestErrorRequest>> requestHttpEntity = new HttpEntity<List<RequestErrorRequest>>(requestErrorRequestList, headers);

        ResponseEntity<CommonResponse> commRsp = restTemplate.exchange(baseUrl, HttpMethod.POST, requestHttpEntity, CommonResponse.class);

        if(!commRsp.getBody().getStatusCode().equals("S200")) {       	
        	throw new Exception("Add Request Errors Failed");            	
        }      
    }
    
}
