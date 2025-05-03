/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:05:47 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpPotentialAndCurrentLiabilities;

@Repository("CorpPotentialAndCurrentLiabilitiesRepository")
public interface CorpPotentialAndCurrentLiabilitiesRepository extends CrudRepository<CorpPotentialAndCurrentLiabilities, Long> {

	List<CorpPotentialAndCurrentLiabilities> findByRequestDetailId(Long requestDetailId);
}
