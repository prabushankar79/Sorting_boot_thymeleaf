package com.hm.numbersorter.service;

import java.util.List;

import com.hm.numbersorter.entity.SortingResult;

/**
 * @author prabu 
 * This interface is a Service interface to expose methods that can be used 
 * to perform the CREATE and RETRIEVE operation onto H2 database entity
 * 
 */

public interface PersistenceService {
	
	public List<SortingResult> getAllMetricsRecords();

	public SortingResult saveMetricsRecord(SortingResult sortingResult);

	public List<SortingResult> findAllWithLimit();
}
