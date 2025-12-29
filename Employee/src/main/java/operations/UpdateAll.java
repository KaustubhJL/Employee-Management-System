package operations;

//import java.util.ArrayList;
import java.util.Scanner;

import controller.Manage;

public class UpdateAll {
	public static void updateAll(Manage ops, Scanner sc, String id)throws Exception{
		UpdateName.updateName(ops, sc, id);
		UpdateMail.updateMail(ops,sc,id);
		UpdateAddress.updateAddress(ops, sc, id);
		UpdateDepartment.updateDepartment(ops, sc, id);
		UpdateRole.updateRole(ops, sc, id);
		
	}
}
