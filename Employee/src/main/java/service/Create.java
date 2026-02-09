package service;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;	


import com.fasterxml.jackson.databind.ObjectMapper;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
import dao.CrudImplementation;
import dao.SaveEmployeesToFile;
import enums.RoleChoice;
import model.Employee;
import util.ValidateAddress;
import util.ValidateDepartment;
import util.ValidateMail;
import util.ValidateName;

public class Create {
	private static final Logger logger = LoggerFactory.getLogger(Create.class);
	public static void handleAdd(CrudImplementation ops, Scanner sc, ObjectMapper mapper, File file)
			throws EmployeeNotFoundException, IdFormatWrongException {

		try {
			System.out.print("Enter name: ");
			String name = sc.nextLine();
			ValidateName.validateName(name);

			System.out.print("Enter mail: ");
			String mail = sc.nextLine();
			ValidateMail.validateMail(mail);

			System.out.print("Enter address: ");
			String address = sc.nextLine();
			ValidateAddress.validateAddress(address);

			System.out.print("Enter department: ");
			String department = sc.nextLine();
			ValidateDepartment.validateDepartment(department);

			System.out.println("Choose a role:");
			for (RoleChoice r : RoleChoice.values()) {
				System.out.println(r);
			}
			RoleChoice choice = RoleChoice.valueOf(sc.nextLine().toUpperCase());

			ArrayList<String> role = new ArrayList<>();
			role.add(choice.name().charAt(0) + choice.name().substring(1).toLowerCase());

			String randomPasswordForNew = PasswordMethods.randomPasswordGenerator();
			Employee newEmployee = ops.add(name, mail, address, department, role, randomPasswordForNew);
			SaveEmployeesToFile.saveToJson(mapper, file);
			
			logger.info("New employee added: id={}, name={}, department={}, role={}", 
                    newEmployee.getId(), 
                    newEmployee.getName(), 
                    newEmployee.getDepartment(), 
                    role);
			logger.info("Generated password for new employee id={} is: {}", newEmployee.getId(), randomPasswordForNew);
			
			
//			System.out.println("New user added!");

		} catch (InvalidDataException | IllegalArgumentException e) {
			logger.error("Failed to add employee", e);
		}
	}

	public static void handleAddDB(CrudImplementation ops, Scanner sc, Connection conn) throws SQLException {

		try {
			System.out.print("Enter name: ");
			String name = sc.nextLine();
			ValidateName.validateName(name);

			System.out.print("Enter mail: ");
			String mail = sc.nextLine();
			ValidateMail.validateMail(mail);;

			System.out.print("Enter address: ");
			String address = sc.nextLine();
			ValidateAddress.validateAddress(address);;

			System.out.print("Enter department: ");
			String department = sc.nextLine();
			ValidateDepartment.validateDepartment(department);

			System.out.println("Choose a role:");
			for (RoleChoice r : RoleChoice.values()) {
				System.out.println(r);
			}
			RoleChoice choice = RoleChoice.valueOf(sc.nextLine().toUpperCase());

			String password = PasswordMethods.randomPasswordGenerator();
			String empId = ops.addDB(name, mail, address, department, choice);
			PasswordTableDB.insertPassword(conn, empId, password);

//			System.out.println("New user added!");
			logger.info("New employee added:" ,ops.showOne(empId));
			logger.info("Generated password for new employee id={} is: {}", empId, password);

		} catch (Exception e) {
			logger.error("Failed to add employee", e);
		}
	}
}
