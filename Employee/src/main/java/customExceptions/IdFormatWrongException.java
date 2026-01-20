package customExceptions;

public class IdFormatWrongException extends Exception {
	private static final long serialVersionUID = 1L;

	public IdFormatWrongException(String s) {
		super(s);
	}
}
