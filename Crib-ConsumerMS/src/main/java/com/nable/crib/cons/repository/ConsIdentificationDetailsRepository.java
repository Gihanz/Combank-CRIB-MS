/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:55:16 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsIdentificationDetails;

@Repository("ConsIdentificationDetailsRepository")
public interface ConsIdentificationDetailsRepository extends CrudRepository<ConsIdentificationDetails, Long> {

	List<ConsIdentificationDetails> findByRequestDetailId(Long requestDetailId);
}
