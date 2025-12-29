package main;

import java.io.File;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import controller.Manage;
import controller.Login;
import model.EmployeeConstructor;
import operations.LoginAndPassword;

public class CrudAppMain {

    private static final String FILE_PATH = "employees_data.json";

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Manage ops = new Manage();

        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);

        if (file.exists() && file.length() > 0) {
            List<EmployeeConstructor> list = mapper.readValue(
                    file, new TypeReference<List<EmployeeConstructor>>() {});
            ops.setEmployees(list);
        }
        
        Login.authenticate(ops, sc);
        String loggedInId = LoginAndPassword.getLoggedInAdminId();

        EmployeeConstructor loggedInEmployee = ops.findEmp(loggedInId);

        if (loggedInEmployee.getRole().contains("Admin")) {
            AdminMenu.showMenu(ops, sc, mapper, file);
        } 
        else if (loggedInEmployee.getRole().contains("Manager")) {
            ManagerMenu.showMenu(ops, sc, mapper, file);
        } 
        else {
            EmployeeMenu.showMenu(ops, sc);
        }

        sc.close();
    }
}