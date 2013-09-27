package rails.exception;

/**
 * Thrown when no shortest route can be found
 * @author alexcheng
 *
 */
public class NoShortestRouteFoundException extends RailsBaseException {
	private static final long serialVersionUID = 1L;

	public NoShortestRouteFoundException() {
		super();
	}

	public NoShortestRouteFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoShortestRouteFoundException(String message) {
		super(message);
	}

	public NoShortestRouteFoundException(Throwable cause) {
		super(cause);
	}

	
}
