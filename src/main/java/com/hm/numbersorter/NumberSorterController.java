package com.hm.numbersorter;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hm.numbersorter.entity.SortingResult;
import com.hm.numbersorter.model.SortingInput;
import com.hm.numbersorter.service.PersistenceService;
import com.hm.numbersorter.service.SortingService;

@Controller
public class NumberSorterController {

    private static final Logger logger = LoggerFactory.getLogger(NumberSorterController.class);

	@Autowired
	public SortingService sortingService;

	@Autowired
	public PersistenceService persistenceService;

	/**
	 * Get method called from UI.
	 * RequestParam determines whether or not to create random numbers.
	 * @param  RequestParam - generateRandomNumbers
	 * @param  model and sortingInput 
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(
			@RequestParam(name = "generateRandomNumbers", required = false, defaultValue = "false") boolean generateRandomNumbers,
			Model model, SortingInput sortingInput) {
		if (generateRandomNumbers) {
			String randomNumbersString = new Random().ints(50, 1, 100).boxed().map(Object::toString)
					.collect(Collectors.joining(","));
			sortingInput.setInputNumbers(randomNumbersString);
			logger.debug("Generated Random Number : "+ randomNumbersString);
		}
		return "index";
	}

	/**
	 * Post method called upon submission of form.
	 * RequestParam determines whether or not to create random numbers.
	 * @param  SortingInput, BindingResult, Model
	 * sortingInput - holds the data to be processed by sorting service
	 * bindingResult - holds validation failure if any and message
	 * model - MVC Model class
	 */
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String result(@Valid SortingInput sortingInput, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			logger.debug("Error reported for input "+ bindingResult.getFieldValue("inputNumbers"));
			return "index";
		}
		Map<String,String> sortMetricsMap = sortingService.sort(sortingInput.getInputNumbers());
		persistenceService.saveMetricsRecord(new SortingResult(sortMetricsMap));
		sortingInput.setResults(persistenceService.findAllWithLimit());
		model.addAttribute("results", sortingInput.getResults());
		return "result";
	}
	
}