package com.hm.numbersorter.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * This is a integration testing scenario where the actual service call runs
 * without Mock
 * 
 * @author prabu
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SortingServiceTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Autowired
	private SortingService sortingService;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testSortWithEmptyInputShouldThrowException() throws Exception {

		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Input Number can't be empty");
		String inputNumbers = "";
		sortingService.sort(inputNumbers);
	}

	@Test
	public void testSortingMetrics() {

		String sortedNumbers = "1,2,4,5,9";
		String inputNumbers = "4,5,2,9,1";
		String expectedSwaps = "4";
		Map<String, String> sortMetricsMap = sortingService.sort(inputNumbers);
		assertThat(sortMetricsMap.get("inputNumbers")).isEqualTo(inputNumbers);
		assertThat(sortMetricsMap.get("sortedNumbers")).isEqualTo(sortedNumbers);
		assertThat(sortMetricsMap.get("positionSwaped")).isEqualTo(expectedSwaps);

	}

}
