package operations;

import modelconstructor.Employee;
//import java.util.List;
import java.util.Scanner;
//import java.io.File;
import java.nio.charset.StandardCharsets;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.hash.Hashing;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;

public class LoginAndPassword {
    private static String loggedInID = null;

    public static boolean acc(Manage ops, String empID, String pass) {

        String hashedPass = Hashing.sha256()
                .hashString(pass, StandardCharsets.UTF_8)
                .toString();
       
        for (Employee e : ops.getEmployees()) {
            if (e.getId().equals(empID)){
            	if(e.getPassword().equals(hashedPass)) {
                	loggedInID = empID;
                    return true;
                }
            	
            }
        }
        return false;
    }

    public static void updatePassword(Manage ops, Scanner sc) {
    	
        System.out.print("Enter new password: ");
        String p1 = sc.nextLine().trim();

        System.out.print("Re-enter new password: ");
        String p2 = sc.nextLine().trim();

        if (!p1.equals(p2)) {
            System.out.println("Passwords do not match.");
            return;
        }

        ops.updatePassword(loggedInID, p1);
    }
    
public static void resetPassword(Manage ops, Scanner sc) throws EmployeeNotFoundException, IdFormatWrongException {
	String defaultPass=Manage.defaultPassword();
	
	
		System.out.println("Enter 1 to Reset Reset Your Password");
		System.out.println("Enter 2 to Reset someone else's password");
		System.out.println("Your choice: ");
		int choice=sc.nextInt();
		sc.nextLine();
		
		switch(choice) {
		case 1:
			ops.resetPassword(loggedInID, defaultPass);
			System.out.println("The Default password is 'Default123'");
			break;
			
		case 2: 
			System.out.println("ID of the employee to reset Password: ");
			String selectedID=sc.nextLine();
			System.out.println("Enter the employee's mail for confirmation.");
			String mailCheck=sc.nextLine();
			System.out.println("Resetting password.");
	        ops.resetPassword(selectedID, mailCheck, defaultPass);
//	        System.out.println("The Default password is 'Default123'");
	        break;

		}
    }

public static String getLoggedInAdminId() {
	return loggedInID;
}
}
