package crudmethods;
import model.EmployeeConstructor;

import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;


public class UpdateAddressMethod {
	public static void updateAddress (ArrayList<EmployeeConstructor>al, EmployeeConstructor em,String id, String address) throws EmployeeNotFoundException,IdFormatWrongException{
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
