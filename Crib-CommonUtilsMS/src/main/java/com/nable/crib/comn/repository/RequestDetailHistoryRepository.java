/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 28, 2022 - 12:21:56 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.RequestDetailHistory;

@Repository("RequestDetailHistoryRepository")
public interface RequestDetailHistoryRepository extends CrudRepository<RequestDetailHistory, Long> {

}
