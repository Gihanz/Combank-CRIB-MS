/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:02:23 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpCreditFacilityOfGlanceStatus;

@Repository("CorpCreditFacilityOfGlanceStatusRepository")
public interface CorpCreditFacilityOfGlanceStatusRepository extends CrudRepository<CorpCreditFacilityOfGlanceStatus, Long> {

	List<CorpCreditFacilityOfGlanceStatus> findByRequestDetailId(Long requestDetailId);
}
