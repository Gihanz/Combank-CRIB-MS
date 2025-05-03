/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:30:07 PM
 *  ***************************************
 */

package com.nable.crib.comn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nable.crib.comn.model.ReportsAccessHistory;
import com.nable.crib.comn.repository.ReportsAccessHistoryRepository;
import com.nable.crib.comn.response.CommonResponse;
import com.nable.crib.comn.response.Status;

@Service("ReportsAccessHistoryService")
@Transactional
public class ReportsAccessHistoryService {

	@Autowired
	private ReportsAccessHistoryRepository reportsAccessHistoryRepository;
	
	public CommonResponse save(ReportsAccessHistory reportsAccessHistory) {	
		
		CommonResponse commonResponse = new CommonResponse();
		
		reportsAccessHistoryRepository.save(reportsAccessHistory);
		commonResponse.setStatus(Status.SUCCESS.getStatus());
		commonResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		commonResponse.setMessage("Insert Reports Access Historys Successful");
		
		return commonResponse;
	}

}
