/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:57:29 AM
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
@Table(name = "CRIB_CONS_IDENTIFICATION_DETAILS")
public class ConsIdentificationDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SOURCE_ID", length = 10)
    private String sourceId;
	
	@Column(name = "ID_VALUE", length = 30)
    private String idValue;
	
	@Column(name = "ID_DISPLAY_NAME", length = 100)
    private String idDisplayName;
	
	@Column(name = "RUID")
    private Long ruId;

}
