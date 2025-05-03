/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:51:58 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsCreditFacility;

@Repository("ConsCreditFacilityRepository")
public interface ConsCreditFacilityRepository extends CrudRepository<ConsCreditFacility, Long> {

	List<ConsCreditFacility> findByRequestDetailId(Long requestDetailId);
}
