/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 7:55:38 PM
 *  ***************************************
 */

package com.nable.crib.cons.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.cons.model.ConsInquiriesBySubject;

@Repository("ConsInquiriesBySubjectRepository")
public interface ConsInquiriesBySubjectRepository extends CrudRepository<ConsInquiriesBySubject, Long> {

	List<ConsInquiriesBySubject> findByRequestDetailId(Long requestDetailId);
}
