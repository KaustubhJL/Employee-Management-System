package util;

import customExceptions.InvalidDataException;

public class ValidateDepartment {
	public static void validateDepartment(String department) throws InvalidDataException {
		if (department == null || department.trim().isEmpty())
			throw new InvalidDataException("Department cannot be empty");
	}
}
