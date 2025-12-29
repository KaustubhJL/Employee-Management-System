package operations;

import java.util.Scanner;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;

public class UpdateDepartment {
	public static void updateDepartment(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Department: ");
		String updatedDepartment=sc.nextLine();
		ops.updateDepartment(id, updatedDepartment);
		
	}
}
