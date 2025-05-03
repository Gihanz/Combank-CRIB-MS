/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 12, 2022 - 12:05:42 AM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsMailingAddresses;

@Repository("ConsMailingAddressesRepository")
public interface ConsMailingAddressesRepository extends CrudRepository<ConsMailingAddresses, Long> {

	List<ConsMailingAddresses> findByRequestDetailId(Long requestDetailId);
}
