/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:54:21 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsDishonouredChequeSummary;

@Repository("ConsDishonouredChequeSummaryRepository")
public interface ConsDishonouredChequeSummaryRepository extends CrudRepository<ConsDishonouredChequeSummary, Long> {

	List<ConsDishonouredChequeSummary> findByRequestDetailId(Long requestDetailId);
}
