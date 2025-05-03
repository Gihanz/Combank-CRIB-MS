/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:02:07 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpCreditFacilityDetailsLast24Months;

@Repository("CorpCreditFacilityDetailsLast24MonthsRepository")
public interface CorpCreditFacilityDetailsLast24MonthsRepository extends CrudRepository<CorpCreditFacilityDetailsLast24Months, Long> {

	List<CorpCreditFacilityDetailsLast24Months> findByRequestDetailId(Long requestDetailId);
}
