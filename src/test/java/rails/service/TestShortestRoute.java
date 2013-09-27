package rails.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import rails.model.Network;
import rails.service.ShortestRoute.ShortestRouteResult;

public class TestShortestRoute {
	
	private ShortestRoute shortestRoute;

	@Before
	public void setUp() throws Exception {
		String encodedString = "A#B#1;A#C#2;B#D#3;C#E#4;D#E#5;E#F#7";
		Network network = Network.fromEncodedString(encodedString);
		shortestRoute = new ShortestRoute(network);
	}

	@Test
	public void test_basic_find() {
		ShortestRouteResult result = shortestRoute.calculate("A", "B");
		assertEquals(1, result.getDistance());
		result = shortestRoute.calculate("A", "E");
		assertEquals(6, result.getDistance());
	}

}
