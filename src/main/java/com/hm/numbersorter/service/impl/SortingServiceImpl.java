package com.hm.numbersorter.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.hm.numbersorter.NumberSorterController;
import com.hm.numbersorter.service.SortingService;
import com.hm.numbersorter.util.StopWatch;

/**
 * This class is a Implementation class of service interface and contains the
 * implementation to perform the sort operation using QuickSort algorithm (last
 * element as pivot)
 * 
 */

@Service
public class SortingServiceImpl implements SortingService {

	private static final Logger logger = LoggerFactory.getLogger(SortingServiceImpl.class);

	public int swapCounter = 0;

	public Map<String, String> sortingMetricsMap = new HashMap<String, String>();

	/**
	 * This is a wrapper function sets the metrics in a hashmap and returns.
	 * 
	 * @param inputNumbers
	 *            - Comma delimited string
	 * 
	 * @return Map<String,String> - contains the sort metrics
	 */

	@Override
	public Map<String, String> sort(String commaStringNumbers) {
		Assert.hasLength(commaStringNumbers, "Input Number can't be empty");
		int[] inputNumbers = CommaStringToIntArray(commaStringNumbers);
		StopWatch stopWatch = StopWatch.start();
		int noOfSwaps = doSort(inputNumbers, 0, inputNumbers.length - 1);
		sortingMetricsMap.put("inputNumbers", commaStringNumbers);
		sortingMetricsMap.put("sortedNumbers", IntArrayToCommaString(inputNumbers));
		sortingMetricsMap.put("timeConsumed", String.valueOf(stopWatch.elapsed()));
		sortingMetricsMap.put("positionSwaped", String.valueOf(noOfSwaps));
		logger.debug("The Sort metrics are " + sortingMetricsMap);
		swapCounter = 0;
		return sortingMetricsMap;
	}

	/**
	 * The main function that implements QuickSort()
	 * 
	 * @param arrToSort
	 *            - Array of int[] to sort
	 * @param startIndex
	 *            - Start Index
	 * @param endIndex
	 *            - End Index
	 * 
	 * @return swapCounter - indicates number of swaps occured to perform the
	 *         sorting
	 */
	public int doSort(int arrToSort[], int startIndex, int endIndex) {
		if (startIndex < endIndex) {

			int pivot = partition(arrToSort, startIndex, endIndex);

			doSort(arrToSort, startIndex, pivot - 1);
			doSort(arrToSort, pivot + 1, endIndex);
		}
		return swapCounter;
	}

	/**
	 * The function is a helper function and called from {@link}doSort()
	 * 
	 * @param arrToSort
	 *            - Array of int[] to sort
	 * @param low
	 *            - Start Index
	 * @param high
	 *            - End Index
	 * 
	 * @return swapCounter - indicates number of swaps occurred to perform the
	 *         sorting
	 */

	private int partition(int arrToSort[], int low, int high) {
		int pivot = arrToSort[high];
		int indexLow = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arrToSort[j] <= pivot) {
				indexLow++;

				// swap arrToSort[indexLow] and arrToSort[j]
				int temp = arrToSort[indexLow];
				arrToSort[indexLow] = arrToSort[j];
				arrToSort[j] = temp;
				swapCounter++;
			}
		}

		// swap arrToSort[indexLow+1] and arrToSort[high] (or pivot)
		int temp = arrToSort[indexLow + 1];
		arrToSort[indexLow + 1] = arrToSort[high];
		arrToSort[high] = temp;
		swapCounter++;
		return indexLow + 1;
	}

}
