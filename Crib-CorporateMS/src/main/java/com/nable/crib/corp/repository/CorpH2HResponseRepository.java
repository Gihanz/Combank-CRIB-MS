/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 17, 2022 - 1:16:37 AM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpH2HResponse;

@Repository("CorpH2HResponseRepository")
public interface CorpH2HResponseRepository extends CrudRepository<CorpH2HResponse, Long> {

}
