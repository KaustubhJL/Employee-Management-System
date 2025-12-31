package main;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
import service.AccessAndPasswordMethods;
import service.Manage;
import service.SaveEmployeesToFile;
import util.AdminChoices;
import util.RoleChoice;

public class AdminMenu {

    public static void showMenu(Manage ops, Scanner sc, ObjectMapper mapper, File file) {

        AdminChoices choice = null;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            for (AdminChoices c : AdminChoices.values()) {
                System.out.println(c);
            }
            System.out.print("Your choice: ");

            try {
                choice = AdminChoices.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            try {
                switch (choice) {

                case ADD:
                    handleAdd(ops, sc, mapper, file);
                    break;

                case UPDATE:
                    handleUpdate(ops, sc, mapper, file);
                    break;

                case DELETE:
                    handleDelete(ops, sc, mapper, file);
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

                case RESET_PASSWORD:
                    AccessAndPasswordMethods.resetPassword(ops, sc);
                    SaveEmployeesToFile.saveToJson(ops, mapper, file);
                    break;

                case EXIT:
                    SaveEmployeesToFile.saveToJson(ops, mapper, file);
                    System.out.println("Admin logged out.");
                    break;

                default:
                    break;
                }

            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

        } while (choice != AdminChoices.EXIT);
    }

    private static void handleAdd(Manage ops, Scanner sc,
                                  ObjectMapper mapper, File file) throws EmployeeNotFoundException, IdFormatWrongException {

        try {
            System.out.print("Enter name: ");
            String name = sc.nextLine();

            System.out.print("Enter mail: ");
            String mail = sc.nextLine();

            System.out.print("Enter address: ");
            String address = sc.nextLine();

            System.out.print("Enter department: ");
            String department = sc.nextLine();

            System.out.println("Choose role:");
            for (RoleChoice r : RoleChoice.values()) {
                System.out.println(r);
            }

            RoleChoice choice = RoleChoice.valueOf(sc.nextLine().toUpperCase());

            ArrayList<String> role = new ArrayList<>();
            role.add(choice.name().charAt(0) + choice.name().substring(1).toLowerCase());

            ops.add(name, mail, address, department, role, Manage.defaultPassword());
            SaveEmployeesToFile.saveToJson(ops, mapper, file);

            ops.showAll().forEach(System.out::println);

        } catch (InvalidDataException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleUpdate(Manage ops, Scanner sc,
                                     ObjectMapper mapper, File file)
            throws EmployeeNotFoundException, IdFormatWrongException {

    	if (ops.isEmpty()) {
    	    System.out.println("Add data before updation");
    	    return;
    	}


        System.out.print("Enter employee ID: ");
        String id = sc.nextLine();

        if (!ops.employeeExists(id)) {
            System.out.println("Employee not found");
            return;
        }

        System.out.println("""
            1. Update all
            2. Update name
            3. Update mail
            4. Update address
            5. Update department
            6. Manage roles
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
            default -> System.out.println("Invalid choice");
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
        System.out.print("New name: ");
        ops.updateName(id, sc.nextLine());
    }

    private static void updateMail(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {
        System.out.print("New mail: ");
        ops.updateMail(id, sc.nextLine());
    }

    private static void updateAddress(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {
        System.out.print("New address: ");
        ops.updateAddress(id, sc.nextLine());
    }

    private static void updateDepartment(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {
        System.out.print("New department: ");
        ops.updateDepartment(id, sc.nextLine());
    }

    private static void updateRole(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.println("1. Add role");
        System.out.println("2. Revoke role");
        int ch = sc.nextInt();
        sc.nextLine();

        for (RoleChoice r : RoleChoice.values()) {
            System.out.println(r);
        }

        RoleChoice role = RoleChoice.valueOf(sc.nextLine().toUpperCase());

        if (ch == 1) {
            ops.addRole(id, role.name());
        } else if (ch == 2) {
            ops.revokeRole(id, role.name());
        } else {
            System.out.println("Invalid choice");
        }
    }

    private static void handleDelete(Manage ops, Scanner sc,
                                     ObjectMapper mapper, File file)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter employee ID to delete: ");
        String id = sc.nextLine();

        ops.delete(id);
        SaveEmployeesToFile.saveToJson(ops, mapper, file);
        ops.showAll().forEach(System.out::println);
    }
}