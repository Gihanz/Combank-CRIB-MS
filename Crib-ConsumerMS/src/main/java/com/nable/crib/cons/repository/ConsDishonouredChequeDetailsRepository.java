/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:53:59 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsDishonouredChequeDetails;

@Repository("ConsDishonouredChequeDetailsRepository")
public interface ConsDishonouredChequeDetailsRepository extends CrudRepository<ConsDishonouredChequeDetails, Long> {

	List<ConsDishonouredChequeDetails> findByRequestDetailId(Long requestDetailId);
}
