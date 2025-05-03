/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:00:57 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpCatalogueDescription;

@Repository("CorpCatalogueDescriptionRepository")
public interface CorpCatalogueDescriptionRepository extends CrudRepository<CorpCatalogueDescription, Long> {

	List<CorpCatalogueDescription> findByRequestDetailId(Long requestDetailId);
}
