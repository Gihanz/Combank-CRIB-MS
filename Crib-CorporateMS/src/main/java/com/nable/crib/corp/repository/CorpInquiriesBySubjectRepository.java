/*
 *  ***************************************
 *  * @author Gihan Liyanage
 *  * @date Aug 15, 2022 - 8:04:17 PM
 *  ***************************************
 */

package com.nable.crib.corp.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nable.crib.corp.model.CorpInquiriesBySubject;

@Repository("CorpInquiriesBySubjectRepository")
public interface CorpInquiriesBySubjectRepository extends CrudRepository<CorpInquiriesBySubject, Long> {

	List<CorpInquiriesBySubject> findByRequestDetailId(Long requestDetailId);
}
