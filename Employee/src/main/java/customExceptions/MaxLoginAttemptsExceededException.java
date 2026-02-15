package customExceptions;

public class MaxLoginAttemptsExceededException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MaxLoginAttemptsExceededException(String message) {
        super(message);
    }
}