package operations;

import controller.Manage;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
public class ShowEmployee{
	public static void showOne(Manage ops) throws EmployeeNotFoundException, IdFormatWrongException {
		String loggedInID=LoginAndPassword.getLoggedInAdminId();
            ops.showOne(loggedInID);
	}
	
	public static void showAll(Manage ops) throws EmployeeNotFoundException, IdFormatWrongException{
		try {
			ops.showAll();
		}
			catch(EmployeeNotFoundException e) {
				System.out.println("Error: "+e.getMessage());
			}
			catch(IdFormatWrongException e) {
				System.out.println("Error:"+e.getMessage());
		}
	}
}
