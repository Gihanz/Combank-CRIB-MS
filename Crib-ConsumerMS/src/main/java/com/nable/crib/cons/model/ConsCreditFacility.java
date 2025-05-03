/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 10, 2022 - 10:58:22 PM
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
@Table(name = "CRIB_CONS_CREDIT_FACILITY")
public class ConsCreditFacility extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "RELATION_ID")
    private Integer relationId;
	
	@Column(name = "SERIAL_NUMBER")
    private Integer serialNumber;

}
