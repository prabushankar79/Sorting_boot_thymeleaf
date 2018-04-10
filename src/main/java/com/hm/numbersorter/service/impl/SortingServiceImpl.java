package com.hm.numbersorter.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.hm.numbersorter.service.SortingService;
import com.hm.numbersorter.util.StopWatch;

@Service
public class SortingServiceImpl implements SortingService {

	public int swapCounter = 0;

	public Map<String, String> sortingMetricsMap = new HashMap<String, String>();

	@Override
	public void sort(String commaStringNumbers) {
		// TODO Auto-generated method stub
		int[] inputNumbers = CommaStringToIntArray(commaStringNumbers);
		StopWatch stopWatch = StopWatch.start();
		int noOfSwaps = doSort(inputNumbers, 0, inputNumbers.length - 1);
		sortingMetricsMap.put("inputNumbers", commaStringNumbers);		
		sortingMetricsMap.put("sortedNumbers", IntArrayToCommaString(inputNumbers));
		sortingMetricsMap.put("timeConsumed", String.valueOf(stopWatch.elapsed()));
		sortingMetricsMap.put("positionSwaped", String.valueOf(noOfSwaps));
		swapCounter = 0;
	}

	/*
	 * The main function that implements QuickSort() arr[] --> Array to be sorted,
	 * low --> Starting index, high --> Ending index
	 */
	public int doSort(int arr[], int low, int high) {
		if (low < high) {
			/*
			 * pi is partitioning index, arr[pi] is now at right place
			 */
			int pi = partition(arr, low, high);

			// Recursively sort elements before
			// partition and after partition
			doSort(arr, low, pi - 1);
			doSort(arr, pi + 1, high);
		}
		return swapCounter;
	}

	private int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1); // index of smaller element
		for (int j = low; j < high; j++) {
			// If current element is smaller than or
			// equal to pivot
			if (arr[j] <= pivot) {
				i++;

				// swap arr[i] and arr[j]
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				System.out.println("value of element " + arr[j] + " Array " + Arrays.toString(arr));
				swapCounter++;
				System.out.println("Swap counter " + swapCounter);
			}
		}

		// swap arr[i+1] and arr[high] (or pivot)
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		swapCounter++;
		System.out.println("value of element outer " + arr[i + 1] + " Array " + Arrays.toString(arr));

		return i + 1;
	}

	@Override
	public Map<String, String> getSortMetrics() {
		// TODO Auto-generated method stub
		return sortingMetricsMap;
	}

}
