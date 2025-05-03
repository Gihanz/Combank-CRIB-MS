/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:54:56 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsEmploymentDetails;

@Repository("ConsEmploymentDetailsRepository")
public interface ConsEmploymentDetailsRepository extends CrudRepository<ConsEmploymentDetails, Long> {

	List<ConsEmploymentDetails> findByRequestDetailId(Long requestDetailId);
}
