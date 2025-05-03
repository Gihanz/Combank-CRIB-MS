/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 2:00:06 PM
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
@Table(name = "CRIB_CORP_SETTLED_CREDIT_FACILITIES_SUMMARY")
public class CorpSettledCreditFacilitiesSummary extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "CURRENCY", length = 10)
    private String currency;
	
	@Column(name = "OWNERSHIP", length = 100)
    private String ownership;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_1")
    private Integer noOfCreditFacilities1;
	
	@Column(name = "AMOUNT_GRANTED_1", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted1;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_2")
    private Integer noOfCreditFacilities2;
	
	@Column(name = "AMOUNT_GRANTED_2", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted2;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_3")
    private Integer noOfCreditFacilities3;
	
	@Column(name = "AMOUNT_GRANTED_3", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted3;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_4")
    private Integer noOfCreditFacilities4;
	
	@Column(name = "AMOUNT_GRANTED_4", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted4;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_5")
    private Integer noOfCreditFacilities5;
	
	@Column(name = "AMOUNT_GRANTED_5", columnDefinition = "DECIMAL(15,2)")
	private Double amountGranted5;
	
	@Column(name = "RP", length = 100)
    private String rp;
	
	@Column(name = "YEAR1", length = 100)
    private String year1;
	
	@Column(name = "YEAR2", length = 100)
    private String year2;
	
	@Column(name = "YEAR3", length = 100)
    private String year3;
	
	@Column(name = "YEAR4", length = 100)
    private String year4;
	
	@Column(name = "YEAR5", length = 100)
    private String year5;
	
	@Column(name = "IS_COLORING_NEEDED", length = 10)
    private String isColoringNeeded;

}
