package crudmethods;
import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import model.EmployeeConstructor;


public class DeleteMethod {
	public static void delete(ArrayList<EmployeeConstructor> al, EmployeeConstructor em,String id) throws EmployeeNotFoundException, IdFormatWrongException {
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{5}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		if(al.remove(em))
			System.out.println("Employee "+id +" removed Successfully");
		else
			System.out.println("Check the ID");
	}
}
