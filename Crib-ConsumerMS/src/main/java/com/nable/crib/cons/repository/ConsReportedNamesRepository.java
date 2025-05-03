/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:58:19 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsReportedNames;

@Repository("ConsReportedNamesRepository")
public interface ConsReportedNamesRepository extends CrudRepository<ConsReportedNames, Long> {

	List<ConsReportedNames> findByRequestDetailId(Long requestDetailId);
}
