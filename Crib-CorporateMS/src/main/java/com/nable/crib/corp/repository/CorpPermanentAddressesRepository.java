/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:05:30 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpPermanentAddresses;

@Repository("CorpPermanentAddressesRepository")
public interface CorpPermanentAddressesRepository extends CrudRepository<CorpPermanentAddresses, Long> {

	List<CorpPermanentAddresses> findByRequestDetailId(Long requestDetailId);
}
