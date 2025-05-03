/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:01:21 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpCreditFacility;

@Repository("CorpCreditFacilityRepository")
public interface CorpCreditFacilityRepository extends CrudRepository<CorpCreditFacility, Long> {

	List<CorpCreditFacility> findByRequestDetailId(Long requestDetailId);
}
