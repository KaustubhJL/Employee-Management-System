package operations;

import java.util.Scanner;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;

public class UpdateName {
	public static void updateName(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Name: ");
		String updatedName=sc.nextLine();
		ops.updateName(id, updatedName);
		
	}
}
