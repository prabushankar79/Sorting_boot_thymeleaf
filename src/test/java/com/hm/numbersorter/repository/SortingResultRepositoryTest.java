package com.hm.numbersorter.repository;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.hm.numbersorter.entity.SortingResult;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link SortingResultRepository}.
 *
 * @author tempprmad
 */

@RunWith(SpringRunner.class)
@DataJpaTest
public class SortingResultRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private SortingResultRepository repository;

	@Test
	public void findAllShouldReturnSortMetrics() throws Exception {
		String inputNumbers = "5,1,2,8";
		String sortedNumbers = "1,2,5,8";
		String timeConsumed = "3";
		String positionSwaped = "4";
		this.entityManager.persist(new SortingResult(inputNumbers, sortedNumbers, timeConsumed, positionSwaped));
		List<SortingResult> sortingResultList = this.repository.findAll();
		assertThat(sortingResultList.size()).isEqualTo(1);
		assertThat(sortingResultList.get(0).getInputNumbers()).isEqualTo(inputNumbers);
		assertThat(sortingResultList.get(0).getSortedNumbers()).isEqualTo(sortedNumbers);
		assertThat(sortingResultList.get(0).getTimeConsumed()).isEqualTo(timeConsumed);
		assertThat(sortingResultList.get(0).getPositionSwaped()).isEqualTo(positionSwaped);
	}

	@Test
	public void findAllWithLimitShouldReturnLimtedRecords() throws Exception {
		final int limit = 10;

		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("6,7,3,4", "3,4,6,7", "4", "5"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));
		// 11 th Record
		this.entityManager.persist(new SortingResult("5,4,3,2,1", "1,2,3,4,5", "3", "4"));

		List<SortingResult> sortingResultList = this.repository.findAllWithLimit(limit);
		assertThat(sortingResultList.size()).isEqualTo(limit);
	}

}
