/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 1:23:10 PM
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
@Table(name = "CRIB_CORP_RELATIONSHIP_DETAILS")
public class CorpRelationshipDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "SNO")
    private Integer sNo;
	
	@Column(name = "RELATION_RUID")
    private Long relationRuId;
	
	@Column(name = "ENTITY_ID", length = 30)
    private String entityId;
	
	@Column(name = "NATURE_OF_RELATIONSHIP", length = 255)
    private String natureOfRelationship;
	
	@Column(name = "ENTITY_NAME", length = 255)
    private String entityName;
	
	@Column(name = "FAX_NO", length = 100)
    private String faxNo;
	
	@Column(name = "PHONE_NO", length = 100)
    private String phoneNo;
	
	@Column(name = "ADDRESS", length = 255)
    private String address;
	
	@Column(name = "PROVINCE", length = 100)
    private String province;
	
	@Column(name = "DISTRICT", length = 100)
    private String district;
	
	@Column(name = "CITY", length = 100)
    private String city;
	
	@Column(name = "POSTAL_CODE", length = 50)
    private String postalCode;
	
	@Column(name = "RELATIONSHIP_TYPE", length = 100)
    private String relationshipType;
	
	@Column(name = "RELATION_TYPE_ID", length = 20)
    private String relationTypeId;
	
	@Column(name = "RELATION_NATURE_ID", length = 20)
    private String relationNatureId;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
