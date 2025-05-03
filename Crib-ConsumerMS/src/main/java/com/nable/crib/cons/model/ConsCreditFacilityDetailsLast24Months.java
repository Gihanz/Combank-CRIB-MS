/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 10, 2022 - 11:30:22 PM
 *  ***************************************
 */

package com.nable.crib.cons.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nable.crib.cons.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_CONS_CREDIT_FACILITY_DETAILS_LAST_24_MONTHS")
public class ConsCreditFacilityDetailsLast24Months extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "ROWNUM")
    private Integer rowNum;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "ACTIVE_ROOT_ID")
    private Long activeRootId;
	
	@Column(name = "TO_MONTH_YEAR", length = 100)
    private String toMonthYear;
	
	@Column(name = "FROM_MONTH_YEAR", length = 100)
    private String fromMonthYear;
	
	@Column(name = "MONTH", length = 100)
    private String month;
	
	@Column(name = "CURRENT_BALANCE", columnDefinition = "DECIMAL(15,2)")
	private Double currentBalance;
	
	@Column(name = "AMOUNT_OVERDUE", columnDefinition = "DECIMAL(15,2)")
	private Double amountOverdue;
	
	@Column(name = "ASSET_CLASSIFICATION", length = 100)
    private String assetClassification;
	
	@Column(name = "MAXIMUM_NUMBER_OF_DAYS_OVERDUE", length = 20)
    private String maximumNumberOfDaysOverdue;

}
