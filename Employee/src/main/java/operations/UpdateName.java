package operations;

import java.util.Scanner;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateName {
	public static void updateName(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Name: ");
		String updatedName=sc.nextLine();
		ops.updateName(id, updatedName);
		
	}
}
