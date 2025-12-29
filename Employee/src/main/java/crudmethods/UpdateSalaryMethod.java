//package crudmethods;
//import exceptions.EmployeeNotFoundException;
//import exceptions.IdFormatWrongException;
//import modelconstructor.Employee;
//import java.util.ArrayList;
//
//public class UpdateSalary {
//public static void updateSalary(ArrayList<Employee> al, Employee em, String id, double salary)throws EmployeeNotFoundException,IdFormatWrongException {
//	if (em == null) {
//        throw new EmployeeNotFoundException("Employee with Id: "+id + " not found.");
//    }
//	if(!id.matches("^TT\\d{2}\\d{3}$")) {
//		throw new IdFormatWrongException("The ID is wrong, please check again.");
//	}
//	em.setSalary(salary);
//	System.out.println("Salary Updated Successfully!");
//	}
//}
package crudmethods;


