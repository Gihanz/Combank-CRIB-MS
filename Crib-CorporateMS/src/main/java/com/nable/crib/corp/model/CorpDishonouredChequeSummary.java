/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:08:14 PM
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
@Table(name = "CRIB_CORP_DISHONOURED_CHEQUE_SUMMARY")
public class CorpDishonouredChequeSummary extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
		
	@Column(name = "NUMBER_OF_CHEQUES")
    private Integer numberOfCheques;
	
	@Column(name = "CHEQUE_VALUE", columnDefinition = "DECIMAL(15,2)")
	private Double chequeValue;

}
