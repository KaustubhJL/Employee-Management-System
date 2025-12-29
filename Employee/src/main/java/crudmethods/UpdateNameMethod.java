package crudmethods;
import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import model.EmployeeConstructor;


public class UpdateNameMethod {
	public static void updateName(ArrayList<EmployeeConstructor> al, EmployeeConstructor em, String id, String name)throws EmployeeNotFoundException,IdFormatWrongException {
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
