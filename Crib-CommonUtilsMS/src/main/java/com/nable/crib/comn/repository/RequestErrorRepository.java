/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 10:59:28 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestError;

@Repository("RequestErrorRepository")
public interface RequestErrorRepository extends CrudRepository<RequestError, Long> {

	List<RequestError> findByRequestDetailId(Long requestDetailId);
	
}
