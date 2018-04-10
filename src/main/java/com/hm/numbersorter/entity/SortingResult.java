package com.hm.numbersorter.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Sort_Result")
@NamedNativeQuery(name = "SortingResult.findAllWithLimit", query = "SELECT * FROM Sort_Result order by Sort_Id desc limit 10", resultClass = SortingResult.class)
public class SortingResult {

	@Id
	@GeneratedValue
	@Column(name = "Sort_Id")
	private int sortId;

	@Column(name = "Input_Numbers")
	private String inputNumbers;
	@Column(name = "Sorted_Numbers")
	private String sortedNumbers;

	@Column(name = "Time_Consumed")
	private String timeConsumed;
	@Column(name = "Position_Swaped")
	private String positionSwaped;

	public SortingResult() {

	}

	public SortingResult(String inputNumbers, String sortedNumbers, String timeConsumed, String positionSwaped) {
		this.inputNumbers = inputNumbers;
		this.sortedNumbers = sortedNumbers;
		this.timeConsumed = timeConsumed;
		this.positionSwaped = positionSwaped;
	}

	public SortingResult(Map<String, String> constructorMap) {
		this.inputNumbers = constructorMap.get("inputNumbers");
		this.sortedNumbers = constructorMap.get("sortedNumbers");
		this.timeConsumed = constructorMap.get("timeConsumed");
		this.positionSwaped = constructorMap.get("positionSwaped");
	}

	public int getSortId() {
		return sortId;
	}

	public void setSortId(int sortId) {
		this.sortId = sortId;
	}

	public String getInputNumbers() {
		return inputNumbers;
	}

	public void setInputNumbers(String inputNumbers) {
		this.inputNumbers = inputNumbers;
	}

	public String getSortedNumbers() {
		return sortedNumbers;
	}

	public void setSortedNumbers(String sortedNumbers) {
		this.sortedNumbers = sortedNumbers;
	}

	public String getTimeConsumed() {
		return timeConsumed;
	}

	public void setTimeConsumed(String timeConsumed) {
		this.timeConsumed = timeConsumed;
	}

	public String getPositionSwaped() {
		return positionSwaped;
	}

	public void setPositionSwaped(String positionSwaped) {
		this.positionSwaped = positionSwaped;
	}

}
