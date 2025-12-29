package app;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import checkaccess.accesscheck;
import manager.Manage;
import modelconstructor.Employee;
import operations.LoginAndPassword;

public class CrudAppMain {

    private static final String FILE_PATH = "employees_data.json";

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Manage ops = new Manage();

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) {
            List<Employee> list = mapper.readValue(
                    file, new TypeReference<List<Employee>>() {});
            ops.setEmployees(list);
        }
        
        accesscheck.authenticate(ops, sc);
        String loggedInId = LoginAndPassword.getLoggedInAdminId();

        Employee loggedInEmployee = ops.findEmp(loggedInId);

        if (loggedInEmployee.getRole().contains("Admin")) {
            AdminCruds.showMenu(ops, sc, mapper, file);
        } 
        else if (loggedInEmployee.getRole().contains("Manager")) {
            ManagerCruds.start(ops, sc, mapper, file);
        } 
        else {
            EmployeeCruds.start(ops, sc);
        }

        sc.close();
    }
}