/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 10, 2022 - 2:11:35 PM
 *  ***************************************
 */

package com.nable.crib.corp.model.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable {

	@Column(name = "IS_ACTIVE", columnDefinition = "SMALLINT", nullable = false)
	protected int isActive;
	
	@CreatedBy
	@Column(name = "CREATED_BY", nullable = false, length = 100)
	protected String createdBy;

	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED", nullable = false)
    protected Date created;

	@LastModifiedBy
	@Column(name = "MODIFIED_BY", length = 100)
    protected String modifiedBy;

	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MODIFIED")
    protected Date modified;

}
