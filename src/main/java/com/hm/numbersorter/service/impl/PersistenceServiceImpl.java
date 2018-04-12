package com.hm.numbersorter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.numbersorter.entity.SortingResult;
import com.hm.numbersorter.repository.SortingResultRepository;
import com.hm.numbersorter.service.PersistenceService;

/**
 * This class is a Implementation class of service interface and directly
 * operates on the repository to perform the actual operation onto H2 database
 * entity
 * 
 */

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	private SortingResultRepository sortingResultsRepo;

	public static final int rowsLimit = 10;

	@Override
	public List<SortingResult> getAllMetricsRecords() {
		return sortingResultsRepo.findAll();
	}

	public SortingResult saveMetricsRecord(SortingResult sortingResult) {
		return sortingResultsRepo.save(sortingResult);
	}

	public List<SortingResult> findAllWithLimit() {
		return sortingResultsRepo.findAllWithLimit(rowsLimit);
	}

}
