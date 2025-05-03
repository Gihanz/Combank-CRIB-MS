package com.nable.crib.comn.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nable.crib.comn.model.ReportsAccessHistory;
import com.nable.crib.comn.model.RequestDetail;
import com.nable.crib.comn.model.RequestDetailHistory;
import com.nable.crib.comn.model.RequestError;
import com.nable.crib.comn.model.RequestMain;
import com.nable.crib.comn.model.RequestMaster;
import com.nable.crib.comn.request.RequestDetailRequest;
import com.nable.crib.comn.response.CommonResponse;
import com.nable.crib.comn.response.RequestDetailResponse;
import com.nable.crib.comn.response.RequestErrorResponse;
import com.nable.crib.comn.response.RequestMainResponse;
import com.nable.crib.comn.response.RequestMasterResponse;
import com.nable.crib.comn.response.Status;
import com.nable.crib.comn.service.ReportsAccessHistoryService;
import com.nable.crib.comn.service.RequestDetailHistoryService;
import com.nable.crib.comn.service.RequestDetailService;
import com.nable.crib.comn.service.RequestErrorService;
import com.nable.crib.comn.service.RequestMainService;
import com.nable.crib.comn.service.RequestMasterService;
import com.nable.crib.comn.util.ErrorDescription;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CommonUtilsController {
	
	static Logger log = LoggerFactory.getLogger(CommonUtilsController.class);
			
	@Autowired
    private RequestDetailService requestDetailService;
	
	@Autowired
    private RequestErrorService requestErrorService;
	
	@Autowired
    private ReportsAccessHistoryService reportsAccessHistoryService;
	
	@Autowired
    private RequestDetailHistoryService requestDetailHistoryService;
	
	@Autowired
    private RequestMainService requestMainService;
	
	@Autowired
    private RequestMasterService requestMasterService;
	
	@Operation(summary = "Test Working Status", description = "Test Working Status by Get")
	@GetMapping("/test")
	public String testService() {
		return "Service working fine";
	}
	
	@Operation(summary = "Get Request Details", description = "Get Request Details from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestDetailResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getRequestDetail/{requestDetailId}")
	public ResponseEntity<RequestDetailResponse> getRequestDetail(@PathVariable(name = "requestDetailId") Long requestDetailId) {
		
		RequestDetailResponse requestDetailResponse = new RequestDetailResponse();
		try {
			log.debug("getRequestDetail for : ", requestDetailId);
			requestDetailResponse = requestDetailService.get(requestDetailId);
			log.info(requestDetailResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestDetailResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestDetailResponse = new RequestDetailResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestDetailResponse);
		}		
	}
	
	@Operation(summary = "Insert Request Details", description = "Insert Request Details")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestDetailResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addRequestDetail")
	public ResponseEntity<RequestDetailResponse> addRequestDetail(@RequestBody RequestDetail requestObj) {
		
		RequestDetailResponse requestDetailResponse = new RequestDetailResponse();
		try {
			log.debug("addRequestDetail for : ", requestObj.getId());
			requestDetailResponse = requestDetailService.save(requestObj);
			log.info(requestDetailResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestDetailResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestDetailResponse = new RequestDetailResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestDetailResponse);
		}
	}
	
	@Operation(summary = "Update Request Details", description = "Update Request Details by requestDetailId")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PutMapping("/updateRequestDetail/{requestDetailId}")
	public ResponseEntity<CommonResponse> updateRequestDetail(@PathVariable(name = "requestDetailId") Long requestDetailId, @RequestBody RequestDetailRequest requestObj) {
		
		CommonResponse commonResponse = new CommonResponse();
		try {
			log.debug("updateRequestDetail for : ", requestDetailId);
			commonResponse = requestDetailService.update(requestDetailId, requestObj);
			log.info(commonResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			commonResponse = new CommonResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
		}
	}
	
	@Operation(summary = "Get Request Errors", description = "Get Request Errors from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestErrorResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getRequestError/{requestDetailId}")
	public ResponseEntity<RequestErrorResponse> getRequestError(@PathVariable(name = "requestDetailId") Long requestDetailId) {
		
		RequestErrorResponse requestErrorResponse = new RequestErrorResponse();
		try {	
			log.debug("getRequestErrors for : ", requestDetailId);
			requestErrorResponse = requestErrorService.get(requestDetailId);
			log.info(requestErrorResponse.getMessage());		
			
			return ResponseEntity.status(HttpStatus.OK).body(requestErrorResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestErrorResponse = new RequestErrorResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
						
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestErrorResponse);
		}	
	}
	
	@Operation(summary = "Insert Request Errors", description = "Insert Request Errors")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addRequestError")
	public ResponseEntity<CommonResponse> addRequestError(@RequestBody List<RequestError> requestObj) {
		
		CommonResponse commonResponse = new CommonResponse();
		try {			
			for(RequestError ReqEr : requestObj) {
	            log.debug("addRequestErrors for : ", ReqEr.getId());
	        }
			commonResponse = requestErrorService.save(requestObj);
			log.info(commonResponse.getMessage());

			return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			commonResponse = new CommonResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
		}
	}
	
	@Operation(summary = "Insert Reports Access History", description = "Insert Reports Access History")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addReportsAccessHistory")
	public ResponseEntity<CommonResponse> addReportsAccessHistory(@RequestBody ReportsAccessHistory requestObj) {
		
		CommonResponse commonResponse = new CommonResponse();
		try {
			log.debug("addReportsAccessHistory for : ", requestObj.getId());
			commonResponse = reportsAccessHistoryService.save(requestObj);
			log.info(commonResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			commonResponse = new CommonResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
		}
	}
	
	@Operation(summary = "Insert Request Detail History", description = "Insert Request Detail History")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = CommonResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addRequestDetailHistory")
	public ResponseEntity<CommonResponse> addRequestDetailHistory(@RequestBody RequestDetailHistory requestObj) {
		
		CommonResponse commonResponse = new CommonResponse();
		try {
			log.debug("addRequestDetailHistory for : ", requestObj.getId());
			commonResponse = requestDetailHistoryService.save(requestObj);
			log.info(commonResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			commonResponse = new CommonResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(commonResponse);
		}
	}
	
	@Operation(summary = "Get Request Main", description = "Get Request Main from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestMainResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getRequestMain/{id}")
	public ResponseEntity<RequestMainResponse> getRequestMain(@PathVariable(name = "id") Long id) {
		
		RequestMainResponse requestMainResponse = new RequestMainResponse();
		try {
			log.debug("getRequestMain for : ", id);
			requestMainResponse = requestMainService.get(id);
			log.info(requestMainResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestMainResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestMainResponse = new RequestMainResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestMainResponse);
		}		
	}
	
	@Operation(summary = "Insert Request Main", description = "Insert Request Main")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestMainResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addRequestMain")
	public ResponseEntity<RequestMainResponse> addRequestMain(@RequestBody RequestMain requestObj) {
		
		RequestMainResponse requestMainResponse = new RequestMainResponse();
		try {
			log.debug("addRequestMain for : ", requestObj.getId());
			requestMainResponse = requestMainService.save(requestObj);
			log.info(requestMainResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestMainResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestMainResponse = new RequestMainResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestMainResponse);
		}
	}
	
	@Operation(summary = "Get Request Master", description = "Get Request Master from DB")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestMasterResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@GetMapping("/getRequestMaster/{id}")
	public ResponseEntity<RequestMasterResponse> getRequestMaster(@PathVariable(name = "id") Long id) {
		
		RequestMasterResponse requestMasterResponse = new RequestMasterResponse();
		try {
			log.debug("getRequestMaster for : ", id);
			requestMasterResponse = requestMasterService.get(id);
			log.info(requestMasterResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestMasterResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestMasterResponse = new RequestMasterResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestMasterResponse);
		}		
	}
	
	@Operation(summary = "Insert Request Master", description = "Insert Request Master")
	@ApiResponses(value = {
	        @ApiResponse(responseCode = "200", description = "Successful Operation", content = @Content(schema = @Schema(implementation = RequestMasterResponse.class))),
	        @ApiResponse(responseCode = "400", description = ErrorDescription.INPUT_PARAMETERS_ARE_NOT_VALID),
            @ApiResponse(responseCode = "500", description = ErrorDescription.INTERNAL_SERVER_ERROR)})
	@PostMapping("/addRequestMaster")
	public ResponseEntity<RequestMasterResponse> addRequestMaster(@RequestBody RequestMaster requestObj) {
		
		RequestMasterResponse requestMasterResponse = new RequestMasterResponse();
		try {
			log.debug("addRequestMaster for : ", requestObj.getId());
			requestMasterResponse = requestMasterService.save(requestObj);
			log.info(requestMasterResponse.getMessage());
			
			return ResponseEntity.status(HttpStatus.OK).body(requestMasterResponse);
			
		}catch (Exception ex) {
			log.error("Error occurred : ", ex);
			requestMasterResponse = new RequestMasterResponse(Status.ERROR.getStatusCode(), Status.ERROR.getStatus(), "Error occurred : "+ex.toString());
			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(requestMasterResponse);
		}
	}

}
