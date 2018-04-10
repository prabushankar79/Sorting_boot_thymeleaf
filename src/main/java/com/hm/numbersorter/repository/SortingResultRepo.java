package com.hm.numbersorter.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hm.numbersorter.entity.SortingResult;

public interface SortingResultRepo extends JpaRepository<SortingResult, Integer> {

	public List<SortingResult> findAllWithLimit();
}
