/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:49:07 AM
 *  ***************************************
 */

package com.nable.crib.cons.model;

import java.util.Date;

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
@Table(name = "CRIB_CONS_REPORTED_NAMES")
public class ConsReportedNames extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "NAME", length = 255)
    private String name;
	
	@Column(name = "REPORTED_INSTITUTION", length = 255)
    private String reportedInstitution;
	
	@Column(name = "REPORTED_DATE")
    private Date reportedDate;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
