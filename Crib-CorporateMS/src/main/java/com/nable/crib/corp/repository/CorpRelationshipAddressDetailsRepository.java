/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:06:20 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpRelationshipAddressDetails;

@Repository("CorpRelationshipAddressDetailsRepository")
public interface CorpRelationshipAddressDetailsRepository extends CrudRepository<CorpRelationshipAddressDetails, Long> {

	List<CorpRelationshipAddressDetails> findByRequestDetailId(Long requestDetailId);
}
