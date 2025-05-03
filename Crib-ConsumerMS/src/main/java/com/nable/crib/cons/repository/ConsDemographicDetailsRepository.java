/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 11, 2022 - 11:44:07 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsDemographicDetails;

@Repository("ConsDemographicDetailsRepository")
public interface ConsDemographicDetailsRepository extends CrudRepository<ConsDemographicDetails, Long> {

	ConsDemographicDetails findByRequestDetailId(Long requestDetailId);
}
