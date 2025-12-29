package crudmethods;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
//import manager.Manage;
import modelconstructor.Employee;
import java.util.ArrayList;
//import java.util.Scanner;

public class UpdateMailMethod {
	public static void updateMail (ArrayList<Employee>al, Employee em,String id, String mail) throws EmployeeNotFoundException,IdFormatWrongException{
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		if (mail == null || !mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format. Please enter a valid email.");
        }
		
		em.setMail(mail);
		System.out.println("Mail Updated Successfully!");
	}
}
