/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:56:17 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsLendingInstutionsInquiries;

@Repository("ConsLendingInstutionsInquiriesRepository")
public interface ConsLendingInstutionsInquiriesRepository extends CrudRepository<ConsLendingInstutionsInquiries, Long> {

	List<ConsLendingInstutionsInquiries> findByRequestDetailId(Long requestDetailId);
}
