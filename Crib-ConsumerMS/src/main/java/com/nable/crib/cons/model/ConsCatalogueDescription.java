/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 10, 2022 - 10:47:15 PM
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
@Table(name = "CRIB_CONS_CATALOGUE_DESCRIPTION")
public class ConsCatalogueDescription extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "CATG_LABEL", length = 255)
    private String catgLabel;
	
	@Column(name = "CATG_VALUE", length = 255)
    private String catgValue;
	
}
