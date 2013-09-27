package rails.exception;

/**
 * Base exception class
 * @author alexcheng
 *
 */
public class RailsBaseException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RailsBaseException() {
		super();
	}

	public RailsBaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public RailsBaseException(String message) {
		super(message);
	}

	public RailsBaseException(Throwable cause) {
		super(cause);
	}
	
}
