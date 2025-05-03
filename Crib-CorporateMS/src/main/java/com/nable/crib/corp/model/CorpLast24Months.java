/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:02:53 PM
 *  ***************************************
 */

package com.nable.crib.corp.model;

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
@Table(name = "CRIB_CORP_LAST_24_MONTHS")
public class CorpLast24Months extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "RELATION_ID")
    private Integer relationId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "ROWNUM")
    private Integer rowNum;
	
	@Column(name = "TO_MONTH_YEAR", length = 20)
    private String toMonthYear;
	
	@Column(name = "FROM_MONTH_YEAR", length = 20)
    private String fromMonthYear;
	
	@Column(name = "ACTIVE_ROOT_ID")
    private Long activeRootId;
	
	@Column(name = "BUREAU_CURRENCY", length = 10)
    private String bureauCurrency;
	
	@Column(name = "BUREAU_ACC_STATUS", length = 10)
    private String bureauAccStatus;
	
	@Column(name = "RANK")
    private Integer rank;
	
}
