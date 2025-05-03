/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:52:34 AM
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
@Table(name = "CRIB_CONS_EMPLOYMENT_DETAILS")
public class ConsEmploymentDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "EMPLOYMENT", length = 10)
    private String employment;
	
	@Column(name = "EMPLOYER_NAME", length = 255)
    private String employerName;
	
	@Column(name = "BUSINESS_NAME", length = 255)
    private String businessName;
	
	@Column(name = "BR_NUMBER", length = 30)
    private String brNumber;
	
	@Column(name = "REPORTED_DATE")
    private Date reportedDate;
	
	@Column(name = "PROFESSION", length = 255)
    private String profession;
	
	@Column(name = "RUID")
    private Long ruId;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
