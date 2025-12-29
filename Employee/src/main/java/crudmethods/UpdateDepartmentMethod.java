package crudmethods;
import model.EmployeeConstructor;

import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateDepartmentMethod {
public static void updateDepartment(ArrayList<EmployeeConstructor> al, EmployeeConstructor em, String id, String department)throws EmployeeNotFoundException, IdFormatWrongException{
	if (em == null) {
        throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
    }
	if(!id.matches("^TT\\d{2}\\d{3}$")) {
		throw new IdFormatWrongException("The ID is wrong, please check again.");
	}
	em.setDepartment(department);
	System.out.println("Department Updates Successfully!");
	}
}
