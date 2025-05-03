/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 3:47:29 PM
 *  ***************************************
 */

package com.nable.crib.comn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nable.crib.comn.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_REQUEST_ERROR")
public class RequestError extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "ERROR_CODE", length = 50)
    private String errorCode;
	
	@Column(name = "ERROR_DESCRIPTION", length = 255)
    private String errorDescription;

}
