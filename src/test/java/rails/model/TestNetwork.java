package rails.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNetwork {

	@Test
	public void test_add_station() {
		Station station = new Station("A");
		Network network = new Network();
		network.addStation(station);
		assertEquals(1, network.getStations().size());
	}
	
	@Test
	public void test_add_route() {
		Station stationA = new Station("A");
		Station stationB = new Station("B");
		Route route = new Route(stationA, stationB, 10);
		Network network = new Network();
		network.addRoute(route);
		assertEquals(1, network.getRoutes().size());
		assertEquals(2, network.getStations().size());
	}

	@Test
	public void test_load_encoded_network() {
		String encodedString = "A#B#10;B#C#15;C#A#20";
		Network network = Network.fromEncodedString(encodedString);
		assertEquals(3, network.getRoutes().size());
		assertEquals(3, network.getStations().size());
	}
	
	@Test
	public void test_load_encoded_network_with_errors() {
		String encodedString = "A#B#10;A#C#b;C#20";
		Network network = Network.fromEncodedString(encodedString);
		assertEquals(1, network.getRoutes().size());
		assertEquals(2, network.getStations().size());
	}
}
