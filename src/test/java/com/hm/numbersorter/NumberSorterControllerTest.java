package com.hm.numbersorter;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Assert;

//import static org.springframework.test.web.servlet.result.ModelResultMatchers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hm.numbersorter.service.SortingService;

/**
 * This class is used to test the controller and the application
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class NumberSorterControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@MockBean
	private SortingService sortingService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testGet() throws Exception {
		this.mockMvc.perform(get("/")).andExpect(view().name("index")).andExpect(status().isOk());
	}

	@Test
	public void testGetWithParam() throws Exception {
		try {
			this.mockMvc.perform(get("/").param("generateRandomNumbers", "true"))
					.andExpect(model().attributeExists("sortingInput")).andExpect(status().isOk());
		} catch (Exception e) {
			Assert.fail();

		}
	}

	@Test
	public void testPageNotFound() {
		try {
			mockMvc.perform(get("/unavailable")).andExpect(status().is(404));
		} catch (Exception e) {
			Assert.fail();

		}
	}

	@Test
	public void testPostMethod() {
		String inputNumbers = "4,5,2,9,1";
		String sortedNumbers = "1,2,4,5,9";
		String timeConsumed = "2";
		String positionSwaped = "5";

		Map<String, String> sortMetricsMap = new HashMap<String, String>();
		sortMetricsMap.put("inputNumbers", inputNumbers);
		sortMetricsMap.put("sortedNumbers", sortedNumbers);
		sortMetricsMap.put("timeConsumed", timeConsumed);
		sortMetricsMap.put("positionSwaped", positionSwaped);

		when(sortingService.sort(inputNumbers)).thenReturn(sortMetricsMap);
		try {
			mockMvc.perform(post("/").param("inputNumbers", inputNumbers)).andExpect(status().isOk())
					.andExpect(view().name("result")).andExpect(model().attributeExists("results"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void testPostMethodWithInvalidInput() {
		String inputNumbers = "4,5,2,9,1,";
		String sortedNumbers = "1,2,4,5,9";
		String timeConsumed = "2";
		String positionSwaped = "5";

		Map<String, String> sortMetricsMap = new HashMap<String, String>();
		sortMetricsMap.put("inputNumbers", inputNumbers);
		sortMetricsMap.put("sortedNumbers", sortedNumbers);
		sortMetricsMap.put("timeConsumed", timeConsumed);
		sortMetricsMap.put("positionSwaped", positionSwaped);

		when(sortingService.sort(inputNumbers)).thenReturn(sortMetricsMap);
		try {
			mockMvc.perform(post("/").param("inputNumbers", inputNumbers)).andExpect(status().isOk())
					.andExpect(view().name("index")).andExpect(model().attributeDoesNotExist("results"))
					.andExpect(model().attributeExists("sortingInput"));
		} catch (Exception e) {
			Assert.fail();
		}
	}

	@Test
	public void applicationContextLoaded() {
	}

	@Test
	/**
	 * Run this test without having the application running otherwise it will fail obtaining the application context
	 */
	public void applicationContextTest() {
		try {
	    WebApplication.main(new String[] {});
		}catch (Exception e) {
			Assert.fail();
		}
		
	}

}
