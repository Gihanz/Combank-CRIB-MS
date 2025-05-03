/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:01:38 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpCreditFacilityDetails;

@Repository("CorpCreditFacilityDetailsRepository")
public interface CorpCreditFacilityDetailsRepository extends CrudRepository<CorpCreditFacilityDetails, Long> {

	List<CorpCreditFacilityDetails> findByRequestDetailId(Long requestDetailId);
}
