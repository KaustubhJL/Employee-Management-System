package util;

import customExceptions.InvalidDataException;

public class ValidateName {
	public static void validateName(String name) throws InvalidDataException {
		if (name == null || name.trim().isEmpty())
			throw new InvalidDataException("Name cannot be empty");
		if(name.matches(".*\\d.*"))
			throw new InvalidDataException("Names should not contain numbers or special Characters");
	}
}
