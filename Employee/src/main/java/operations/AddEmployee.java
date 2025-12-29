package operations;
import java.util.InputMismatchException;
import java.util.ArrayList;
import java.util.Scanner;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
public class AddEmployee {
	public static void add(Manage ops,Scanner sc) throws EmployeeNotFoundException, IdFormatWrongException {

		try{
			System.out.println("Enter name of the employee:");
		String name=sc.nextLine();
		System.out.println("Enter mail of the employee:");
		String mail=sc.nextLine();
		System.out.println("Enter address of the employee:");
		String address=sc.nextLine();
		System.out.println("Enter department of the employee:");
		String department=sc.nextLine();
		System.out.println("Choose role of the employee:");
		
		for(RoleChoice selected:RoleChoice.values())
			System.out.println(selected);
		String rolech=sc.nextLine();
		
		RoleChoice choice;
		try {
            choice = RoleChoice.valueOf(rolech.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid choice");
            return;
        }

		ArrayList<String> role=new ArrayList<>();
		
		
		switch(choice) {
		case ADMIN:
			role.add("Admin");
			break;
			
		case MANAGER:
			role.add("Manager");
			break;
			
		case EMPLOYEE:
			role.add("Employee");
			break;
		default:
			System.out.println("Choose correct role");
			break;
		}
		
		ops.add(name,mail, address, department,role,Manage.defaultPassword());
		ShowEmployee.showAll(ops);
		}
		catch (InputMismatchException e) {
            System.out.println("Invalid salary input. Please enter a number.");
            sc.nextLine();
        }
		catch(InvalidDataException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
