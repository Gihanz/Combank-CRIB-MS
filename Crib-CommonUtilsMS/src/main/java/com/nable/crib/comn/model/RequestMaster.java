/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 11:09:09 AM
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
@Table(name = "CRIB_REQUEST_MASTER")
public class RequestMaster {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_MAIN_ID")
    private Long requestMainId;
	
	@Column(name = "CITIZENSHIP", length = 20)
    private String citizenship;
	
	@Column(name = "CUSTOMER_ID", length = 100)
    private String customerId;
	
	@Column(name = "SUBJECT_TYPE", length = 20)
    private String subjectType;
	
	@Column(name = "NAME", length = 500)
    private String name;
	
	@Column(name = "GENDER", length = 20)
    private String gender;
	
	@Column(name = "DOB")
    protected Date dob;
	
	@Column(name = "REASON_FOR_REPORTING", length = 20)
    private String reasonForReporting;
	
	@Column(name = "CURRENCY", length = 20)
    private String currency;
	
	@Column(name = "REQUESTED_AMOUNT", precision=10, scale=2)
	private Double requestedAmount;
			
	@Column(name = "PRODUCT_TYPE", length = 20)
    private String productType;
	
	@Column(name = "APPLICATION_DATE")
    protected Date applicationDate;
	
	@Column(name = "IS_APPLICATION_OR_CONSENT_REQUIRED", columnDefinition = "SMALLINT")
	protected Short isApplicationOrConsentRequired;
	
	@Column(name = "IS_NEW_CRIB_REPORT_REQUIRED", columnDefinition = "SMALLINT")
	protected Short isNewCribReportRequired;
	
	@Column(name = "DATA_PROVIDER_BRANCH_ID", length = 20)
    private String dataProviderBranchId;
	
	@Column(name = "APPLICATION_REFERENCE", length = 100)
    private String applicationReference;
	
	@Column(name = "COST_CENTER_CODE", length = 10)
    private String costCenterCode;
	
	@Column(name = "ACCOUNT_NUMBER", length = 20)
    private String accountNumber;
	
	@Column(name = "IS_CIF_AVAILABLE", columnDefinition = "SMALLINT")
	protected Short isCifAvailable;
	
	@Column(name = "CIF_CODE", length = 100)
    private String cifCode;
	
	@Column(name = "IS_SCORE_REPORT_REQUIRED", columnDefinition = "SMALLINT")
	protected Short isScoreReportRequired;
	
	@Column(name = "SIC_CODE", length = 100)
    private String sicCode;
	
	@Column(name = "REQUESTOR_USER_ID", length = 20)
    private String requestorUserId;
	
	@Column(name = "REQUESTOR_NAME", length = 500)
    private String requestorName;
	
	@Column(name = "REQUESTOR_DESIGNATION", length = 255)
    private String requestorDesignation;
	
	@Column(name = "REQUESTOR_DEPARTMENT", length = 255)
    private String requestorDepartment;
	
	@Column(name = "REQUESTOR_BRANCH", length = 255)
    private String requestorBranch;
	
	@Column(name = "REQUESTOR_BUSINESS_UNIT", length = 255)
    private String requestorBusinessUnit;
	
	@Column(name = "REQUESTOR_BRANCH_CODE", length = 10)
    private String requestorBranchCode;
	
	@Column(name = "REQUESTOR_DEPARTMENT_CODE", length = 10)
    private String requestorDepartmentCode;
	
	@Column(name = "REQUESTOR_GRADE", length = 255)
    private String requestorGrade;
	
	@Column(name = "REQUESTOR_EMPLOYEE_CODE", length = 10)
    private String requestorEmployeeCode;
	
	@Column(name = "STATUS", length = 20)
    private String status;
	
	@Column(name = "STATUS_DESCRIPTION", length = 255)
    private String statusDescription;
	
}
