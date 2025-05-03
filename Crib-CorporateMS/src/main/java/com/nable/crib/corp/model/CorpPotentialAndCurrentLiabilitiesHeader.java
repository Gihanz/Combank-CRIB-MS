/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:19:57 PM
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
@Table(name = "CRIB_CORP_POTENTIAL_AND_CURRENT_LIABILITIES_HEADER")
public class CorpPotentialAndCurrentLiabilitiesHeader extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "BUREAU_CURRENCY", length = 10)
    private String bureauCurrency;
	
	@Column(name = "PRIORITY")
    private Integer priority;
	
	@Column(name = "MONTHYEAR", length = 255)
    private String monthyear;

}
