package util;

import customExceptions.InvalidDataException;

public class ValidateMail {
	public static void validateMail(String mail) throws InvalidDataException {
		if (mail == null || !mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
			throw new InvalidDataException("Invalid email format");
	}
}
