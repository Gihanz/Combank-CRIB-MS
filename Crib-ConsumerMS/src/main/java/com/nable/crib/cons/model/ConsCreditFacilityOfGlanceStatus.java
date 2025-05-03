/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:02:10 AM
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
@Table(name = "CRIB_CONS_CREDIT_FACILITY_OF_GLANCE_STATUS")
public class ConsCreditFacilityOfGlanceStatus extends Auditable {
	
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
	
	@Column(name = "MONTHYEAR", length = 255)
    private String monthyear;
	
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
