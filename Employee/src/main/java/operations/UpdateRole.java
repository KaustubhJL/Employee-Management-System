package operations;

import java.util.Scanner;

import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;
import manager.RoleChoice;

public class UpdateRole {
	public static void updateRole(Manage ops, Scanner sc, String id) throws EmployeeNotFoundException, IdFormatWrongException {
		System.out.println("Enter 1 to add a Role");
		System.out.println("Enter 2 to revoke a Role");
		
		int ch=sc.nextInt();
		sc.nextLine();
		
		
		System.out.println("Select Role:");
		for(RoleChoice r: RoleChoice.values()) {
			System.out.println(r);
		}
		String selectedRole=sc.nextLine();
		RoleChoice role=RoleChoice.valueOf(selectedRole.toUpperCase());
		
		if(ch==1) {
			ops.addRole(id, role.name());
			System.out.println("Role Added successfully!");
//			break;
		}
		if(ch==2) {
			ops.revokeRole(id, role.name());
			System.out.println("Role Rvoked successfully.");
		}
		else {
			System.out.println("Invalid Choice");
		}
	}
}
