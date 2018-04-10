package com.hm.numbersorter.service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public interface SortingService {

	public void sort(String commaStringNumbers);

	public Map<String, String> getSortMetrics();

	default int[] CommaStringToIntArray(String commaString) {
		int[] intNumbers = Arrays.stream(commaString.substring(0, commaString.length()).split(",")).map(String::trim)
				.mapToInt(Integer::parseInt).toArray();
		return intNumbers;
	}

	default String IntArrayToCommaString(int[] intNumbers) {
		String commaString = Arrays.stream(intNumbers).boxed().map(Object::toString).collect(Collectors.joining(","));
		return commaString;
	}
}
