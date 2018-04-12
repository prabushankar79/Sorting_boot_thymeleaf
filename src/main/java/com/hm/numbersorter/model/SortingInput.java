package com.hm.numbersorter.model;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.hm.numbersorter.entity.SortingResult;
import com.hm.numbersorter.util.CsvSize;

/**
 * @author prabu This class is the model class which is being used to retrieve
 *         sorting input and publish sorting results
 */
public class SortingInput {

	//TO CLARIFY: Assuming 0 to be a valid candidate for sorting 0 to 99
	@Pattern(regexp = "[0-9]+(,[0-9]+)*", message = "Please input a valid positive numbers separated by comma. No decimal allowed and No trailing and ending commas allowed")
	@CsvSize(min = 1, max = 50, message = "Max number of allowed input numbers are only 50 ")
	private String inputNumbers;

	private boolean generateRandomNumbers;

	private List<SortingResult> results;

	/**
	 * get the inputNumbers to be sorted.
	 * 
	 * @return inputNumbers
	 */

	public String getInputNumbers() {
		return inputNumbers;
	}

	/**
	 * set the inputNumbers to be sorted
	 * 
	 * @param inputNumbers
	 */

	public void setInputNumbers(String inputNumbers) {
		this.inputNumbers = inputNumbers;
	}

	/**
	 * get the results persisted in H2.
	 * 
	 * @return results as List
	 */

	public List<SortingResult> getResults() {
		return results;
	}

	/**
	 * set the results retrieved from H2 i.e. List<{@link}SortingResult>
	 * 
	 * @param results
	 */

	public void setResults(List<SortingResult> results) {
		this.results = results;
	}

	/**
	 * set the generateRandomNumbers
	 * 
	 * @param generateRandomNumbers
	 */

	public void setGenerateRandomNumbers(boolean generateRandomNumbers) {
		this.generateRandomNumbers = generateRandomNumbers;
	}

	/**
	 * get the generateRandomNumbers.
	 * 
	 * @return generateRandomNumbers
	 */

	public boolean getGenerateRandomNumbers() {
		return generateRandomNumbers;
	}

}
