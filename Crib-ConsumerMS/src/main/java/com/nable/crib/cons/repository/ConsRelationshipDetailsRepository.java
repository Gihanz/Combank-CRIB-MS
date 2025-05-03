/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:57:59 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsRelationshipDetails;

@Repository("ConsRelationshipDetailsRepository")
public interface ConsRelationshipDetailsRepository extends CrudRepository<ConsRelationshipDetails, Long> {

	List<ConsRelationshipDetails> findByRequestDetailId(Long requestDetailId);
}
