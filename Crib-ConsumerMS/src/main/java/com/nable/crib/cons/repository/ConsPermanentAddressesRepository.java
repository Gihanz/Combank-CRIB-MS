/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:56:36 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsPermanentAddresses;

@Repository("ConsPermanentAddressesRepository")
public interface ConsPermanentAddressesRepository extends CrudRepository<ConsPermanentAddresses, Long> {

	List<ConsPermanentAddresses> findByRequestDetailId(Long requestDetailId);
}
