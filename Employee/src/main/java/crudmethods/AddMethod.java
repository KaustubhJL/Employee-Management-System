package crudmethods;
import modelconstructor.Employee;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.common.hash.Hashing;

import exceptions.InvalidDataException;
//import manager.Manage;

public class AddMethod {
	public static void add(ArrayList<Employee> al,String id, String name, String mail, String address, String department, ArrayList<String> role, String password) throws InvalidDataException {
		
		
		if(name == null || name.trim().isEmpty())
	        throw new InvalidDataException("Name cannot be empty");
		
		if (mail == null || !mail.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Invalid email format. Please enter a valid email.");
        }

	    if(department == null || department.trim().isEmpty())
	        throw new InvalidDataException("Department cannot be empty");

	    String hashedPassword = Hashing.sha256()
	            .hashString(password.trim(), StandardCharsets.UTF_8)
	            .toString();
	    
	    
	    Employee emp=new Employee(id, name, mail, address, department, role, hashedPassword);
		al.add(emp);
		
		System.out.println("Employee added Successfully!");
	}
}
