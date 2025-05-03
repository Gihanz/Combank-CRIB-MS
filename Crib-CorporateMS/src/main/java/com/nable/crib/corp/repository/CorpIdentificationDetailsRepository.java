/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:04:02 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpIdentificationDetails;

@Repository("CorpIdentificationDetailsRepository")
public interface CorpIdentificationDetailsRepository extends CrudRepository<CorpIdentificationDetails, Long> {

	List<CorpIdentificationDetails> findByRequestDetailId(Long requestDetailId);
}
