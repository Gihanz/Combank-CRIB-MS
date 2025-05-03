/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:58:35 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsSearchResults;

@Repository("ConsSearchResultsRepository")
public interface ConsSearchResultsRepository extends CrudRepository<ConsSearchResults, Long> {

	List<ConsSearchResults> findByRequestDetailId(Long requestDetailId);
}
