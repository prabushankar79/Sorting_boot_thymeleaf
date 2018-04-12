package com.hm.numbersorter.service;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This interface is a Service interface to expose methods that can be used to
 * perform the CREATE and RETRIEVE operation onto H2 database entity
 * 
 */

public interface SortingService {

	public Map<String, String> sort(String commaStringNumbers);

	/**
	 * Convert comma delimited string of numbers to array of numbers.
	 * 
	 * @return intNumbers
	 */

	default int[] commaStringToIntArray(String commaString) {
		int[] intNumbers = Arrays.stream(commaString.substring(0, commaString.length()).split(",")).map(String::trim)
				.mapToInt(Integer::parseInt).toArray();
		return intNumbers;
	}

	/**
	 * Convert array of numbers to comma delimited string of numbers.
	 * 
	 * @return commaString
	 */

	default String intArrayToCommaString(int[] intNumbers) {
		String commaString = Arrays.stream(intNumbers).boxed().map(Object::toString).collect(Collectors.joining(","));
		return commaString;
	}
}
