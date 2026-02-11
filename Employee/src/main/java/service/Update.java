package service;

import java.io.File;

import java.sql.Connection;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
import dao.CrudImplementation;
import dao.EmployeeListOps;
import dao.SaveEmployeesToFile;
import enums.RoleChoice;
import util.ValidateAddress;
import util.ValidateDepartment;
import util.ValidateMail;
import util.ValidateName;

public final class Update {
	private static final Logger logger = LoggerFactory.getLogger(Update.class);

	private Update() {
	}

	public static void handleUpdate(CrudImplementation ops, Scanner sc, ObjectMapper mapper, File file)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {

		if (EmployeeListOps.isEmpty()) {
			logger.warn("There are no employees at the moment, add data first.");
			System.out.println("Add data before updation");
			return;
		}

		String targetId = null;
		System.out.println("Update Details of:");
		System.out.println("Enter 1 for self.");
		System.out.println("Enter 2 to update Someone else's details.");
		System.out.println("Your choice: ");

		int who = sc.nextInt();
		sc.nextLine();
		if (who == 1) {
			targetId = PasswordMethods.getLoggedInId();
		} else if (who == 2) {
			System.out.println("Enter employee ID: ");
			targetId = sc.nextLine();

			if (!ops.employeeExists(targetId)) {
				logger.warn("Employee not found!");
				System.out.println("Employee not found!");
				return;
			}
		}

		while (true) {
			System.out.println("""
					Enter 1 to Update all
					Enter 2 to Update name
					Enter 3 to Update mail
					Enter 4 to Update address
					Enter 5 to Update department
					Enter 6 to Manage roles

					Your choice :
					""");

			int ch;
			try {
				ch = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
				logger.warn("Invalid input, need to enter a number.");
				System.out.println("Invalid input, enter a number.");
				continue;
			}

			switch (ch) {
			case 1 -> updateAll(ops, sc, targetId);
			case 2 -> updateName(ops, sc, targetId);
			case 3 -> updateMail(ops, sc, targetId);
			case 4 -> updateAddress(ops, sc, targetId);
			case 5 -> updateDepartment(ops, sc, targetId);
			case 6 -> updateRole(ops, sc, targetId);
			default -> System.out.println("Invalid choice selected for update: "+ ch);
			}

			SaveEmployeesToFile.saveToJson(mapper, file);
			System.out.println("Updated employee data for ID "+targetId);
			logger.info("Updated employee data for ID {}", targetId);
			ops.showAll().forEach(System.out::println);
			break;
		}
	}

	private static void updateAll(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		updateName(ops, sc, id);
		updateMail(ops, sc, id);
		updateAddress(ops, sc, id);
		updateDepartment(ops, sc, id);
		updateRole(ops, sc, id);
	}

	private static void updateName(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		System.out.print("New name: ");
		String name = sc.nextLine();
		ValidateName.validateName(name);
		ops.updateName(id, name);
		System.out.println("Updated name for employee ID "+id+" : "+ name);
		logger.info("Updated name for employee ID {}: {}", id, name);
		return;
	}

	private static void updateMail(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		System.out.print("New mail: ");
		String mail = sc.nextLine();
		ValidateMail.validateMail(mail);
		ops.updateMail(id, mail);
		System.out.println("Updated mail for employee ID "+id+" : "+mail);
		logger.info("Updated mail for employee ID {}: {}", id, mail);
		return;
	}

	private static void updateAddress(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		System.out.print("New address: ");
		String address = sc.nextLine();
		ValidateAddress.validateAddress(address);
		ops.updateAddress(id, address);
		System.out.println("Updated address for employee ID "+id+" : "+address);
		logger.info("Updated address for employee ID {}: {}", id, address);
		return;
	}

	private static void updateDepartment(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException, InvalidDataException {
		System.out.print("New department: ");
		String department = sc.nextLine();
		ValidateDepartment.validateDepartment(department);
		ops.updateDepartment(id, department);
		System.out.println("Updated department for employee ID "+id+" : "+department);
		logger.info("Updated department for employee ID {}: {}", id, department);
		return;
	}

	private static void updateRole(CrudImplementation ops, Scanner sc, String id)
			throws EmployeeNotFoundException, IdFormatWrongException {

		System.out.println("1. Add role");
		System.out.println("2. Revoke role");
		int ch = sc.nextInt();
		sc.nextLine();

		for (RoleChoice r : RoleChoice.values()) {
			System.out.println(r);
		}
		System.out.println("Your choice: ");

		RoleChoice role = RoleChoice.valueOf(sc.nextLine().toUpperCase());

		if (ch == 1) {
			ops.addRole(id, role.name());
			System.out.println("Added role "+role +" to employee ID "+id);
			logger.info("Added role {} to employee ID {}", role, id);
		} else if (ch == 2) {
			ops.revokeRole(id, role.name());
			System.out.println("Revoked role "+ role +" to employee ID "+id);
			logger.info("Revoked role {} from employee ID {}", role, id);
		} else {
			System.out.println("Invalid role operation choice: "+ ch);
			logger.warn("Invalid role operation choice: {}", ch);
		}
		return;
	}

	public final static void handleUpdateForEmployee(CrudImplementation ops, Scanner sc) {

		System.out.println("Enter 1 to update your Mail ID");
		System.out.println("Enter 2 to update your Address");
		System.out.print("Your choice: ");

		int ch = sc.nextInt();
		sc.nextLine();

		String loggedInID = PasswordMethods.getLoggedInId();

		try {
			switch (ch) {
			case 1:
				Update.updateMail(ops, sc, loggedInID);
				break;

			case 2:
				Update.updateAddress(ops, sc, loggedInID);
				break;

			default:
				System.out.println("Invalid choice");
				break;
			}
		} catch (Exception e) {
			System.out.println("Invalid choice.");
			logger.warn("Error: " + e.getMessage());
		}
		return;
	}

// -----------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------

	public static void handleUpdateDB(CrudImplementation ops, Scanner sc, Connection conn) {
		try {

			String targetId = null;
			System.out.println("Update Details of:");
			System.out.println("Enter 1 for self.");
			System.out.println("Enter 2 to update Someone else's details.");
			System.out.println("Your choice: ");

			int who = sc.nextInt();
			sc.nextLine();
			
			if (who != 1 && who != 2) {
				System.out.println("Invalid selection. Select 1 or 2.");
			    logger.warn("Invalid selection");
			    return;
			}
			
			if (who == 1) {
				targetId = PasswordMethods.getLoggedInId();
			} else if (who == 2) {
				System.out.println("Enter employee ID: ");
				targetId = sc.nextLine();

				if (!ops.employeeExistsDB(targetId)) {
					System.out.println("Employee not found!");
					logger.warn("Employee not found!");
					return;
				}
			}

			while (true) {
				System.out.println("""
						Enter 1 to Update all
						Enter 2 to Update name
						Enter 3 to Update mail
						Enter 4 to Update address
						Enter 5 to Update department
						Enter 6 to Manage roles

						Your choice :
						""");
				int ch;
				try {
					ch = sc.nextInt();
					sc.nextLine();
				} catch (Exception e) {
					sc.nextLine();
					System.out.println("Invalid input, enter a number 1-6.");
					logger.warn("Invalid input, enter a number.");
					continue;
				}

				switch (ch) {
				case 1 -> updateAllDB(ops, sc, targetId);
				case 2 -> updateNameDB(ops, sc, targetId);
				case 3 -> updateMailDB(ops, sc, targetId);
				case 4 -> updateAddressDB(ops, sc, targetId);
				case 5 -> updateDepartmentDB(ops, sc, targetId);
				case 6 -> updateRoleDB(ops, sc, targetId);
				default -> System.out.println("Invalid choice selected for update: "+ ch);
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("Error updating data.");
			logger.warn("Erroe updating data.");
		}
	}

	public static void updateAllDB(CrudImplementation ops, Scanner sc, String id) throws InvalidDataException {
		updateNameDB(ops, sc, id);
		updateMailDB(ops, sc, id);
		updateAddressDB(ops, sc, id);
		updateDepartmentDB(ops, sc, id);
		updateRoleDB(ops, sc, id);
	}

	public static void updateNameDB(CrudImplementation ops, Scanner sc, String id) throws InvalidDataException {
		System.out.print("New name: ");
		String name = sc.nextLine();
		ValidateName.validateName(name);
		ops.updateNameDB(id, name);
		System.out.println("Updated name for employee ID "+id+" : "+name);
		logger.info("Updated name for employee ID {}: {}", id, name);
		return;
	}

	public static void updateMailDB(CrudImplementation ops, Scanner sc, String id) throws InvalidDataException {
		System.out.print("New mail: ");
		String mail = sc.nextLine();
		ValidateMail.validateMail(mail);
		ops.updateMailDB(id, mail);
		System.out.println("Updated mail for employee ID "+id+" : "+mail);
		logger.info("Updated mail for employee ID {}: {}", id, mail);
		return;
	}

	public static void updateAddressDB(CrudImplementation ops, Scanner sc, String id) throws InvalidDataException {
		System.out.print("New address: ");
		String address = sc.nextLine();
		ValidateAddress.validateAddress(address);
		ops.updateAddressDB(id, address);
		System.out.println("Updated address for employee ID "+id+" : "+address);
		logger.info("Updated address for employee ID {}: {}", id, address);
		return;
	}

	public static void updateDepartmentDB(CrudImplementation ops, Scanner sc, String id) throws InvalidDataException {
		System.out.print("New department: ");
		String department = sc.nextLine();
		ValidateDepartment.validateDepartment(department);
		ops.updateDepartmentDB(id, department);
		System.out.println("Updated department for employee ID "+id+" : "+department);
		logger.info("Updated department for employee ID {}: {}", id, department);
		return;
	}

	public static void updateRoleDB(CrudImplementation ops, Scanner sc, String id) {
		System.out.println("Enter 1 to Add role");
		System.out.println("Enter 2 to Revoke role");
		System.out.println("Your choice: ");
		int ch = sc.nextInt();
		sc.nextLine();

		for (RoleChoice r : RoleChoice.values()) {
			System.out.println(r);
		}
		System.out.println("Your choice: ");

		RoleChoice role = RoleChoice.valueOf(sc.nextLine().toUpperCase());

		if (ch == 1) {
			ops.addRoleDB(id, role.name());
			System.out.println("Added role "+role+" from employee ID "+id);
			logger.info("Added role {} to employee ID {}", role, id);
		} else if (ch == 2) {
			ops.revokeRoleDB(id, role.name());
			System.out.println("Revoked role "+role+" from employee ID "+id);
			logger.info("Revoked role {} from employee ID {}", role, id);
		} else {
			System.out.println("Invalid choice");
			logger.warn("Invalid choice");
		}
		return;
	}

	public final static void handleUpdateForEmployeeDB(CrudImplementation ops, Scanner sc) throws InvalidDataException {

		System.out.println("Enter 1 to update your Mail ID");
		System.out.println("Enter 2 to update your Address");
		System.out.print("Your choice: ");

		int ch = sc.nextInt();
		sc.nextLine();

		String loggedInID = PasswordMethods.getLoggedInId();

		try {
			switch (ch) {
			case 1:
				Update.updateMailDB(ops, sc, loggedInID);
				break;

			case 2:
				Update.updateAddressDB(ops, sc, loggedInID);
				break;

			default:
				System.out.println("Invalid choice");
				logger.warn("Invalid choice");
				break;
			}
		} catch (InvalidDataException e) {
			System.out.println("Invalid data."+e.getMessage());
			logger.warn("Invalid data: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error updating data."+e.getMessage());
			logger.warn("Error updating data: " + e.getMessage());
		}
		return;
	}
}
