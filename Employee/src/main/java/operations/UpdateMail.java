package operations;

import java.util.Scanner;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateMail {
	public static void updateMail(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Mail: ");
		String updatedMail=sc.nextLine();
		ops.updateMail(id, updatedMail);
	}
		
}
