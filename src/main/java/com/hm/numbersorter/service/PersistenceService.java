package com.hm.numbersorter.service;

import java.util.List;

import com.hm.numbersorter.entity.SortingResult;

public interface PersistenceService {

	public List<SortingResult> getAllMetricsRecords();

	public SortingResult saveMetricsRecord(SortingResult sortingResult);

	public List<SortingResult> findAllWithLimit();
}
