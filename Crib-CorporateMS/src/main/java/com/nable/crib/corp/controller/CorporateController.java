package com.nable.crib.corp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nable.crib.corp.response.Status;
import com.nable.crib.corp.request.CorporateApiInvokeRequest;
import com.nable.crib.corp.response.ApiInvokeResponseBean;
import com.nable.crib.corp.response.CorporateDataResponse;
import com.nable.crib.corp.service.CorporateService;
import com.nable.crib.corp.util.ErrorDescription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CorporateController {
	
	static Logger log = LoggerFactory.getLogger(CorporateController.class);
		
	@Autowired
    private CorporateService corporateService;

	@Operation(summary = "Test Working Status", description = "Test Working Status by Get")
	@GetMapping("/test")
	public String testService() {
		return "Service working fine";
	}
	
	@Operation(summary = "Invoke CRIB Corporate API and insert into DB", description = "Invoke CRIB Corporate API, Read H2H response and insert into DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = ApiInvokeResponseBean.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/invokeCorporateApi")
	public ResponseEntity<ApiInvokeResponseBean> invokeCorporateApi(@RequestBody CorporateApiInvokeRequest requestObj) {

		ApiInvokeResponseBean responseBean = new ApiInvokeResponseBean();
		try {
			log.debug("invokeCorporateApi for : ", requestObj.getRequestDetailId());
			responseBean = corporateService.save(requestObj);
			log.info(responseBean.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(responseBean);
			
		} catch(Exception ex) {
			log.error("Error occurred : ", ex);
			responseBean = new ApiInvokeResponseBean(responseBean.getStatusCode(), responseBean.getStatus(), "Error occurred : "+ex.toString());
			
			MDC.clear();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBean);					
		}

	}
	
	@Operation(summary = "Get Corporate CRIB Data", description = "Get Corporate CRIB Data from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = CorporateDataResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getCorporateCribData/{requestDetailId}")
	public ResponseEntity<CorporateDataResponse> getCorporateCribData(@PathVariable(name = "requestDetailId") Long requestDetailId) {
		
		CorporateDataResponse corporateDataResponse = new CorporateDataResponse();
		try {		
			log.debug("getCorporateCribData for : ", requestDetailId);
			corporateDataResponse = corporateService.get(requestDetailId);
			log.info(corporateDataResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(corporateDataResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			corporateDataResponse = new CorporateDataResponse(Status.APPLICATION_ERROR.getStatusCode(), Status.APPLICATION_ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(corporateDataResponse);
		}		
	}
	
}
