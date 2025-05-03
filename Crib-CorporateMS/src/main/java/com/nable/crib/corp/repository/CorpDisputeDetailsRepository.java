/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:03:13 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpDisputeDetails;

@Repository("CorpDisputeDetailsRepository")
public interface CorpDisputeDetailsRepository extends CrudRepository<CorpDisputeDetails, Long> {

	List<CorpDisputeDetails> findByRequestDetailId(Long requestDetailId);
}
