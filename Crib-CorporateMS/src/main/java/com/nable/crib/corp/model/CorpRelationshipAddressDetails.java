/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:21:02 PM
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
@Table(name = "CRIB_CORP_RELATIONSHIP_ADDRESS_DETAILS")
public class CorpRelationshipAddressDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "RELATIONSHIP_DETAILS_ID")
    private Long relationshipDetailsId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "RELATION_RUID")
    private Long relationRuId;
	
	@Column(name = "ADDRESS", length = 255)
    private String address;
	
	@Column(name = "PROVINCE", length = 20)
    private String province;
	
	@Column(name = "DISTRICT", length = 20)
    private String district;
	
	@Column(name = "CITY", length = 100)
    private String city;
	
	@Column(name = "PHONE_NUMBER", length = 50)
    private String phoneNumber;
	
	@Column(name = "FAX_NUMBER", length = 100)
    private String faxNumber;
	
	@Column(name = "POSTAL_CODE", length = 10)
    private String postalCode;

}
