package com.hm.numbersorter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Map;

import org.apache.tomcat.util.file.Matcher;
import org.junit.Assert;

//import static org.springframework.test.web.servlet.result.ModelResultMatchers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.hm.numbersorter.service.SortingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
//@AutoConfigureMockMvc
public class NumberSorterControllerTest {

	  @Autowired
	  private WebApplicationContext wac;

	  private MockMvc mockMvc;
	  
	  @Autowired
	  private SortingService sortingService;


	  @Before
	  public void setup() {
	    mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	  }


	  @Test
	  public void testGet() throws Exception {
	    this.mockMvc
	        .perform(get("/"))
	        .andExpect(view().name("index"))
	        .andExpect(status().isOk());
	  }

	  @Test
	  public void testGetWithParam() throws Exception {
		  try {
	    	 this.mockMvc
	        .perform(get("/").param("generateRandomNumbers", "true"))
	        .andExpect(model().attributeExists("sortingInput"))
	        .andExpect(status().isOk());
			} catch (Exception e) {
				Assert.fail();

			}

	  }

	  @Test
		public void testPageNotFound(){
			try {
				mockMvc.perform(get("/unavailable"))
					.andExpect(status().is(404));
			} catch (Exception e) {
				Assert.fail();

			}
}
		//@Test
		public void testPostMethod(){
			String randomNumber = "4,5,2,9,1";
			try {
				mockMvc.perform(post("/"))
					.andExpect(status().isOk());
					//.andExpect(view().name("result"));
			} catch (Exception e) {
				Assert.fail();
			}
		}

	    //@Test
	    public void testSortingMetrics() {

	    	String sortedNumbers = "1,2,4,5,9";
	    	String inputNumbers = "4,5,2,9,1";
	    	String expectedSwaps = "4";
	    	Map<String,String> sortMetricsMap = sortingService.sort(inputNumbers);
	    	assertThat(sortMetricsMap.get("inputNumbers")).isEqualTo(inputNumbers);
	    	assertThat(sortMetricsMap.get("sortedNumbers")).isEqualTo(sortedNumbers);
	    	assertThat(sortMetricsMap.get("positionSwaped")).isEqualTo(expectedSwaps);

	    }

}
