/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:58:46 PM
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
@Table(name = "CRIB_CORP_SETTLED_CREDIT_FACILITIES_DETAILS")
public class CorpSettledCreditFacilitiesDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "CURRENCY", length = 10)
    private String currency;
	
	@Column(name = "CF_TYPE", length = 10)
    private String cfType;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_AS_BORROWER")
    private Integer noOfCreditFacilitiesAsBorrower;
	
	@Column(name = "AMOUNT_GRANTED_AS_BORROWER", columnDefinition = "DECIMAL(15,2)")
	private Double amountGrantedAsBorrower;
	
	@Column(name = "NO_OF_CREDIT_FACILITIES_AS_GUARANTOR")
    private Integer noOfCreditFacilitiesAsGuarantor;
	
	@Column(name = "AMOUNT_GRANTED_AS_GUARANTOR", columnDefinition = "DECIMAL(15,2)")
	private Double amountGrantedAsGuarantor;

}
