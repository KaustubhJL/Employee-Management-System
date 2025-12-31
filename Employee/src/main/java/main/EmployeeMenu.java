package main;

import java.util.Scanner;

import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import service.AccessAndPasswordMethods;
import service.Manage;
import util.EmployeeChoices;

public class EmployeeMenu {

    public static void showMenu(Manage ops, Scanner sc) {

        EmployeeChoices choice = null;

        do {
            System.out.println("\n--- EMPLOYEE MENU ---");
            for (EmployeeChoices c : EmployeeChoices.values()) {
                System.out.println(c);
            }
            System.out.print("Your choice: ");

            try {
                choice = EmployeeChoices.valueOf(sc.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid choice.");
                continue;
            }

            try {
                switch (choice) {

                case UPDATE:
                    handleUpdate(ops, sc);
                    break;

                case SHOW_SELF:
                    System.out.println(
                        ops.showOne(AccessAndPasswordMethods.getLoggedInAdminId())
                    );
                    break;

                case CHANGE_PASSWORD:
                    AccessAndPasswordMethods.updatePassword(ops, sc);
                    break;

                case EXIT:
                    System.out.println("Employee logged out.");
                    break;

                default:
                    break;
                }

            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }

        } while (choice != EmployeeChoices.EXIT);
    }

    private static void handleUpdate(Manage ops, Scanner sc) {

        System.out.println("Enter 1 to update your Mail ID");
        System.out.println("Enter 2 to update your Address");
        System.out.print("Your choice: ");

        int ch = sc.nextInt();
        sc.nextLine();

        String loggedInID = AccessAndPasswordMethods.getLoggedInAdminId();

        try {
            switch (ch) {
            case 1:
                updateMail(ops, sc, loggedInID);
                break;

            case 2:
                updateAddress(ops, sc, loggedInID);
                break;

            default:
                System.out.println("Invalid choice");
                break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void updateMail(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Mail: ");
        ops.updateMail(id, sc.nextLine());
        System.out.println("Mail updated successfully.");
    }

    private static void updateAddress(Manage ops, Scanner sc, String id)
            throws EmployeeNotFoundException, IdFormatWrongException {

        System.out.print("Enter the Updated Address: ");
        ops.updateAddress(id, sc.nextLine());
        System.out.println("Address updated successfully.");
    }
}
