/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:07:19 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpSearchResults;

@Repository("CorpSearchResultsRepository")
public interface CorpSearchResultsRepository extends CrudRepository<CorpSearchResults, Long> {

	List<CorpSearchResults> findByRequestDetailId(Long requestDetailId);
}
