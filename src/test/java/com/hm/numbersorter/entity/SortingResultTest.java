package com.hm.numbersorter.entity;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class SortingResultTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void createWhenUserIdIsNullShouldThrowException() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Sorting Results can't be empty");
		new SortingResult(null);
	}

	@Test
	public void createWhenUserIdIsEmptyShouldThrowException() throws Exception {
		this.thrown.expect(IllegalArgumentException.class);
		this.thrown.expectMessage("Sorting Results can't be empty");
		new SortingResult(new HashMap<String, String>());
	}

	@Test
	public void saveShouldPersistData() throws Exception {
		String inputNumbers = "5,1,2,8";
		String sortedNumbers = "1,2,5,8";
		String timeConsumed = "3";
		String positionSwaped = "4";
		SortingResult sortingResult = this.entityManager
				.persistFlushFind(new SortingResult(inputNumbers, sortedNumbers, timeConsumed, positionSwaped));
		assertThat(sortingResult.getInputNumbers()).isEqualTo(inputNumbers);
		assertThat(sortingResult.getSortedNumbers()).isEqualTo(sortedNumbers);
		assertThat(sortingResult.getTimeConsumed()).isEqualTo(timeConsumed);
		assertThat(sortingResult.getPositionSwaped()).isEqualTo(positionSwaped);
	}

}