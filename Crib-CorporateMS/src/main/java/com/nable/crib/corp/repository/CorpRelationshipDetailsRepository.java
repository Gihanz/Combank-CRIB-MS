/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:06:37 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpRelationshipDetails;

@Repository("CorpRelationshipDetailsRepository")
public interface CorpRelationshipDetailsRepository extends CrudRepository<CorpRelationshipDetails, Long> {

	List<CorpRelationshipDetails> findByRequestDetailId(Long requestDetailId);
}
