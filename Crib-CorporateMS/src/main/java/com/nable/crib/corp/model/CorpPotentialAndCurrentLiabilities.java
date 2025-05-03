/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:16:54 PM
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
@Table(name = "CRIB_CORP_POTENTIAL_AND_CURRENT_LIABILITIES")
public class CorpPotentialAndCurrentLiabilities extends Auditable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "PCL_HEADER_ID")
    private Long pclHeaderId;
	
	@Column(name = "OWNERSHIP", length = 100)
    private String ownership;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES")
    private Integer noOfCreditFacilities;
	
	@Column(name = "TOTAL_AMOUNT_GRANTED", columnDefinition = "DECIMAL(15,2)")
	private Double totalAmountGranted;
	
	@Column(name = "TOTAL_OUTSTANDING", columnDefinition = "DECIMAL(15,2)")
	private Double totalOutstanding;
	
	@Column(name = "CREDIT_FACILITY_STATUS", length = 50)
    private String creditFacilityStatus;
	
	@Column(name = "BUREAU_CURRENCY", length = 10)
    private String bureauCurrency;
	
}
