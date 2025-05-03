/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 10:51:54 AM
 *  ***************************************
 */

package com.nable.crib.comn.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_REQUEST_MAIN")
public class RequestMain {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "INSTANCE_ID")
    private Long instanceId;
	
	@Column(name = "SOURCE_TYPE", length = 255)
    private String sourceType;
	
	@Column(name = "REQUEST_CATEGORY", length = 255)
    private String requestCategory;
	
	@Column(name = "REQUEST_TYPE", length = 255)
    private String requestType;
	
	@Column(name = "REQUEST_NUMBER", length = 255)
    private String requestNumber;
	
	@Column(name = "EXTERNAL_REFERENCE", length = 255)
    private String externalReference;
	
	@Column(name = "REQUESTED_DATE")
    protected Date requestedDate;
	
	@Column(name = "INITIATOR_USER_ID", length = 20)
    private String initiatorUserId;
	
	@Column(name = "INITIATOR_NAME", length = 500)
    private String initiatorName;
	
	@Column(name = "INITIATOR_DESIGNATION", length = 255)
    private String initiatorDesignation;
	
	@Column(name = "INITIATOR_DEPARTMENT", length = 255)
    private String initiatorDepartment;
	
	@Column(name = "INITIATOR_BRANCH", length = 255)
    private String initiatorBranch;
	
	@Column(name = "INITIATOR_BUSINESS_UNIT", length = 255)
    private String initiatorBusinessUnit;
	
	@Column(name = "INITIATOR_BRANCH_CODE", length = 20)
    private String initiatorBranchCode;
	
	@Column(name = "INITIATOR_DEPARTMENT_CODE", length = 20)
    private String initiatorDepartmentCode;
	
	@Column(name = "INITIATOR_GRADE", length = 255)
    private String initiatorGrade;
	
	@Column(name = "INITIATOR_EMPLOYEE_CODE", length = 20)
    private String initiatorEmployeeCode;
	
	@Column(name = "INITIATOR_USER_LOGIN_NAME", length = 20)
    private String initiatorUserLoginName;
	
	@Column(name = "IS_APPROVAL_REQUIRED", columnDefinition = "SMALLINT")
	protected Short isApprovalRequired;
	
	@Column(name = "APPROVAL_ACTION", length = 50)
    private String approvalAction;
	
	@Column(name = "APPROVER_USER_ID", length = 50)
    private String approverUserId;
	
	@Column(name = "APPROVED")
    protected Date approved;
	
	@Column(name = "COST_CENTER_CODE", length = 20)
    private String costCenterCode;
	
	@Column(name = "VALIDITY_PERIOD")
    private Short validityPeriod;
	
	@Column(name = "RECORD_COUNT")
    private Long recordCount;
	
	@Column(name = "EWS_ROOT_PATH", length = 800)
    private String ewsRootPath;
	
	@Column(name = "EWS_BUREAU_REFERENCE_NO", length = 50)
    private String ewsBureauReferenceNo;
	
	@Column(name = "EWS_REQUEST_ACK_TIME")
    protected Date ewsRequestAckTime;
	
	@Column(name = "EWS_REQUEST_PROCESSED_TIME")
    protected Date ewsRequestProcessedTime;
	
	@Column(name = "EWS_REQUEST_XML_GENERATED_TIME")
    protected Date ewsRequestXmlGeneratedTime;
	
	@Column(name = "REMARK_1", length = 500)
    private String remark1;
	
	@Column(name = "REMARK_2", length = 500)
    private String remark2;
	
	@Column(name = "REMARK_3", length = 500)
    private String remark3;
	
	@Column(name = "USER_REFERENCE_NUMBER", length = 100)
    private String userReferenceNumber;
	
	@Column(name = "IS_VALID_REQUEST", columnDefinition = "SMALLINT")
	protected Short isValidRequest;
	
	@Column(name = "STATUS", length = 20)
    private String status;
	
	@Column(name = "STATUS_DESCRIPTION", length = 255)
    private String statusDescription;
	
}
