/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:53:11 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsCreditFacilityDetailsLast24Months;

@Repository("ConsCreditFacilityDetailsLast24MonthsRepository")
public interface ConsCreditFacilityDetailsLast24MonthsRepository extends CrudRepository<ConsCreditFacilityDetailsLast24Months, Long> {

	List<ConsCreditFacilityDetailsLast24Months> findByRequestDetailId(Long requestDetailId);
}
