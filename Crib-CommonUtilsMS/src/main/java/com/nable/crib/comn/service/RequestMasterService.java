/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:31:24 PM
 *  ***************************************
 */

package com.nable.crib.comn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nable.crib.comn.model.RequestMaster;
import com.nable.crib.comn.repository.RequestMasterRepository;
import com.nable.crib.comn.response.RequestMasterResponse;
import com.nable.crib.comn.response.Status;

@Service("RequestMasterService")
@Transactional
public class RequestMasterService {

	@Autowired
	private RequestMasterRepository requestMasterRepository;
	
	public RequestMasterResponse get(Long id) {	
		
		RequestMasterResponse requestMasterResponse = new RequestMasterResponse();
		
		requestMasterResponse.setRequestMaster(requestMasterRepository.findById(id).get());
		requestMasterResponse.setStatus(Status.SUCCESS.getStatus());
		requestMasterResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		requestMasterResponse.setMessage("Get Request Master Successful");
		
		return requestMasterResponse;	
	}
	
	public RequestMasterResponse save(RequestMaster requestMaster) {	
		
		RequestMasterResponse requestMasterResponse = new RequestMasterResponse();
		
		requestMasterResponse.setRequestMaster(requestMasterRepository.save(requestMaster));
		requestMasterResponse.setStatus(Status.SUCCESS.getStatus());
		requestMasterResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		requestMasterResponse.setMessage("Insert Request Master Successful");
		
		return requestMasterResponse;
	}

}
