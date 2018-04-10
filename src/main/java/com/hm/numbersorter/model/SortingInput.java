package com.hm.numbersorter.model;

import java.util.List;

import javax.validation.constraints.Pattern;

import com.hm.numbersorter.entity.SortingResult;

public class SortingInput {

	@Pattern(regexp = "[0-9]+(,[0-9]+)*", message = "Please input a valid positive number separated by comma. No decimal allowed and No trailing and ending commas allowed")
    //@Pattern(regexp = "/^([^,]*,){50}[^,]*$/", message = "Maximum numbers are only 50, It looks like more numbers are there!")
	private String inputNumbers;

	private boolean generateRandomNumbers;

	private List<SortingResult> results;

	public String getInputNumbers() {
		return inputNumbers;
	}

	public void setInputNumbers(String inputNumbers) {
		this.inputNumbers = inputNumbers;
	}

	public List<SortingResult> getResults() {
		return results;
	}

	public void setResults(List<SortingResult> results) {
		this.results = results;
	}

	public void setGenerateRandomNumbers(boolean generateRandomNumbers) {
		this.generateRandomNumbers = generateRandomNumbers;
	}

	public boolean getGenerateRandomNumbers() {
		return generateRandomNumbers;
	}

}
