package com.nable.crib.cons.controller;

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

import com.nable.crib.cons.request.ConsumerApiInvokeRequest;
import com.nable.crib.cons.response.ApiInvokeResponseBean;
import com.nable.crib.cons.response.ConsumerDataResponse;
import com.nable.crib.cons.response.Status;
import com.nable.crib.cons.service.ConsumerService;
import com.nable.crib.cons.util.ErrorDescription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ConsumerController {
	
	static Logger log = LoggerFactory.getLogger(ConsumerController.class);
	
	@Autowired
    private ConsumerService consumerService;

	@Operation(summary = "Test Working Status", description = "Test Working Status by Get")
	@GetMapping("/test")
	public String testService() {
		return "Service working fine";
	}
	
	@Operation(summary = "Invoke CRIB Consumer API and insert into DB", description = "Invoke CRIB Consumer API, Read H2H response and insert into DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = ApiInvokeResponseBean.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/invokeConsumerApi")
	public ResponseEntity<ApiInvokeResponseBean> invokeConsumerApi(@RequestBody ConsumerApiInvokeRequest requestObj) {

		ApiInvokeResponseBean responseBean = new ApiInvokeResponseBean();
		try {
			log.debug("invokeConsumerApi for : ", requestObj.getRequestDetailId());
			responseBean = consumerService.save(requestObj);
			log.info(responseBean.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(responseBean);
			
		} catch(Exception ex) {
			log.error("Error occurred : ", ex);
			responseBean = new ApiInvokeResponseBean(responseBean.getStatusCode(), responseBean.getStatus(), "Error occurred : "+ex.toString());
			
			MDC.clear();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBean);					
		}

	}
	
	@Operation(summary = "Get Consumer CRIB Data", description = "Get Consumer CRIB Data from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = ConsumerDataResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getConsumerCribData/{requestDetailId}")
	public ResponseEntity<ConsumerDataResponse> getConsumerCribData(@PathVariable(name = "requestDetailId") Long requestDetailId) {
		
		ConsumerDataResponse consumerDataResponse = new ConsumerDataResponse();
		try {		
			log.debug("getConsumerCribData for : ", requestDetailId);
			consumerDataResponse = consumerService.get(requestDetailId);
			log.info(consumerDataResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(consumerDataResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			consumerDataResponse = new ConsumerDataResponse(Status.APPLICATION_ERROR.getStatusCode(), Status.APPLICATION_ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(consumerDataResponse);
		}		
	}

}
