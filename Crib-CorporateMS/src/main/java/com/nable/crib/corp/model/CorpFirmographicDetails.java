/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:13:55 PM
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
@Table(name = "CRIB_CORP_FIRMOGRAPHIC_DETAILS")
public class CorpFirmographicDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "NAME", length = 500)
    private String name;
	
	@Column(name = "DATE_OF_REGISTRATION")
    private Date dateOfRegistration;
	
	@Column(name = "AREA_CODE", length = 50)
    private String areaCode;
	
	@Column(name = "TELEPHONE_NUMBER", length = 255)
    private String telephoneNumber;
	
	@Column(name = "FAX_NUMBER", length = 255)
    private String faxNumber;
	
	@Column(name = "LEGAL_CONSTITUTION", length = 10)
    private String legalConstitution;
	
	@Column(name = "ECONOMIC_ACTIVITY1", length = 20)
    private String economicActivity1;
	
	@Column(name = "ECONOMIC_ACTIVITY2", length = 20)
    private String economicActivity2;
	
	@Column(name = "ECONOMIC_ACTIVITY3", length = 20)
    private String economicActivity3;
	
	@Column(name = "URL", length = 255)
    private String url;
	
	@Column(name = "CRA", length = 50)
    private String cra;
	
	@Column(name = "CRA_RUID")
    private Long craRuId;
	
	@Column(name = "VAT", length = 50)
    private String vat;
	
	@Column(name = "VAT_RUID")
    private Long vatRuId;
	
	@Column(name = "RUID")
    private Long ruId;
	
	@Column(name = "LAST_REPORTED_DATE")
    private Date lastReportedDate;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
