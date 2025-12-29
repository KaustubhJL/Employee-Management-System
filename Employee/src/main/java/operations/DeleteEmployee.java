package operations;
import java.util.*;
import manager.Manage;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
public class DeleteEmployee {
	public static void delete(Manage ops,Scanner sc) {
		
		try {
        System.out.println("Enter employee ID to delete:");
        String id = sc.nextLine();

        ops.delete(id);
        ShowEmployee.showAll(ops);
		}
		catch(EmployeeNotFoundException e) {
			System.out.println("Error: "+e.getMessage());
		}
		catch(IdFormatWrongException e) {
			System.out.println("Error:"+e.getMessage());
		}
    }
}
