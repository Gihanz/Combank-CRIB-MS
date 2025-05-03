/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:08:01 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpSettledCreditFacilitiesSummary;

@Repository("CorpSettledCreditFacilitiesSummaryRepository")
public interface CorpSettledCreditFacilitiesSummaryRepository extends CrudRepository<CorpSettledCreditFacilitiesSummary, Long> {

	List<CorpSettledCreditFacilitiesSummary> findByRequestDetailId(Long requestDetailId);
}
