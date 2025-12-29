package operations;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import manager.Manage;
import modelconstructor.Employee;

public class SaveEmployeesToFile {
	public static void saveToJson(Manage ops, ObjectMapper objectMapper, File file) {
        try {
            List<Employee> employeeList = ops.getEmployees();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employeeList);
            System.out.println("Saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving employees to JSON: " + e.getMessage());
        }
    }
}
