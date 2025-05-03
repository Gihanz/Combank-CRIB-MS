/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 1:16:37 AM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsH2HResponse;

@Repository("ConsH2HResponseRepository")
public interface ConsH2HResponseRepository extends CrudRepository<ConsH2HResponse, Long> {

}
