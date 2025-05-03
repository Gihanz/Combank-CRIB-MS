/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:22:46 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestMain;

@Repository("RequestMainRepository")
public interface RequestMainRepository extends CrudRepository<RequestMain, Long> {

}
