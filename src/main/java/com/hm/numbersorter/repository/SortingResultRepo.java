package com.hm.numbersorter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hm.numbersorter.entity.SortingResult;

/**
 * @author prabu 
 * This interface is a JPArepository to persist the data onto H2 database
 * 
 */

public interface SortingResultRepo extends JpaRepository<SortingResult, Integer> {

	public List<SortingResult> findAllWithLimit();
}