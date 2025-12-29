package crudmethods;
import java.util.ArrayList;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;
import exceptions.EmployeeNotFoundException;

public class ShowMethod {
	public static void showAll(ArrayList<Employee>al) throws EmployeeNotFoundException, IdFormatWrongException{
		
		if(al.isEmpty())
			throw new EmployeeNotFoundException("No employees to display");
		
	        for (Employee emp : al) {
	            System.out.println(emp);
	    }
	}
	
	public static void showOne(ArrayList<Employee>al, Employee em) throws EmployeeNotFoundException, IdFormatWrongException{
		System.out.println(em);
	}
}
