package rails.exception;

/**
 * Thrown when a station cannot be found in a network
 * @author alexcheng
 *
 */
public class StationNotExistException extends RailsBaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <tt>StationNotExistException</tt> with station name
	 * @param name Name of station
	 */
	public StationNotExistException(String name) {
		super(String.format("Station \"%s\" doesn't exist.", name));
	}
}
