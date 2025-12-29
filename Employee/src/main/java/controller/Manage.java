	package controller;
	import java.util.ArrayList;	
	import java.util.List;
	import crudmethods.*;
import customExceptions.EmployeeNotFoundException;
import customExceptions.IdFormatWrongException;
import customExceptions.InvalidDataException;
import model.CrudInterface;
import model.EmployeeConstructor;

import java.time.Year;
	
	
	public class Manage implements CrudInterface{
	 	protected ArrayList<EmployeeConstructor> al=new ArrayList<>();
	 	String dID;
	 	
	 	public String setNextId(ArrayList<EmployeeConstructor>al) {
		    int max = 000;
		    int next;
		    for (EmployeeConstructor e : al) {
		        	String id = e.getId();
		        	int num=Integer.parseInt(id.substring(4));
		        	max=Math.max(max,num);
		    }
		    next=max+1;
		    int year=Year.now().getValue() % 100;
		    return "TT"+year+String.format("%03d",next);
		}
	 	
	 	
		public EmployeeConstructor findEmp(String id) {
			for(EmployeeConstructor e: al) {
				if(e.getId().equals(id)) return e;
			}
			return null;
		}
		
		
		public void setEmployees(List<EmployeeConstructor> al) {
		    this.al = new ArrayList<>(al);
		    this.dID = setNextId(this.al);
		}
		public List<EmployeeConstructor> getEmployees() {
			return al;
		}
		
		
		public void add(String name, String mail, String address, String department, ArrayList<String>role,String password) throws InvalidDataException{
			AddMethod.add(al, setNextId(al), name, mail, address, department, role,password);
		}
	
		public void delete(String id) throws EmployeeNotFoundException,IdFormatWrongException {
			DeleteMethod.delete(al, findEmp(id), id);
		}
		
		public void showAll() throws EmployeeNotFoundException,IdFormatWrongException {
				ShowMethod.showAll(al);
		}
		public void showOne(String id) throws EmployeeNotFoundException,IdFormatWrongException {
			if(id!=null)
				ShowMethod.showOne(al,findEmp(id));
		}
		public void updateName(String id, String name) throws EmployeeNotFoundException,IdFormatWrongException {
			UpdateNameMethod.updateName(al,findEmp(id), id, name);
		}
		public void updateMail(String id, String mail) throws EmployeeNotFoundException,IdFormatWrongException {
			UpdateMailMethod.updateMail(al,findEmp(id), id, mail);
		}
		public void updateAddress(String id, String address) throws EmployeeNotFoundException,IdFormatWrongException {
			UpdateAddressMethod.updateAddress(al,findEmp(id), id, address);
		}
		public void updateDepartment(String id, String department) throws EmployeeNotFoundException,IdFormatWrongException {
			UpdateDepartmentMethod.updateDepartment(al,findEmp(id), id, department);
		}
		public void updateRole(String id, ArrayList<String> role) throws EmployeeNotFoundException,IdFormatWrongException {
			UpdateRoleMethod.updateRole(al,findEmp(id), id, role);
		}
		public boolean employeeExists(String id) {
		    return findEmp(id) != null;
		}
		public boolean ArrayListIsEmpty() {
			return al.isEmpty();
		}
		public void updatePassword(String loggedInID, String updatedPass) {
		    UpdatePasswordMethod.updatePassword(al, loggedInID,updatedPass);
		}
		public void resetPassword(String selectedID, String mailCheck, String resetPass)  throws EmployeeNotFoundException,IdFormatWrongException {
			UpdatePasswordMethod.resetPassword(al,selectedID,mailCheck,resetPass);
		}
		public void resetPassword(String loggedInID, String resetPass) {
			UpdatePasswordMethod.resetPassword(al,loggedInID,resetPass);
		}
		public static String defaultPassword() {
			return "Default123";
		}
		
		
		public void addRole(String id, String role)
		        throws EmployeeNotFoundException, IdFormatWrongException {
		    EmployeeConstructor e = findEmp(id);
		    if (e == null) throw new EmployeeNotFoundException("Employee not found");
		    if (!e.getRole().contains(role)) {
		        e.getRole().add(role);
		    }
		}
		public void revokeRole(String id, String role)
		        throws EmployeeNotFoundException, IdFormatWrongException {
		    EmployeeConstructor e = findEmp(id);
		    if (e == null) throw new EmployeeNotFoundException("Employee not found");
		    e.getRole().remove(role);
		}
		
	}