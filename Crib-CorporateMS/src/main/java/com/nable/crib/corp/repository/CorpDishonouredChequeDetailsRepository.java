/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:02:41 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpDishonouredChequeDetails;

@Repository("CorpDishonouredChequeDetailsRepository")
public interface CorpDishonouredChequeDetailsRepository extends CrudRepository<CorpDishonouredChequeDetails, Long> {

	List<CorpDishonouredChequeDetails> findByRequestDetailId(Long requestDetailId);
}
