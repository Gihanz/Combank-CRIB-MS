/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:59:07 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsSettledCreditFacilitiesSummary;

@Repository("ConsSettledCreditFacilitiesSummaryRepository")
public interface ConsSettledCreditFacilitiesSummaryRepository extends CrudRepository<ConsSettledCreditFacilitiesSummary, Long> {

	List<ConsSettledCreditFacilitiesSummary> findByRequestDetailId(Long requestDetailId);
}
