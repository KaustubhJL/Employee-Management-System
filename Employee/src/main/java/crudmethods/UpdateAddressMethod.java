package crudmethods;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;
import java.util.ArrayList;


public class UpdateAddressMethod {
	public static void updateAddress (ArrayList<Employee>al, Employee em,String id, String address) throws EmployeeNotFoundException,IdFormatWrongException{
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		
		em.setAddress(address);
		System.out.println("Address Updated Successfully!");
	}
}
