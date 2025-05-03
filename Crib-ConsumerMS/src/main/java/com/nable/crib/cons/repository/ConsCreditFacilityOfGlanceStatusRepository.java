/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:53:38 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsCreditFacilityOfGlanceStatus;

@Repository("ConsCreditFacilityOfGlanceStatusRepository")
public interface ConsCreditFacilityOfGlanceStatusRepository extends CrudRepository<ConsCreditFacilityOfGlanceStatus, Long> {

	List<ConsCreditFacilityOfGlanceStatus> findByRequestDetailId(Long requestDetailId);
}
