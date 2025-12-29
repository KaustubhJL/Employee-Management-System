package crudmethods;
import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import model.EmployeeConstructor;

public class ShowMethod {
	public static void showAll(ArrayList<EmployeeConstructor>al) throws EmployeeNotFoundException, IdFormatWrongException{
		
		if(al.isEmpty())
			throw new EmployeeNotFoundException("No employees to display");
		
	        for (EmployeeConstructor emp : al) {
	            System.out.println(emp);
	    }
	}
	
	public static void showOne(ArrayList<EmployeeConstructor>al, EmployeeConstructor em) throws EmployeeNotFoundException, IdFormatWrongException{
		System.out.println(em);
	}
}
