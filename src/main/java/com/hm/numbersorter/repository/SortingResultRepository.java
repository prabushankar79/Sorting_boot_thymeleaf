package com.hm.numbersorter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.hm.numbersorter.entity.SortingResult;

/**
 * This interface is a JPArepository to persist the data onto H2 database
 * 
 */

public interface SortingResultRepository extends JpaRepository<SortingResult, Integer> {

	public List<SortingResult> findAllWithLimit(@Param("rowsLimit") int rowsLimit);
}
