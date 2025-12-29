package crudmethods;
import java.util.ArrayList;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;


public class UpdateNameMethod {
	public static void updateName(ArrayList<Employee> al, Employee em, String id, String name)throws EmployeeNotFoundException,IdFormatWrongException {
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		em.setName(name);
		System.out.println("Name Updated Successfully!");
	}
}
