/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:54:38 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsDisputeDetails;

@Repository("ConsDisputeDetailsRepository")
public interface ConsDisputeDetailsRepository extends CrudRepository<ConsDisputeDetails, Long> {

	List<ConsDisputeDetails> findByRequestDetailId(Long requestDetailId);
}
