package crudmethods;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import modelconstructor.Employee;
import java.util.ArrayList;

public class UpdateRoleMethod {
	public static void updateRole (ArrayList<Employee>al, Employee em,String id, ArrayList<String> role) throws EmployeeNotFoundException,IdFormatWrongException{
		if (em == null) {
            throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
        }
		if(!id.matches("^TT\\d{2}\\d{3}$")) {
			throw new IdFormatWrongException("The ID is wrong, please check again.");
		}
		System.out.println("Existing roles:" + role);
		System.out.println("Choose new Role: ");
		
		
		ArrayList<String> existingRoles=em.getRole();
		for(String newrole:role) {
			if(existingRoles.contains(newrole))
				System.out.println("Role Already Exists: "+newrole);
			else {
				existingRoles.add(newrole);
				System.out.println("Role Added: "+newrole);
			}
		}
		
		
		em.setRole(existingRoles);
		System.out.println("Role Updated Successfully!");
	}

}

