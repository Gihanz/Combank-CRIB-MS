/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 11:45:59 AM
 *  ***************************************
 */

package com.nable.crib.corp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nable.crib.corp.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_CORP_CREDIT_FACILITY_DETAILS")
public class CorpCreditFacilityDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "RELATION_ID")
    private Integer relationId;
	
	@Column(name = "ROWNUM")
    private Integer rowNum;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "ACCOUNT_STATUS", length = 10)
    private String accountStatus;
	
	@Column(name = "DISPUTE", length = 100)
    private String dispute;
	
	@Column(name = "BUREAU_GUARANTEE_COVERAGE", length = 10)
    private String bureauGuaranteeCoverage;
	
	@Column(name = "BUREAU_SECURITY_COVERAGE", length = 10)
    private String bureauSecurityCoverage;
	
	@Column(name = "INTEREST_OUTSTANDING", columnDefinition = "DECIMAL(15,2)")
	private Double interestOutstanding;
	
	@Column(name = "MAX_NUM_DAYS_DUE")
    private Integer maxNumDaysDue;
	
	@Column(name = "INSTALLMENT_AMOUNT", columnDefinition = "DECIMAL(15,2)")
	private Double installmentAmount;
	
	@Column(name = "LOAN_TYPE", length = 10)
    private String loanType;
	
	@Column(name = "AMOUNT_GRANTED", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted;
	
	@Column(name = "OWNERSHIP", length = 10)
    private String ownership;
	
	@Column(name = "REPAYMENT_TYPE", length = 10)
    private String repaymentType;
	
	@Column(name = "CURRENCY", length = 10)
    private String currency;
	
	@Column(name = "PRIORITY")
    private Integer priority;
	
	@Column(name = "PRIORITY2")
    private Integer priority2;
	
	@Column(name = "FIRST_DISBURSE_DATE")
    private Date firstDisburseDate;
	
	@Column(name = "LATEST_PAYMENT_DATE")
    private Date latestPaymentDate;
	
	@Column(name = "SANCTION_DATE")
    private Date sanctionDate;
	
	@Column(name = "CURRENT_BALANCE", columnDefinition = "DECIMAL(15,2)")
	private Double currentBalance;
	
	@Column(name = "ARREARS_AMOUNT", columnDefinition = "DECIMAL(15,2)")
	private Double arrearsAmount;
	
	@Column(name = "LEGAL_ACTION", length = 100)
    private String legalAction;
	
	@Column(name = "END_DATE")
    private Date endDate;
	
	@Column(name = "PURPOSE", length = 100)
    private String purpose;
	
	@Column(name = "RESTRUCTURING_DATE")
    private Date restructuringDate;
	
	@Column(name = "AMOUNT_WRITTEN_OFF", columnDefinition = "DECIMAL(15,2)")
	private Double amountWrittenOff;
	
	@Column(name = "NUMBER_OF_INSTALLMENTS")
    private Integer numberOfInstallments;
	
	@Column(name = "PRIMARY_ROOT_ID")
    private Long primaryRootId;
	
	@Column(name = "ACTIVE_ROOT_ID")
    private Long activeRootId;
	
	@Column(name = "RUID")
    private Long ruId;
	
	@Column(name = "REPORTED_DATE")
    private Date reportedDate;
	
	@Column(name = "PROVIDER_SOURCE", length = 100)
    private String providerSource;
	
	@Column(name = "PROVIDER_BRANCH", length = 100)
    private String providerBranch;
	
	@Column(name = "CATEGORY_DESC", length = 100)
    private String categoryDesc;
	
	@Column(name = "INSTITUTION_BRANCH", length = 100)
    private String institutionBranch;
	
	@Column(name = "REPAY_TYPE", length = 10)
    private String repayType;
	
	@Column(name = "SI_INST_NAME", length = 100)
    private String siInstName;
	
	@Column(name = "SI_BRNH_NAME", length = 100)
    private String siBrnhName;
	
	@Column(name = "INSTITUTION_CATEGORY", length = 10)
    private String institutionCategory;
	
	@Column(name = "DISPUTE_ID", length = 10)
    private String disputeId;
	
	@Column(name = "COVERAGE", length = 100)
    private String coverage;
	
	@Column(name = "CF_STATUS", length = 10)
    private String cfStatus;
	
	@Column(name = "CF_TYPE", length = 10)
    private String cfType;
	
	@Column(name = "RANK")
    private Integer rank;
	
	@Column(name = "SECUR_TYPE", length = 10)
    private String securType;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;
	
	@Column(name = "OWNERSHIP_INDICATOR", length = 10)
    private String ownershipIndicator;
	
}
