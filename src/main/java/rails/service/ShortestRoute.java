package rails.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import rails.exception.NoShortestRouteFoundException;
import rails.model.Network;
import rails.model.Route;
import rails.model.Station;

import com.google.common.collect.Lists;

/**
 * Calculate shortest route from one station to another station.<br>
 * A* search is used to find the shortest route from start station to end station.
 * 
 * @author alexcheng
 *
 */
public class ShortestRoute {
	private Network network;

	public ShortestRoute(Network network) {
		this.network = network;
	}
	
	/**
	 * Calculate shortest route
	 * @param start start station
	 * @param end end station
	 * @return shortest route
	 */
	public ShortestRouteResult calculate(String start, String end) {
		List<Node> open = Lists.newArrayList();
		List<Node> closed = Lists.newArrayList();
		open.add(new Node(new Station(start)));
		while (!open.isEmpty()) {
			Collections.sort(open); //Choose the node with lowest score
			Node current = open.get(0);
			if (new Station(end).equals(current.getStation())) {
				return new ShortestRouteResult(current.toRoute(), current.getDistance());
			}
			open.remove(current); // Move this node from open list to closed list
			closed.add(current);
			Set<Route> toRoutes = network.getRoutes(current.getStation());
			for (Route toRoute : toRoutes) {
				Node toNode = new Node(toRoute.getEnd());
				if (closed.contains(toNode)) {
					continue;
				}
				if (open.contains(toNode)) {
					//Node is in open list, update the score and path when necessary
					if (current.getDistance() + toRoute.getDistance() < toNode.getDistance()) {
						toNode.setParentNode(current);
						toNode.setDistance(current.getDistance() + toRoute.getDistance());
					}
				}
				else {
					//Node is not in open list, add it to open list and record the path
					open.add(toNode);
					toNode.setParentNode(current);
					toNode.setDistance(current.getDistance() + toRoute.getDistance());
				}
			}
		}
		throw new NoShortestRouteFoundException();
	}
	
	/**
	 * Result of shortest route calculation
	 * @author alexcheng
	 *
	 */
	public final static class ShortestRouteResult {
		private final List<Station> route;
		private final int distance;
		public ShortestRouteResult(List<Station> route, int distance) {
			super();
			this.route = route;
			this.distance = distance;
		}
		public List<Station> getRoute() {
			return route;
		}
		public int getDistance() {
			return distance;
		}
	}
	
	private static class Node implements Comparable<Node> {
		private Station station;
		private Node parentNode = null;
		private int distance = 0;
		public Node(Station station) {
			this.station = station;
		}
		
		public int getDistance() {
			return distance;
		}
		
		public Station getStation() {
			return station;
		}
		
		public Node getParentNode() {
			return parentNode;
		}

		public void setParentNode(Node parentNode) {
			this.parentNode = parentNode;
		}
		
		public void setDistance(int distance) {
			this.distance = distance;
		}
		
		public String toString() {
			return String.format("%s (%s)", station, distance);
		}
		
		public List<Station> toRoute() {
			List<Station> stations = Lists.newArrayList();
			Node node = this;
			while (node != null) {
				stations.add(node.getStation());
				node = node.getParentNode();
			}
			return Lists.reverse(stations);
		}

		public int compareTo(Node aNode) {
			return this.getDistance() - aNode.getDistance();
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((station == null) ? 0 : station.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (station == null) {
				if (other.station != null)
					return false;
			} else if (!station.equals(other.station))
				return false;
			return true;
		}
	}
}
