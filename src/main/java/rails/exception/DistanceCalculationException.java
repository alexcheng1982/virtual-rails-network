package rails.exception;

/**
 * Thrown when distance calculation cannot continue
 * @author alexcheng
 *
 */
public class DistanceCalculationException extends RailsBaseException {
	private static final long serialVersionUID = 1L;

	public DistanceCalculationException() {
		super();
	}

	public DistanceCalculationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DistanceCalculationException(String message) {
		super(message);
	}

	public DistanceCalculationException(Throwable cause) {
		super(cause);
	}	
}
