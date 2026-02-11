package util;

import customExceptions.InvalidDataException;

public class ValidateAddress {
	public static void validateAddress(String address) throws InvalidDataException {
		if (address == null || address.trim().isEmpty())
			throw new InvalidDataException("Address cannot be empty");
	}
}
