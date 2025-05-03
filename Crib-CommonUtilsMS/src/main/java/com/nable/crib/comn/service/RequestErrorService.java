/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 2:38:07 AM
 *  ***************************************
 */

package com.nable.crib.comn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nable.crib.comn.model.RequestError;
import com.nable.crib.comn.repository.RequestErrorRepository;
import com.nable.crib.comn.response.CommonResponse;
import com.nable.crib.comn.response.RequestErrorResponse;
import com.nable.crib.comn.response.Status;

@Service("RequestErrorService")
@Transactional
public class RequestErrorService {
	
	@Autowired
	private RequestErrorRepository requestErrorRepository;
	
	public RequestErrorResponse get(Long requestDetailId) {	
		
		RequestErrorResponse requestErrorResponse = new RequestErrorResponse();
		
		requestErrorResponse.setRequestError(requestErrorRepository.findByRequestDetailId(requestDetailId));
		requestErrorResponse.setStatus(Status.SUCCESS.getStatus());
		requestErrorResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		requestErrorResponse.setMessage("Get Request Errors Successful");
		
		return requestErrorResponse;
	}
	
	public CommonResponse save(List<RequestError> requestErrorsList) {
				
		CommonResponse commonResponse = new CommonResponse();
		
		requestErrorRepository.saveAll(requestErrorsList);
		commonResponse.setStatus(Status.SUCCESS.getStatus());
		commonResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		commonResponse.setMessage("Insert Request Errors Successful");
		
		return commonResponse;
	}

}
