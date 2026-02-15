package util;

import java.sql.Connection;
import java.sql.SQLException;

public class MakeConnection {

	public Connection connect_to_db(String host, String dbname, String user, String pass, boolean ssl) throws SQLException {
		Connection conn = null;
		try {
			Class.forName("org.postgresql.Driver");
			
			String url = "jdbc:postgresql://" + host + ":5432/" + dbname;
			
			 if (ssl) {
		            url += "?sslmode=require";
		        }

			conn = java.sql.DriverManager.getConnection(url, user, pass);

			System.out.println("Connection successful");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
