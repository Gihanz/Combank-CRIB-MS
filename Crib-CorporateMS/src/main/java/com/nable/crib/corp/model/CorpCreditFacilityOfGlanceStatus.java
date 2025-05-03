/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:01:57 PM
 *  ***************************************
 */

package com.nable.crib.corp.model;

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
@Table(name = "CRIB_CORP_CREDIT_FACILITY_OF_GLANCE_STATUS")
public class CorpCreditFacilityOfGlanceStatus extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "CATALOGUE_CODE", length = 10)
    private String catalogueCode;
	
	@Column(name = "CATALOGUE_VAL_ENGLISH", length = 100)
    private String catalogueValEnglish;
	
	@Column(name = "STATUS", length = 20)
    private String status;
	
	@Column(name = "ARREARS_DAYS_0")
    private Integer arrearsDays0;
	
	@Column(name = "ARREARS_DAYS_1_30")
    private Integer arrearsDays1_30;
	
	@Column(name = "ARREARS_DAYS_31_60")
    private Integer arrearsDays31_60;
	
	@Column(name = "ARREARS_DAYS_61_90")
    private Integer arrearsDays61_90;
	
	@Column(name = "ARREARS_DAYS_90")
    private Integer arrearsDays90;

}
