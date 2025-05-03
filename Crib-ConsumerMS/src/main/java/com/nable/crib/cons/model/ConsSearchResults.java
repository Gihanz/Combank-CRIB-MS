/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:51:36 AM
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
@Table(name = "CRIB_CONS_SEARCH_RESULTS")
public class ConsSearchResults extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "NAME", length = 8000)
    private String name;
	
	@Column(name = "BUREAU_ID", length = 255)
    private String bureauId;
	
	@Column(name = "IDENTIFIER_ID_SOURCE", length = 255)
    private String identifierIdSource;
	
	@Column(name = "IDENTIFIER_MATCHED", length = 10)
    private String identifierMatched;
	
	@Column(name = "IDENTIFIER_VALUE", length = 50)
    private String identifierValue;
	
	@Column(name = "SURROGATE_ID", length = 255)
    private String surrogateId;
	
	@Column(name = "SURROGATE_MATCHED", length = 10)
    private String surrogateMatched;

}
