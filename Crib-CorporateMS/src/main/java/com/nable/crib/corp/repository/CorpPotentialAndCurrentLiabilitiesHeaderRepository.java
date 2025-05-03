/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:06:04 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpPotentialAndCurrentLiabilitiesHeader;

@Repository("CorpPotentialAndCurrentLiabilitiesHeaderRepository")
public interface CorpPotentialAndCurrentLiabilitiesHeaderRepository extends CrudRepository<CorpPotentialAndCurrentLiabilitiesHeader, Long> {

	List<CorpPotentialAndCurrentLiabilitiesHeader> findByRequestDetailId(Long requestDetailId);
}
