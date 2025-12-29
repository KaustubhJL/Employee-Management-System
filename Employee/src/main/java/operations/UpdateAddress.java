package operations;

import java.util.Scanner;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;

public class UpdateAddress {
	public static void updateAddress(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Address: ");
		String updatedAddress=sc.nextLine();
		ops.updateAddress(id, updatedAddress);
		
	}
}
