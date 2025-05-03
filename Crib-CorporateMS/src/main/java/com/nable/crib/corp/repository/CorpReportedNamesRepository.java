/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:06:54 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpReportedNames;

@Repository("CorpReportedNamesRepository")
public interface CorpReportedNamesRepository extends CrudRepository<CorpReportedNames, Long> {

	List<CorpReportedNames> findByRequestDetailId(Long requestDetailId);
}
