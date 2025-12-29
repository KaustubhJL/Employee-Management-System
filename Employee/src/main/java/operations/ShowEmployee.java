package operations;

//import java.util.Scanner;
import exceptions.EmployeeNotFoundException;
import exceptions.IdFormatWrongException;
import manager.Manage;
public class ShowEmployee{
	public static void showOne(Manage ops) throws EmployeeNotFoundException, IdFormatWrongException {
		String loggedInID=LoginAndPassword.getLoggedInAdminId();
            ops.showOne(loggedInID);
	}
	
	public static void showAll(Manage ops){
		try {
			ops.showAll();
		}
		catch(EmployeeNotFoundException | IdFormatWrongException e) {
			System.out.println("Error:"+e.getMessage());
		}
	}
}
