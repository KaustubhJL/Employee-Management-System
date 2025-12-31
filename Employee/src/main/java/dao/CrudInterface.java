package dao;

//import manager.Manage;
import java.util.ArrayList;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;

public interface CrudInterface {
	void add(String name, String mail, String address, String department, ArrayList<String> role, String password)
			throws InvalidDataException;

	void delete(String id) throws EmployeeNotFoundException, IdFormatWrongException;

	void updateName(String id, String name) throws EmployeeNotFoundException, IdFormatWrongException;

	void updateMail(String id, String mail) throws EmployeeNotFoundException, IdFormatWrongException;

	void updateAddress(String id, String address) throws EmployeeNotFoundException, IdFormatWrongException;

	void updateDepartment(String id, String department) throws EmployeeNotFoundException, IdFormatWrongException;

	void updateRole(String id, ArrayList<String> role) throws EmployeeNotFoundException, IdFormatWrongException;

	void updatePassword(String loggedInid, String password);

	void resetPassword(String id, String mail, String pass) throws EmployeeNotFoundException, IdFormatWrongException;;

	void resetPassword(String id, String pass);

	void showAll() throws EmployeeNotFoundException, IdFormatWrongException;

	void showOne(String id) throws EmployeeNotFoundException, IdFormatWrongException;
}