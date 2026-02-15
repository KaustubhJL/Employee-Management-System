package service;

import java.sql.Connection;

import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import enums.RoleChoice;

public class RoleTableDB {
	private static final Logger logger = LoggerFactory.getLogger(RoleTableDB.class);

	public static void insertRole(Connection conn, String empid, RoleChoice role) {
		try {
			String roleQuery = "INSERT INTO roles (empId,role) VALUES (?,?);";
			PreparedStatement ps = conn.prepareStatement(roleQuery);
			ps.setString(1, empid);
			ps.setString(2, role.name());
			ps.executeUpdate();
			logger.info("The role {} added for employee {}", role, empid);
		} catch (Exception e) {
			System.out.println("Error adding role " + role + "for employee " + empid + " : " + e.getMessage());
			logger.error("Error adding role {} for employee {}", role, empid, e.getMessage());
			throw new RuntimeException(e);
		}
	}
}