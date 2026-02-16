package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CrudFileImplementation;
import model.Employee;

public class SaveEmployeesToFile {
	public static void saveToJson(ObjectMapper objectMapper, File file) {
		try {
			List<Employee> employeeList = CrudFileImplementation.findAll();
			objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, employeeList);
			System.out.println("Saved successfully.");
		} catch (IOException e) {
			System.out.println("Error saving employees to JSON: " + e.getMessage());
		}
	}
}
