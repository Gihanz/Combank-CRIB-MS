/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:08:23 PM
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
@Table(name = "CRIB_CORP_MAILING_ADDRESSES")
public class CorpMailingAddresses extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "ADDRESS_TYPE", length = 100)
    private String addressType;
	
	@Column(name = "BUILDING_NUMBER", length = 100)
    private String buildingNumber;
	
	@Column(name = "COUNTRY", length = 100)
    private String country;
	
	@Column(name = "ROAD", length = 255)
    private String road;
	
	@Column(name = "CITY", length = 100)
    private String city;
	
	@Column(name = "LAST_REPORTED_DATE")
    private Date lastReportedDate;
	
	@Column(name = "AREA", length = 100)
    private String area;
	
	@Column(name = "DISTRICT", length = 100)
    private String district;
	
	@Column(name = "PROVINCE", length = 100)
    private String province;
	
	@Column(name = "DOOR_NUMBER", length = 100)
    private String doorNumber;
	
	@Column(name = "ADDRESS_VALUE", length = 255)
    private String addressValue;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
