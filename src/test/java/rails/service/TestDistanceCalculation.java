package rails.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rails.exception.DistanceCalculationException;
import rails.exception.StationNotExistException;

public class TestDistanceCalculation {
	
	private DistanceCalculation calculation;

	@Before
	public void setUp() throws Exception {
		calculation = new DistanceCalculation(TestData.basicNetwork());
	}

	@Test
	public void test_calculate_ok() {
		assertEquals(12, calculation.calculate("A", "B"));
		assertEquals(5, calculation.calculate("B", "C"));
		assertEquals(17, calculation.calculate("A", "B", "C"));
		assertEquals(22, calculation.calculate("A", "B", "C", "D"));
	}
	
	@Test(expected = StationNotExistException.class)
	public void test_calculate_invalid_station() {
		calculation.calculate("a", "b");
	}
	
	@Test(expected = DistanceCalculationException.class)
	public void test_calculate_no_route() {
		calculation.calculate("A", "B", "C", "E");
	}
}
