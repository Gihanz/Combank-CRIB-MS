/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:52:37 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsCreditFacilityDetails;

@Repository("ConsCreditFacilityDetailsRepository")
public interface ConsCreditFacilityDetailsRepository extends CrudRepository<ConsCreditFacilityDetails, Long> {

	List<ConsCreditFacilityDetails> findByRequestDetailId(Long requestDetailId);
}
