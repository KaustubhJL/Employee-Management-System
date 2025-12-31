package main;

import java.io.File;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import service.AccessAndPasswordMethods;
import service.Manage;
import service.SaveEmployeesToFile;
import util.ManagerChoices;
import util.RoleChoice;

public class ManagerMenu {

    public static void showMenu(Manage ops, Scanner sc,
                                ObjectMapper mapper, File file) {

        ManagerChoices choice = null;

        do {
            System.out.println("\n--- MANAGER MENU ---");
            for (ManagerChoices c : ManagerChoices.values()) {
                System.out.println(c);
            }
            System.out.print("Your choice: ");

            try {
                choice = ManagerChoices.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            try {
                switch (choice) {

                case UPDATE:
                    handleUpdate(ops, sc, mapper, file);
                    break;

                case SHOW_ALL:
                    ops.showAll().forEach(System.out::println);
                    break;

                case SELF_DETAILS:
                    System.out.println(ops.showOne(
                        AccessAndPasswordMethods.getLoggedInAdminId()
                    ));
                    break;

                case CHANGE_PASSWORD:
                    AccessAndPasswordMethods.updatePassword(ops, sc);
                    SaveEmployeesToFile.saveToJson(ops, mapper, file);
                    break;

                case EXIT:
                    SaveEmployeesToFile.saveToJson(ops, mapper, file);
                    System.out.println("Manager logged out.");
                    break;

                default:
                    break;
                }

            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

        } while (choice != ManagerChoices.EXIT);
    }

    private static void handleUpdate(Manage ops, Scanner sc,
                                     ObjectMapper mapper, File file)
            throws EmployeeNotFoundException, IdFormatWrongException {

        if (ops.isEmpty()) {
            System.out.println("Add data before updation");
            return;
        }

        System.out.print("Enter the id of the employee: ");
        String id = sc.nextLine();

        if (!ops.employeeExists(id)) {
            System.out.println("Employee with ID " + id + " not found.");
            return;
        }

        System.out.println("""
            1. Update all details
            2. Update name
            3. Update mail
            4. Update address
            5. Update department
            6. Manage roles (Add / Revoke)
            """);

        int ch = sc.nextInt();
        sc.nextLine();

        switch (ch) {
            case 1 -> updateAll(ops, sc, id);
            case 2 -> updateName(ops, sc, id);
            case 3 -> updateMail(ops, sc, id);
            case 4 -> updateAddress(ops, sc, id);
            case 5 -> updateDepartment(ops, sc, id);
            case 6 -> updateRole(ops, sc, id);
            default -> {
                System.out.println("Invalid choice (1–6 only)");
                return;
            }
        }

        SaveEmployeesToFile.saveToJson(ops, mapper, file);
        ops.showAll().forEach(System.out::println);
    }

    private static void updateAll(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        updateName(ops, sc, id);
        updateMail(ops, sc, id);
        updateAddress(ops, sc, id);
        updateDepartment(ops, sc, id);
        updateRole(ops, sc, id);
    }

    private static void updateName(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Name: ");
        ops.updateName(id, sc.nextLine());
    }

    private static void updateMail(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Mail: ");
        ops.updateMail(id, sc.nextLine());
    }

    private static void updateAddress(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Address: ");
        ops.updateAddress(id, sc.nextLine());
    }

    private static void updateDepartment(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Department: ");
        ops.updateDepartment(id, sc.nextLine());
    }

    private static void updateRole(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.println("Enter 1 to add a Role");
        System.out.println("Enter 2 to revoke a Role");

        int ch = sc.nextInt();
        sc.nextLine();

        for (RoleChoice r : RoleChoice.values()) {
            System.out.println(r);
        }

        RoleChoice role = RoleChoice.valueOf(sc.nextLine().toUpperCase());

        if (ch == 1) {
            ops.addRole(id, role.name());
            System.out.println("Role added successfully!");
        } else if (ch == 2) {
            ops.revokeRole(id, role.name());
            System.out.println("Role revoked successfully.");
        } else {
            System.out.println("Invalid choice");
        }
    }
}
