package com.hm.numbersorter.util;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
 
public class CsvValidator implements ConstraintValidator<CsvSize, String> {
 
	private CsvSize csvSize;
	
	@Override
	public void initialize(CsvSize csvSize) {
		this.csvSize = csvSize;
	}
 
	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		if (value == null  ) {
			return false;
		}
		int noOfCsvs = Arrays.stream(value.split(",")).toArray().length;
		 
		return (noOfCsvs > csvSize.max() || noOfCsvs < csvSize.min()) ? false : true;
 
	}
}
 