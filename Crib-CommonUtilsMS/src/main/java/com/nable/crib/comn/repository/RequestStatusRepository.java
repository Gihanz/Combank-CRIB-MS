/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 1:15:31 AM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestStatus;

@Repository("RequestStatusRepository")
public interface RequestStatusRepository extends CrudRepository<RequestStatus, Long> {

}
