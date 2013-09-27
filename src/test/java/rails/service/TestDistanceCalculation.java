package rails.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rails.exception.DistanceCalculationException;
import rails.exception.StationNotExistException;
import rails.model.Network;

public class TestDistanceCalculation {
	
	private DistanceCalculation calculation;

	@Before
	public void setUp() throws Exception {
		String encodedString = "A#B#1;B#C#2;C#D#3;D#C#4;B#E#5";
		Network network = Network.fromEncodedString(encodedString);
		calculation = new DistanceCalculation(network);
	}

	@Test
	public void test_calculate_ok() {
		assertEquals(1, calculation.calculate("A", "B"));
		assertEquals(2, calculation.calculate("B", "C"));
		assertEquals(3, calculation.calculate("A", "B", "C"));
		assertEquals(6, calculation.calculate("A", "B", "C", "D"));
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
