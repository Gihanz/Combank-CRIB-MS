/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 10:59:51 PM
 *  ***************************************
 */

package com.nable.crib.comn.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.comn.model.Catalogues;

@Repository("CataloguesRepository")
public interface CataloguesRepository extends CrudRepository<Catalogues, Long> {

}
