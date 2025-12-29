package crudmethods;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;
import java.util.ArrayList;


public class UpdateAllMethod {
	public static void updateAll (ArrayList<Employee>al, Employee em,String id, String name, String mail, String address, String department, ArrayList<String> role) throws EmployeeNotFoundException,IdFormatWrongException{
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		em.setName(name);
		em.setMail(mail);
		em.setAddress(address);
		em.setDepartment(department);
		em.setRole(role);
//		em.setSalary(salary);
		System.out.println("Details Updated Successfully!");
	}
}
