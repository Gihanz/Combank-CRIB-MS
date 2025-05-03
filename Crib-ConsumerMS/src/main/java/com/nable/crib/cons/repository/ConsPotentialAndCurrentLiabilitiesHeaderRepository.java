/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:57:18 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsPotentialAndCurrentLiabilitiesHeader;

@Repository("ConsPotentialAndCurrentLiabilitiesHeaderRepository")
public interface ConsPotentialAndCurrentLiabilitiesHeaderRepository extends CrudRepository<ConsPotentialAndCurrentLiabilitiesHeader, Long> {

	List<ConsPotentialAndCurrentLiabilitiesHeader> findByRequestDetailId(Long requestDetailId);
}
