package com.hm.numbersorter;

import java.util.Random;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hm.numbersorter.entity.SortingResult;
import com.hm.numbersorter.model.SortingInput;
import com.hm.numbersorter.service.PersistenceService;
import com.hm.numbersorter.service.SortingService;

@Controller
public class NumberSorterController {

	@Autowired
	public SortingService sortingService;

	@Autowired
	public PersistenceService persistenceService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(
			@RequestParam(name = "generateRandomNumbers", required = false, defaultValue = "false") boolean generateRandomNumbers,
			Model model, SortingInput sortingInput) {
		if (generateRandomNumbers) {
			String randomNumbersString = new Random().ints(50, 1, 100).boxed().map(Object::toString)
					.collect(Collectors.joining(","));
			sortingInput.setInputNumbers(randomNumbersString);
		}
		System.out.println("The value of generate Random number flag: " + generateRandomNumbers);
		System.out.println("The value of generate Random number object: " + sortingInput.getGenerateRandomNumbers());
		return "index";
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String result(@Valid SortingInput sortingInput, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "index";
		}
		sortingService.sort(sortingInput.getInputNumbers());
		persistenceService.saveMetricsRecord(new SortingResult(sortingService.getSortMetrics()));
		sortingInput.setResults(persistenceService.findAllWithLimit());
		model.addAttribute("results", sortingInput.getResults());
		sortingInput.getResults().stream().forEach(e -> {
			System.out.println(e.getInputNumbers());
			System.out.println(e.getSortedNumbers());
			System.out.println(e.getPositionSwaped());
			System.out.println(e.getTimeConsumed());
		});
		// model.addAttribute("numbers", sortingInput.getInputNumbers());
		// String numberToSort = sortingInput.getInputNumbers();
		// int[] arr = Arrays.stream(numberToSort.substring(0,
		// numberToSort.length()).split(","))
		// .map(String::trim).mapToInt(Integer::parseInt).toArray();
		// int[] arr = {2,1,9,4,5,8};
		// System.out.println("Numbers:" +Arrays.toString(arr));
		// System.out.println("Number of swaps:"+SortUtil.sort(arr,0,arr.length-1));
		// System.out.println("Number of swaps:"+SortUtil.sort(arr));
		// model.addAttribute("content", Arrays.toString(arr));
		return "result";
	}

}
