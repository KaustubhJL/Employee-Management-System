package service;

import java.io.File;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;	

import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import dao.CrudImplementation;
import dao.SaveEmployeesToFile;

public class Delete {
	private static final Logger logger = LoggerFactory.getLogger(Delete.class);
	public static void handleDelete(CrudImplementation ops, Scanner sc, ObjectMapper mapper, File file)
			throws EmployeeNotFoundException, IdFormatWrongException {

		System.out.print("Enter employee ID to delete: ");
		String id = sc.nextLine();

		if (id == PasswordMethods.getLoggedInId()) {
			logger.warn("You Cannot delete your own records.");
			return;
		}

		System.out.print("Are you sure you want to delete Employee " + id + "? (yes/no): ");
		String confirm = sc.nextLine().trim().toLowerCase();

		if (!confirm.equals("yes")) {
			logger.info("Deletion cancelled.");
			return;
		}
		ops.delete(id);
		SaveEmployeesToFile.saveToJson(mapper, file);
		logger.info("Employee {} has been deleted successfully.", id);
	}

	public static void handleDeleteDB(CrudImplementation ops, Scanner sc, Connection conn) {
		try {
			System.out.print("Enter the Employee ID to delete: ");
			String id = sc.nextLine();

			if (id == PasswordMethods.getLoggedInId()) {
				logger.warn("You Cannot delete your own records.");
				return;
			}

			System.out.print("Are you sure you want to delete Employee " + id + "? (yes/no): ");
			String confirm = sc.nextLine().trim().toLowerCase();

			if (!confirm.equals("yes")) {
				System.out.println("Deletion cancelled.");
				return;
			}
			ops.deleteDB(id);
			 logger.info("Employee {} has been deleted successfully.", id);

		} catch (Exception e) {
			 logger.error("Error deleting employee: {}", e.getMessage(), e);
		}
	}
}
