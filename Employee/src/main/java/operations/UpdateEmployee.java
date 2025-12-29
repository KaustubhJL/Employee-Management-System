
package operations;

import java.util.Scanner;
//import java.util.ArrayList;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;

public class UpdateEmployee {

    public static void update(Manage ops, Scanner sc) throws Exception{
        try {

            if (ops.ArrayListIsEmpty()) {
                System.out.println("Add data before updation");
                return;
            }

            System.out.print("Enter the id of the employee: ");
            String id = sc.nextLine();

            if (!ops.employeeExists(id)) {
                System.out.println("Employee with ID " + id + " not found.");
                return;
            }

            System.out.println("\nSelect what to update");
            System.out.println("1. Update all details");
            System.out.println("2. Update name");
            System.out.println("3. Update mail");
            System.out.println("4. Update address");
            System.out.println("5. Update department");
            System.out.println("6. Manage roles (Add / Revoke )");
            System.out.println("Your choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    UpdateAll.updateAll(ops, sc, id);
                    break;

                case 2:
                    UpdateName.updateName(ops, sc, id);
                    break;

                case 3:
                    UpdateMail.updateMail(ops, sc, id);
                    break;

                case 4:
                    UpdateAddress.updateAddress(ops, sc, id);
                    break;

                case 5:
                    UpdateDepartment.updateDepartment(ops, sc, id);
                    break;

                case 6:
                    UpdateRole.updateRole(ops, sc, id);
                    break;

                default:
                    System.out.println("Invalid choice (1–6 only)");
                    return;
            }

            ShowEmployee.showAll(ops);

        } catch (EmployeeNotFoundException | IdFormatWrongException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }   
}