/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:03:46 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpFirmographicDetails;

@Repository("CorpFirmographicDetailsRepository")
public interface CorpFirmographicDetailsRepository extends CrudRepository<CorpFirmographicDetails, Long> {

	CorpFirmographicDetails findByRequestDetailId(Long requestDetailId);
}
