package crudmethods;
import java.nio.charset.StandardCharsets;
import java.util.List;
//import java.util.Scanner;

import com.google.common.hash.Hashing;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
//import modelconstructor.Admin;
import modelconstructor.Employee;

public class UpdatePasswordMethod {
	public static void updatePassword(List<Employee> employeeList,String loggedInID,String updatedPassword) {
		if (loggedInID == null) {
            System.out.println("No user logged in.");
            return;
        }

		
		String hashedPassword = Hashing.sha256()
                .hashString(updatedPassword.trim(), StandardCharsets.UTF_8)
                .toString();
		
        for (Employee e : employeeList) {
            if (e.getId().equals(loggedInID)) {
                e.setPassword(hashedPassword);
                System.out.println("Password updated.");
                return;
            }
        }
        System.out.println("User not found.");
	}
	
	
	public static void resetPassword(List<Employee> employeeList,String selectedID, String mailCheck,String resetPassword) throws EmployeeNotFoundException,IdFormatWrongException {
		
		
		if (selectedID == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+selectedID + " not found.");
        }
		if(!selectedID.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		
//		boolean userFound = false;
		String hashedPassword = Hashing.sha256()
                .hashString(resetPassword.trim(), StandardCharsets.UTF_8)
                .toString();
		
		for (Employee e : employeeList) {
	        if (e.getId().equals(selectedID)) {
//	            userFound = true;

	            if (e.getMail().equals(mailCheck)) {
	                e.setPassword(hashedPassword);
	                System.out.println("Password reset.");
	                return;
	            } else {
	                System.out.println("Incorrect Mail ID. Please check");
	                return;
	            }
	        }
	    }
	}

	public static void resetPassword(List<Employee> employeeList,String loggedInID,String resetPassword) {
		
		String hashedPassword = Hashing.sha256()
                .hashString(resetPassword.trim(), StandardCharsets.UTF_8)
                .toString();
		
        for (Employee e : employeeList) {
            if (e.getId().equals(loggedInID)) {
            		e.setPassword(hashedPassword);
                    System.out.println("Password reset.");
                    return;
            }
        }
	}
}
