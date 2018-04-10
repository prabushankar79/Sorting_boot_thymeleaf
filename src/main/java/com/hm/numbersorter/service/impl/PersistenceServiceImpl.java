package com.hm.numbersorter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hm.numbersorter.entity.SortingResult;
import com.hm.numbersorter.repository.SortingResultRepo;
import com.hm.numbersorter.service.PersistenceService;

@Service
public class PersistenceServiceImpl implements PersistenceService {

	@Autowired
	private SortingResultRepo sortingResultsRepo;

	@Override
	public List<SortingResult> getAllMetricsRecords() {
		// TODO Auto-generated method stub
		return sortingResultsRepo.findAll();
	}

	public SortingResult saveMetricsRecord(SortingResult sortingResult) {
		// TODO Auto-generated method stub
		return sortingResultsRepo.save(sortingResult);
	}

	@Override
	public List<SortingResult> findAllWithLimit() {
		// TODO Auto-generated method stub
		return sortingResultsRepo.findAllWithLimit();
	}

}
