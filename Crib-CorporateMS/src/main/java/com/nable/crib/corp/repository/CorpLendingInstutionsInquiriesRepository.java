/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:04:48 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpLendingInstutionsInquiries;

@Repository("CorpLendingInstutionsInquiriesRepository")
public interface CorpLendingInstutionsInquiriesRepository extends CrudRepository<CorpLendingInstutionsInquiries, Long> {

	List<CorpLendingInstutionsInquiries> findByRequestDetailId(Long requestDetailId);
}
