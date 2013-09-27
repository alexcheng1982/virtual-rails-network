package rails.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rails.model.Station;

public class TestJourneyPlanning {
	
	private JourneyPlanning journeyPlanning;

	@Before
	public void setUp() throws Exception {
		journeyPlanning = new JourneyPlanning(TestData.basicNetwork());
	}

	@Test
	public void test_all_possible_routes() {
		List<List<Station>> results = journeyPlanning.plan("A", "E");
		assertEquals(5, results.size());
		assertTrue(results.contains(Utils.asStations("A", "E")));
		assertTrue(results.contains(Utils.asStations("A", "D", "E")));
		assertTrue(results.contains(Utils.asStations("A", "B", "D", "E")));
		assertTrue(results.contains(Utils.asStations("A", "B", "C", "D", "E")));
		assertTrue(results.contains(Utils.asStations("A", "B", "I", "J", "C", "D", "E")));
	}

	@Test
	public void test_max_number_stops() {
		List<List<Station>> results = journeyPlanning.planWithMaxStops("A", "E", 2);
		assertEquals(2, results.size());
	}
	
	@Test
	public void test_exact_number_stops() {
		List<List<Station>> results = journeyPlanning.planWithExactStops("A", "E", 3);
		assertEquals(1, results.size());
	}	
}
