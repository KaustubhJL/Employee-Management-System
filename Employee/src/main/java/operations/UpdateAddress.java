package operations;

import java.util.Scanner;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateAddress {
	public static void updateAddress(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Address: ");
		String updatedAddress=sc.nextLine();
		ops.updateAddress(id, updatedAddress);
		
	}
}
