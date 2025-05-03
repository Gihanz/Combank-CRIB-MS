/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:02:57 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpDishonouredChequeSummary;

@Repository("CorpDishonouredChequeSummaryRepository")
public interface CorpDishonouredChequeSummaryRepository extends CrudRepository<CorpDishonouredChequeSummary, Long> {

	List<CorpDishonouredChequeSummary> findByRequestDetailId(Long requestDetailId);
}
