package rails.service;

import java.util.Set;

import com.google.common.base.Preconditions;

import rails.exception.DistanceCalculationException;
import rails.model.Network;
import rails.model.Route;
import rails.model.Station;

/**
 * Calculate distance from one station to another station following certain routes
 * @see Network
 * @author alexcheng
 *
 */
public class DistanceCalculation {
	private Network network;

	/**
	 * 
	 * @param network
	 */
	public DistanceCalculation(Network network) {
		Preconditions.checkNotNull(network);
		this.network = network;
	}

	/**
	 * Calculate the distance
	 * @param stationNames A list of station names
	 * @return distance distance
	 * @throws DistanceCalculationException when no route can be found following given stations
	 */
	public int calculate(String... stationNames) {
		Preconditions.checkArgument(stationNames.length > 1,
				"At least two stations are required.");
		int distance = 0;
		String start = stationNames[0];
		network.checkStationExists(start);
		String last = start;
		for (int i = 1, n = stationNames.length; i < n; i++) {
			String current = stationNames[i];
			network.checkStationExists(current);
			int routeDistance = getDistance(last, current);
			if (routeDistance <= 0) {
				throw new DistanceCalculationException(String.format(
						"No route from \"%s\" to \"%s\"", last, current));
			}
			distance += routeDistance;
			last = current;
		}
		return distance;
	}

	private int getDistance(String start, String end) {
		Set<Route> routes = network.getRoutes(new Station(start));
		if (routes == null || routes.isEmpty()) {
			return -1;
		}
		Station endStation = new Station(end);
		for (Route route : routes) {
			if (endStation.equals(route.getEnd())) {
				return route.getDistance();
			}
		}
		return -1;
	}
}
