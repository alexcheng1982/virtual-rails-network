package rails.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rails.model.Network;
import rails.model.Station;
import rails.service.JourneyPlanning;

public class TestJourneyPlanning {
	
	private JourneyPlanning journeyPlanning;

	@Before
	public void setUp() throws Exception {
		String encodedString = "A#B#1;A#C#2;B#D#3;C#E#4;D#E#5;E#F#7";
		Network network = Network.fromEncodedString(encodedString);
		journeyPlanning = new JourneyPlanning(network);
	}

	@Test
	public void test_all_possible_routes() {
		List<List<Station>> results = journeyPlanning.plan("A", "F");
		assertEquals(2, results.size());
	}

	@Test
	public void test_max_number_stops() {
		List<List<Station>> results = journeyPlanning.planWithMaxStops("A", "E", 2);
		assertEquals(1, results.size());
	}
	
	@Test
	public void test_exact_number_stops() {
		List<List<Station>> results = journeyPlanning.planWithExactStops("A", "E", 3);
		assertEquals(1, results.size());
	}
}
