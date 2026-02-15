package dao;

import java.sql.SQLException;
import java.util.List;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import enums.RoleChoice;
import model.Employee;

public interface EmployeeDaoDB {
	String addDB(String name, String mail, String address, String department, RoleChoice role) throws SQLException;

	boolean updateNameDB(String id, String name) throws SQLException;

	boolean updateMailDB(String id, String mail) throws SQLException;

	boolean updateDepartmentDB(String id, String department) throws SQLException;

	boolean updateAddressDB(String id, String address) throws SQLException;

	boolean addRoleDB(String id, String role) throws SQLException;

	boolean deleteDB(String id) throws SQLException;

	boolean updatePasswordDB(String id, String password) throws SQLException;

	boolean revokeRoleDB(String id, String role) throws SQLException;
	
	List<Employee> readAllDB() throws EmployeeNotFoundException, IdFormatWrongException, SQLException;

	Employee readOneDB(String id) throws EmployeeNotFoundException, IdFormatWrongException, SQLException;

}
