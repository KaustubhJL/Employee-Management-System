package dao;

import java.util.ArrayList;

import java.util.List;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
import model.Employee;
import service.PasswordMethods;
import util.SetNextID;
import util.ValidateId;

public class CrudFileImplementation implements EmployeeDaoFile {

	private static final List<Employee> employees = java.util.Collections.synchronizedList(new ArrayList<>());

	public void setEmployees(List<Employee> list) {
		employees.clear();
		employees.addAll(list);
	}

	public static List<Employee> findAll() {
		return new ArrayList<>(employees);
	}

	public static Employee findById(String id) {
		for (Employee e : employees) {
			if (e.getId().equals(id)) {
				return e;
			}
		}
		return null;
	}

	public boolean checkEmpty() {
		return employees.isEmpty();
	}

//---------------------------------------------------------------------------------------------------	

	public boolean employeeExists(String id) throws IdFormatWrongException {
		ValidateId.validateId(id);
		return findById(id) != null;
	}

	public Employee add(String name, String mail, String address, String department, ArrayList<String> role,
			String password) throws InvalidDataException {

		String id = SetNextID.generateNextId(findAll());
		String hashedPassword = PasswordMethods.hash(password);

		Employee emp = new Employee(id, name, mail, address, department, role, hashedPassword);
		employees.add(emp);
		return emp;
	}

	public void delete(String id) throws EmployeeNotFoundException, IdFormatWrongException {

		Employee emp = findById(id);
		if (emp == null)
			throw new EmployeeNotFoundException("Employee not found");
		employees.remove(emp);
	}

	public List<Employee> readAll() throws EmployeeNotFoundException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		return findAll();
	}

	public Employee readOne(String id) throws EmployeeNotFoundException, IdFormatWrongException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		return findById(id);
	}

	public void updateName(String id, String name)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).setName(name);

	}

	public void updateMail(String id, String mail)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).setMail(mail);

	}

	public void updateAddress(String id, String address)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).setAddress(address);

	}

	public void updateDepartment(String id, String department)
			throws EmployeeNotFoundException, IdFormatWrongException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).setDepartment(department);

	}

	public void updatePassword(String id, String newPassword)
			throws InvalidDataException, IdFormatWrongException, EmployeeNotFoundException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).setPassword(PasswordMethods.hash(newPassword));
	}

	public void addRole(String id, String role) throws EmployeeNotFoundException, IdFormatWrongException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).addRole(role);

	}

	public void revokeRole(String id, String role) throws EmployeeNotFoundException, IdFormatWrongException {
		if (checkEmpty()) {
			throw new EmployeeNotFoundException("No existing employees");
		}
		findById(id).removeRole(role);

	}
}
