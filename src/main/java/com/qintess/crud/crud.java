package com.qintess.crud;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.qintess.dbaconnection.ConnectionFactory;

public class crud {

	public static void insert() {
		Connection conn = ConnectionFactory.getConnection();
		String createQuery = "CREATE TABLE CLIENTE(id int primary key, name varchar(255))";
		String insertQuery1 = "INSERT INTO CLIENTE(id, name) VALUES(1, 'Victor')";
		String insertQuery2 = "INSERT INTO CLIENTE(id, name) values(2, 'Leandro')";
		String insertQuery3 = "INSERT INTO CLIENTE(id, name) values(3, 'Paulo')";
		String selectQuery = "Select * from CLIENTE";
	
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(createQuery);
			stmt.execute(insertQuery1);
			stmt.execute(insertQuery2);
			stmt.execute(insertQuery3);

			ResultSet rs = stmt.executeQuery(selectQuery);
			while(rs.next()) {
				System.out.println("id: " + rs.getInt("id") + " | Nome: " + rs.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
