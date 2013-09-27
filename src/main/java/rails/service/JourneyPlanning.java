package rails.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import rails.model.Network;
import rails.model.Route;
import rails.model.Station;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Plan the journey from a station to another station. <br>
 * 
 * Traverse the network from start station. For each station, traverse stations which have routes from current station.
 * During the travel, the path from start station to current station is recorded.
 * If current station is the same as target station, then the current path is considered as a result.
 * If max number or exact number of stops is specified, then the path needs to satisfy these constraints.
 * @author alexcheng
 *
 */
public class JourneyPlanning {
	private Network network;

	public JourneyPlanning(Network network) {
		this.network = network;
	}
	
	/**
	 * List all possible journeys from a station to another station
	 * @param start start station
	 * @param end end station
	 * @return a list of all possible journeys
	 */
	public List<List<Station>> plan(String start, String end) {
		return journeyPlan(start, end, -1, false);
	}
	
	/**
	 * List all journeys from a station to another station and limit the maximum number of stops
	 * @param start start station
	 * @param end end station
	 * @param stops maximum number of stops
	 * @return a list of journeys
	 */
	public List<List<Station>> planWithMaxStops(String start, String end, int stops) {
		Preconditions.checkArgument(stops > 0, "Stops number should be greater than 0.");
		return journeyPlan(start, end, stops, true);
	}
	
	/**
	 * List all journeys from a station to another station with exact number of stops
	 * @param start start station
	 * @param end end station
	 * @param stops number of stops
	 * @return a list of journeys
	 */
	public List<List<Station>> planWithExactStops(String start, String end, int stops) {
		Preconditions.checkArgument(stops > 0, "Stops number should be greater than 0.");
		return journeyPlan(start, end, stops, false);
	}
	
	private List<List<Station>> journeyPlan(String start, String end, int stops, boolean max) {
		network.checkStationExists(start);
		network.checkStationExists(end);
		List<List<Station>> results = Lists.newArrayList();
		List<Station> path = Lists.newArrayList();
		plan(new Station(start), new Station(end), stops, max, 0, path, results);
		return results;
	}
	
	private void plan(Station current, Station target, int stops, boolean max, int depth, List<Station> path, List<List<Station>> results) {
		if (current.equals(target) && valid(stops, max, depth)) {
			List<Station> newPath = new ArrayList<Station>(path);
			newPath.add(current);
			results.add(newPath);
			return;
		}
		if (stops > 0 && ((max && depth > stops) || (!max && depth == stops))) {
			return;
		}
		Set<Route> routes = network.getRoutes(current);
		for (Route route : routes) {
			if (path.contains(current)) {
				continue;
			}
			List<Station> newPath = new ArrayList<Station>(path);
			newPath.add(current);
			plan(route.getEnd(), target, stops, max, depth + 1, newPath, results);
		}
	}
	
	private boolean valid(int stops, boolean max, int depth) {
		return (stops <= 0) || (stops > 0 && ((max && depth <= stops) || (!max && depth == stops)));
	}
	
}
