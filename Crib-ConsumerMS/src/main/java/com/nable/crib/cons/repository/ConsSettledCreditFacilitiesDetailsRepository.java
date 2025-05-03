/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:58:54 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsSettledCreditFacilitiesDetails;

@Repository("ConsSettledCreditFacilitiesDetailsRepository")
public interface ConsSettledCreditFacilitiesDetailsRepository extends CrudRepository<ConsSettledCreditFacilitiesDetails, Long> {

	List<ConsSettledCreditFacilitiesDetails> findByRequestDetailId(Long requestDetailId);
}
