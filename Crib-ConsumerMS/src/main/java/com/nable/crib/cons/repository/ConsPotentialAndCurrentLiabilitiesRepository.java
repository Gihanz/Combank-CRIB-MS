/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:56:59 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsPotentialAndCurrentLiabilities;

@Repository("ConsPotentialAndCurrentLiabilitiesRepository")
public interface ConsPotentialAndCurrentLiabilitiesRepository extends CrudRepository<ConsPotentialAndCurrentLiabilities, Long> {

	List<ConsPotentialAndCurrentLiabilities> findByRequestDetailId(Long requestDetailId);
}
