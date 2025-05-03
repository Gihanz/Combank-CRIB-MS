/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:03:28 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpEconomicActivityHistory;

@Repository("CorpEconomicActivityHistoryRepository")
public interface CorpEconomicActivityHistoryRepository extends CrudRepository<CorpEconomicActivityHistory, Long> {

	List<CorpEconomicActivityHistory> findByRequestDetailId(Long requestDetailId);
}
