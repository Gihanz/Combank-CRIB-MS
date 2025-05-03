/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:08:54 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestDetail;

@Repository("RequestDetailRepository")
public interface RequestDetailRepository extends CrudRepository<RequestDetail, Long> {

}
