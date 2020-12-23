package com.te.hospitalmanagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnection {
	public static Connection c = null;
	private GetConnection()
	{
		
	}
	public static Connection getConnection(){
		if(c == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital","root","root");
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			return c;
		}else {
			return c;	
		}
	}

}
