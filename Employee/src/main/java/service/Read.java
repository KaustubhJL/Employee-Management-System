package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import model.Employee;

public class Read {

	// currently only for db, file reading operations in employeelistOps.java

	private static final Logger logger = LoggerFactory.getLogger(Read.class);
	
	

	public static List<Employee> readAllDB(Connection conn) {
		List<Employee> list = new ArrayList<>();

		try {
			String query = """
					    SELECT empid, empname, empmail, empaddress, empdepartment
					    FROM employees WHERE active IS TRUE
					""";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Employee(rs.getString("empid"), rs.getString("empname"), rs.getString("empmail"),
						rs.getString("empaddress"), rs.getString("empdepartment"), new ArrayList<>(), null));
			}
			System.out.println("Employees data fetched");
			logger.info("Employees data fetched");
		} catch (Exception e) {
			System.out.println("Error fetching all employees: "+e.getMessage());
			logger.error("Error fetching all employees: {}", e.getMessage(), e);
		}

		return list;
	}

	public static Employee readOneDB(Connection conn, String id) {
		try {
			String query = """
					    SELECT empid, empname, empmail, empaddress, empdepartment
					    FROM employees
					    WHERE empid = ? AND active IS TRUE
					""";

			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				System.out.println("Employee with ID "+id+" not found");
				logger.warn("Employee with ID {} not found", id);
				return null;
			}
			System.out.println("Fetched employee with ID "+ id);
			logger.info("Fetched employee with ID {}", id);
			return new Employee(rs.getString("empid"), rs.getString("empname"), rs.getString("empmail"),
					rs.getString("empaddress"), rs.getString("empdepartment"), new ArrayList<>(), null);

		} catch (Exception e) {
			System.out.println("Error fetching details of employee"+id+" : "+ e.getMessage());
			logger.error("Error fetching self details for employee {}", id,e);
			return null;
		}
	}

	public static Employee readSelfDB(Connection conn) {
		String id=PasswordMethods.getLoggedInId();
		try {
			ArrayList<String> roles = new ArrayList<>();
			String roleQuery = "SELECT role FROM roles WHERE empid = ? AND active IS TRUE";
			try (PreparedStatement ps = conn.prepareStatement(roleQuery)) {
				ps.setString(1, id);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					roles.add(rs.getString("role"));
				}
			}

			String empQuery = """
					    SELECT empid, empname, empmail, empaddress, empdepartment
					    FROM employees
					    WHERE empid = ? AND active IS TRUE
					""";

			PreparedStatement ps = conn.prepareStatement(empQuery);
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				return null;
			}
			System.out.println("Fetched your details.");
			logger.info("Fetched self details for employee ID {}", id);

			return new Employee(rs.getString("empid"), rs.getString("empname"), rs.getString("empmail"),
					rs.getString("empaddress"), rs.getString("empdepartment"), roles, null);

		} catch (Exception e) {
			System.out.println("Error fetching your details: "+e.getMessage());
			logger.error("Error fetching self details for employee {}", id, e);
			return null;
		}
	}

	
	public static List<Employee> readInactive(Connection conn) {
		List<Employee> list = new ArrayList<>();

		try {
			String query = """
					    SELECT empid, empname, empmail, empaddress, empdepartment
					    FROM employees WHERE active IS FALSE;
					""";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				list.add(new Employee(rs.getString("empid"), rs.getString("empname"), rs.getString("empmail"),
						rs.getString("empaddress"), rs.getString("empdepartment"), new ArrayList<>(), null));
			}
			System.out.println("Inactive Employees data fetched");
			logger.info("Inactive Employees data fetched");
		} catch (Exception e) {
			System.out.println("Error fetching all employees: "+e.getMessage());
			logger.error("Error fetching all employees: {}", e.getMessage());
		}
		return list;
	}
	
}