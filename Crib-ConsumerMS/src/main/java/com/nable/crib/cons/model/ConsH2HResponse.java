/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 12:29:38 AM
 *  ***************************************
 */

package com.nable.crib.cons.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.nable.crib.cons.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_CONS_H2H_RESPONSE")
public class ConsH2HResponse extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Lob
	@Column(name = "RESPONSE")
    private byte[] response;

}
