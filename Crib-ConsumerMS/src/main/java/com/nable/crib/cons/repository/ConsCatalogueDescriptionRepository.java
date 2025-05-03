/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:51:24 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsCatalogueDescription;

@Repository("ConsCatalogueDescriptionRepository")
public interface ConsCatalogueDescriptionRepository extends CrudRepository<ConsCatalogueDescription, Long> {

	List<ConsCatalogueDescription> findByRequestDetailId(Long requestDetailId);
}
