package rails.model;

import com.google.common.base.Preconditions;

/**
 * A station in rails network
 * @see Network
 * @author alexcheng
 *
 */
public class Station {
	private final String name;

	/**
	 * Construct a new station
	 * @param name Station name
	 */
	public Station(String name) {
		Preconditions.checkNotNull(name);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Station other = (Station) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}
}
