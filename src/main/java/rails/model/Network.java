package rails.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import rails.exception.StationNotExistException;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

/**
 * This class provides the basic abstraction of rails network. 
 * A network contains a list of stations and routes connecting these stations.
 * @see Station
 * @see Route
 * @author alexcheng
 *
 */
public class Network {
	private Set<Station> stations = Sets.newHashSet();
	private Map<Station, Set<Route>> allRoutes = Maps.newHashMap();
	
	private static final Logger logger = LoggerFactory.getLogger(Network.class);
	
	/**
	 * Add a new {@link Station} to the network.
	 * @param station New {@link Station} to add
	 * @return Current <tt>Network</tt> object to allow chaining
	 */
	public Network addStation(Station station) {
		Preconditions.checkNotNull(station);
		stations.add(station);
		return this;
	}
	
	/**
	 * Add a new {@link Route} to the network. Start and end stations of this route will also be added.
	 * @param route New {@link Route} to add
	 * @return Current <tt>Network</tt> object to allow chaining
	 */
	public Network addRoute(Route route) {
		Preconditions.checkNotNull(route);
		this.addStation(route.getStart()).addStation(route.getEnd());
		Set<Route> routes = allRoutes.get(route.getStart());
		if (routes != null) {
			routes.add(route);
		}
		else {
			routes = Sets.newHashSet(route);
			allRoutes.put(route.getStart(), routes);
		}
		return this;
	}
	
	/**
	 * Get all stations of current network
	 * @return All stations
	 */
	public Set<Station> getStations() {
		return Collections.unmodifiableSet(stations);
	}
	
	/**
	 * Get all routes in current network
	 * @return
	 */
	public Set<Route> getRoutes() {
		Set<Route> result = Sets.newHashSet();
		for (Set<Route> routes : allRoutes.values()) {
			result.addAll(routes);
		}
		return result;
	}
	
	/**
	 * Get all routes start from a {@link Station}.
	 * @param start Start station
	 * @return
	 */
	public Set<Route> getRoutes(Station start) {
		Preconditions.checkNotNull(start);
		Set<Route> result = allRoutes.get(start);
		return result != null ? result : new HashSet<Route>();
	}
	
	/**
	 * Check if a station exists in current network
	 * @param stationName Station name
	 * @throws StationNotExistException when station doesn't exist
	 */
	public void checkStationExists(String stationName) {
		Preconditions.checkNotNull(stationName);
		if (!stations.contains(new Station(stationName))) {
			throw new StationNotExistException(stationName);
		}
	}
	
	/**
	 * Create a <tt>Network</tt> object from an encoded string.
	 * The encoded string is a list of encoded routes separated by ";".
	 * For each encoded route, it contains start station name, end station name and distance.
	 * These three components are separated by "#". See following for some examples of encoded string.
	 * <ul>
	 * 	<li><code>A#B#1</code></li>
	 * 	<li><code>A#B#1;B#C#2</code></li>
	 * 	<li><code>A#B#1;C#D#4;Demo#Another node#100</code></li>
	 * </ul>
	 * 
	 * @param encodedString String format of a network
	 * @return <tt>Network</tt> object
	 */
	public static Network fromEncodedString(String encodedString) {
		Preconditions.checkNotNull(encodedString);
		Network network = new Network();
		Iterable<String> iter = Splitter.on(";").trimResults().omitEmptyStrings().split(encodedString);
		for (String routeStr : iter) {
			List<String> routeParts = Splitter.on("#").trimResults().omitEmptyStrings().splitToList(routeStr);
			if (routeParts.size() != 3) {
				logger.warn("Invalid encoded string \"{}\", ignore.", routeStr);
				continue;
			}
			else {
				Integer distance = Ints.tryParse(routeParts.get(2));
				if (distance == null) {
					logger.warn("Invalid route distance, ignore.");
					continue;
				}
				Station start = new Station(routeParts.get(0));
				Station end = new Station(routeParts.get(1));
				Route route = new Route(start, end, distance.intValue());
				network.addRoute(route);
			}
		}
		return network;
	}
}
