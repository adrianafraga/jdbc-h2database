package com.qintess.dbaconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection() {
		String url = "jdbc:h2:~/test";
		try{
			return DriverManager.getConnection(url);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			rs.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
