/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:00:14 AM
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
@Table(name = "CRIB_CONS_INQUIRIES_BY_SUBJECT")
public class ConsInquiriesBySubject extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "INQUIRY_DATE")
    private Date inquiryDate;
	
	@Column(name = "REASON_ID")
    private Integer reasonId;
		
	@Column(name = "REASON", length = 255)
    private String reason;

}
