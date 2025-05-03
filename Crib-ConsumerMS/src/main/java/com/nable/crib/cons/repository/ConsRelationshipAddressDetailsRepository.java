/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:57:41 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsRelationshipAddressDetails;

@Repository("ConsRelationshipAddressDetailsRepository")
public interface ConsRelationshipAddressDetailsRepository extends CrudRepository<ConsRelationshipAddressDetails, Long> {

	List<ConsRelationshipAddressDetails> findByRequestDetailId(Long requestDetailId);
}
