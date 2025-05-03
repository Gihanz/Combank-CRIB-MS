/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:56:01 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsLast24Months;

@Repository("ConsLast24MonthsRepository")
public interface ConsLast24MonthsRepository extends CrudRepository<ConsLast24Months, Long> {

	List<ConsLast24Months> findByRequestDetailId(Long requestDetailId);
}
