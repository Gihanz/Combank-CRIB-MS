/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 12:12:34 AM
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
@Table(name = "CRIB_REQUEST_STATUS")
public class RequestStatus extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "STATUS_CODE", length = 5)
    private String statusCode;
	
	@Column(name = "STATUS", length = 50)
    private String status;
	
	@Column(name = "STATUS_DESCRIPTION", length = 255)
    private String statusDescription;

}
