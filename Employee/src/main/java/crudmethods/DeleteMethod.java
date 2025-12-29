package crudmethods;
import java.util.ArrayList;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;
import exceptions.EmployeeNotFoundException;


public class DeleteMethod {
	public static void delete(ArrayList<Employee> al, Employee em,String id) throws EmployeeNotFoundException, IdFormatWrongException {
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
