/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:04:33 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpLast24Months;

@Repository("CorpLast24MonthsRepository")
public interface CorpLast24MonthsRepository extends CrudRepository<CorpLast24Months, Long> {

	List<CorpLast24Months> findByRequestDetailId(Long requestDetailId);
}
