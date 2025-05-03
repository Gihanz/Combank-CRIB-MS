/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:05:13 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpMailingAddresses;

@Repository("CorpMailingAddressesRepository")
public interface CorpMailingAddressesRepository extends CrudRepository<CorpMailingAddresses, Long> {

	List<CorpMailingAddresses> findByRequestDetailId(Long requestDetailId);
}
