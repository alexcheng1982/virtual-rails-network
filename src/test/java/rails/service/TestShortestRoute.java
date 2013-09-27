package rails.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rails.service.ShortestRoute.ShortestRouteResult;

public class TestShortestRoute {
	
	private ShortestRoute shortestRoute;

	@Before
	public void setUp() throws Exception {
		shortestRoute = new ShortestRoute(TestData.basicNetwork());
	}

	@Test
	public void test_basic_find() {
		ShortestRouteResult result = shortestRoute.calculate("A", "B");
		assertEquals(12, result.getDistance());
		assertEquals(Utils.asStations("A", "B"), result.getRoute());
	}
	
	@Test
	public void test_complex_find() {
		ShortestRouteResult result = shortestRoute.calculate("I", "D");
		assertEquals(30, result.getDistance());
	}

	@Test
	public void test_same_station() {
		ShortestRouteResult result = shortestRoute.calculate("A", "A");
		assertEquals(0, result.getDistance());
	}
}
