package operations;

import java.util.Scanner;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateDepartment {
	public static void updateDepartment(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Department: ");
		String updatedDepartment=sc.nextLine();
		ops.updateDepartment(id, updatedDepartment);
		
	}
}
