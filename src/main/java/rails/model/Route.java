package rails.model;

import com.google.common.base.Preconditions;

/**
 * A route in the rails network to connect two stations
 * @see Station
 * @see Network
 * @author alexcheng
 *
 */
public class Route {
	private final Station start;
	private final Station end;
	private final int distance;

	/**
	 * Create a new route
	 * @param start start station
	 * @param end end station
	 * @param distance distance between start and end station
	 */
	public Route(Station start, Station end, int distance) {
		Preconditions.checkNotNull(start);
		Preconditions.checkNotNull(end);
		Preconditions.checkArgument(distance > 0, "Distance should be greater than 0.");
		this.start = start;
		this.end = end;
		this.distance = distance;
	}

	public Station getStart() {
		return start;
	}

	public Station getEnd() {
		return end;
	}

	public int getDistance() {
		return distance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + distance;
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
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
		Route other = (Route) obj;
		if (distance != other.distance)
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}
	
	public String toString() {
		return String.format("%s - (%s) -> %s", start, distance, end);
	}
}
