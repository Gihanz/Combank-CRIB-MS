/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:30:26 PM
 *  ***************************************
 */

package com.nable.crib.comn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nable.crib.comn.model.RequestDetailHistory;
import com.nable.crib.comn.repository.RequestDetailHistoryRepository;
import com.nable.crib.comn.response.CommonResponse;
import com.nable.crib.comn.response.Status;

@Service("RequestDetailHistoryService")
@Transactional
public class RequestDetailHistoryService {

	@Autowired
	private RequestDetailHistoryRepository requestDetailHistoryRepository;
	
	public CommonResponse save(RequestDetailHistory requestDetailHistory) {	
		
		CommonResponse commonResponse = new CommonResponse();
		
		requestDetailHistoryRepository.save(requestDetailHistory);
		commonResponse.setStatus(Status.SUCCESS.getStatus());
		commonResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		commonResponse.setMessage("Insert Request Detail History Successful");
		
		return commonResponse;
	}

}
