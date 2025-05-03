/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:07:46 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpSettledCreditFacilitiesDetails;

@Repository("CorpSettledCreditFacilitiesDetailsRepository")
public interface CorpSettledCreditFacilitiesDetailsRepository extends CrudRepository<CorpSettledCreditFacilitiesDetails, Long> {

	List<CorpSettledCreditFacilitiesDetails> findByRequestDetailId(Long requestDetailId);
}
