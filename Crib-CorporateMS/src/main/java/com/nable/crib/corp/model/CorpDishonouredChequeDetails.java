/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:04:32 PM
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
@Table(name = "CRIB_CORP_DISHONOURED_CHEQUE_DETAILS")
public class CorpDishonouredChequeDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
		
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "INSTITUTION_ID", length = 100)
    private String institutionId;
	
	@Column(name = "INSTITUTION_AND_BRANCH", length = 100)
    private String institutionAndBranch;
	
	@Column(name = "ACCOUNT_NUMBER", length = 100)
    private String accountNumber;
	
	@Column(name = "CHEQUE_NUMBER", length = 100)
    private String chequeNumber;
	
	@Column(name = "CHEQUE_AMOUNT", columnDefinition = "DECIMAL(15,2)")
	private Double chequeAmount;
	
	@Column(name = "DATE_DISHONOURED")
    private Date dateDishonoured;
	
	@Column(name = "REASON", length = 255)
    private String reason;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
