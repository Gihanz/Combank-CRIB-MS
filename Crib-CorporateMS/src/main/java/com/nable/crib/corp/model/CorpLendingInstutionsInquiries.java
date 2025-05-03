/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:05:19 PM
 *  ***************************************
 */

package com.nable.crib.corp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nable.crib.corp.model.audit.Auditable;

import lombok.Data;

@Data
@Entity
@Table(name = "CRIB_CORP_LENDING_INSTUTIONS_INQUIRIES")
public class CorpLendingInstutionsInquiries extends Auditable {
	
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
	
	@Column(name = "INSTITUTION_NAME", length = 255)
    private String institutionName;
	
	@Column(name = "PRODUCT_NAME", length = 255)
    private String productName;
	
	@Column(name = "CURRENCY", length = 10)
    private String currency;
	
	@Column(name = "AMOUNT", columnDefinition = "DECIMAL(15,2)")
	private Double amount;
	
	@Column(name = "CF_TYPE", length = 255)
    private String cfType;
	
	@Column(name = "INSTITUION_CATEGORY", length = 255)
    private String instituionCategory;

}
