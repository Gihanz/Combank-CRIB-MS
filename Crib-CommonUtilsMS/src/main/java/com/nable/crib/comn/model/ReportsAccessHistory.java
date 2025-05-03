/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 10:33:16 AM
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
@Table(name = "CRIB_REPORTS_ACCESS_HISTORY")
public class ReportsAccessHistory {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "EMPLOYEE_CODE", length = 20)
    private String employeeCode;
	
	@Column(name = "USER_NAME", length = 20)
    private String userName;
	
	@Column(name = "NAME", length = 255)
    private String name;
	
	@Column(name = "BUSINESS_UNIT", length = 255)
    private String businessUnit;
	
	@Column(name = "BUSINESS_UNIT_CODE", length = 20)
    private String businessUnitCode;
	
	@Column(name = "DESIGNATION", length = 255)
    private String designation;
	
	@Column(name = "DESIGNATION_CODE", length = 20)
    private String designationCode;
	
	@Column(name = "GRADE", length = 255)
    private String grade;
	
	@Column(name = "EMPLOYMENT_STATUS", length = 20)
    private String employmentStatus;
	
	@Column(name = "ACTION", length = 20)
    private String action;
	
	@Column(name = "ACTION_ON")
    protected Date actionOn;
	
	@Column(name = "REQUEST_ID")
    private Long requestId;
	
	@Column(name = "REMARKS", length = 255)
    private String remarks;
	
	@Column(name = "SUBJECT", length = 2)
    private String subject;
	
	@Column(name = "BRANCH_CODE", length = 20)
    private String branchCode;
	
	@Column(name = "BRANCH", length = 255)
    private String branch;
	
	@Column(name = "DEPARTMENT_CODE", length = 20)
    private String departmentCode;
	
	@Column(name = "DEPARTMENT", length = 255)
    private String department;
	
}
