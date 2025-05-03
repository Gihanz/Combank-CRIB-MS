/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 1:18:48 AM
 *  ***************************************
 */

package com.nable.crib.comn.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nable.crib.comn.model.RequestDetail;
import com.nable.crib.comn.repository.RequestDetailRepository;
import com.nable.crib.comn.request.RequestDetailRequest;
import com.nable.crib.comn.response.CommonResponse;
import com.nable.crib.comn.response.RequestDetailResponse;
import com.nable.crib.comn.response.Status;

@Service("RequestDetailService")
@Transactional
public class RequestDetailService {
	
	@Autowired
	private RequestDetailRepository requestDetailRepository;
	
	public RequestDetailResponse get(Long requestDetailId) {	
		
		RequestDetailResponse requestDetailResponse = new RequestDetailResponse();
		
		requestDetailResponse.setRequestDetail(requestDetailRepository.findById(requestDetailId).get());
		requestDetailResponse.setStatus(Status.SUCCESS.getStatus());
		requestDetailResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		requestDetailResponse.setMessage("Get Request Details Successful");
		
		return requestDetailResponse;	
	}
	
	public RequestDetailResponse save(RequestDetail requestDetail) {	
		
		RequestDetailResponse requestDetailResponse = new RequestDetailResponse();
		
		requestDetailResponse.setRequestDetail(requestDetailRepository.save(requestDetail));
		requestDetailResponse.setStatus(Status.SUCCESS.getStatus());
		requestDetailResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		requestDetailResponse.setMessage("Insert Request Details Successful");
		
		return requestDetailResponse;
	}

	public CommonResponse update(Long requestDetailId, RequestDetailRequest requestDetailRequest) {	
		
		CommonResponse commonResponse = new CommonResponse();
		
		RequestDetail rd = requestDetailRepository.findById(requestDetailId).get();
		rd.setBureauReferenceNo(requestDetailRequest.getBureauReferenceNo());
		rd.setIsCribAvailable(requestDetailRequest.getIsCribAvailable());
		rd.setIsHit(requestDetailRequest.getIsHit());
		rd.setStatusCode(requestDetailRequest.getStatusCode());
		rd.setStatusDescription(requestDetailRequest.getStatusDescription());
		rd.setStatus(requestDetailRequest.getStatus());
		rd.setErrorCode(requestDetailRequest.getErrorCode());
		rd.setError(requestDetailRequest.getError());
		rd.setSourceName(requestDetailRequest.getSourceName());
		rd.setReportPath(requestDetailRequest.getReportPath());
		rd.setPerHitCost(requestDetailRequest.getPerHitCost());
		rd.setReportId(requestDetailRequest.getReportId());
		rd.setModifiedBy(requestDetailRequest.getUsername());
		rd.setModified(new Date(System.currentTimeMillis()));
		requestDetailRepository.save(rd);
		
		commonResponse.setStatus(Status.SUCCESS.getStatus());
		commonResponse.setStatusCode(Status.SUCCESS.getStatusCode());
		commonResponse.setMessage("Update Request Details Successful");
		
		return commonResponse;
		
	}

}
