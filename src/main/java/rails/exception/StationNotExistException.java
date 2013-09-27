package rails.exception;

public class StationNotExistException extends RailsBaseException {
	private static final long serialVersionUID = 1L;

	public StationNotExistException(String name) {
		super(String.format("Station \"%s\" doesn't exist.", name));
	}
}
