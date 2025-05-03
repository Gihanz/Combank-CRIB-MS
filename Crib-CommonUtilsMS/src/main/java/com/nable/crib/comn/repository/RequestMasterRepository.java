/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:23:55 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestMaster;

@Repository("RequestMasterRepository")
public interface RequestMasterRepository extends CrudRepository<RequestMaster, Long> {

}
