/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 12:08:02 AM
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
@Table(name = "CRIB_CONS_DEMOGRAPHIC_DETAILS")
public class ConsDemographicDetails extends Auditable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
    private Long id;
	
	@Column(name = "REQUEST_DETAIL_ID")
    private Long requestDetailId;
	
	@Column(name = "NAME", length = 500)
    private String name;
	
	@Column(name = "DOB")
    private Date dob;
	
	@Column(name = "GENDER", length = 10)
    private String gender;
	
	@Column(name = "SPOUSE_NAME", length = 255)
    private String spouseName;
	
	@Column(name = "CITIZENSHIP", length = 10)
    private String citizenship;
	
	@Column(name = "MARITAL_STATUS", length = 10)
    private String maritalStatus;
	
	@Column(name = "TELEPHONE_AREA_CODE", length = 100)
    private String telephoneAreaCode;
	
	@Column(name = "TELEPHONE_NUMBER", length = 255)
    private String telephoneNumber;
	
	@Column(name = "MOBILE_NUMBER", length = 255)
    private String mobileNumber;
	
	@Column(name = "EMAIL_ID", length = 255)
    private String emailId;
	
	@Column(name = "RUID")
    private Long ruId;
	
	@Column(name = "NIC_NUMBER", length = 30)
    private String nicNumber;
	
	@Column(name = "NIC_RUID")
    private Long nicRuId;
	
	@Column(name = "PASSPORT_NUMBER", length = 30)
    private String passportNumber;
	
	@Column(name = "PAS_RUID")
    private Long pasRuId;
	
	@Column(name = "RTO_RUID")
    private Long rtoRuId;
	
	@Column(name = "BLOCK_FLAG", columnDefinition = "SMALLINT")
	protected Short blockFlag;

}
