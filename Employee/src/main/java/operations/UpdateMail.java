package operations;

import java.util.Scanner;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;

public class UpdateMail {
	public static void updateMail(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter the Updated Mail: ");
		String updatedMail=sc.nextLine();
		ops.updateMail(id, updatedMail);
	}
		
}
