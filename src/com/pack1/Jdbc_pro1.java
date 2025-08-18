package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

public class Jdbc_pro1 {
	void getConnection() {
		String driver = "oracle.jdbc.OracleDriver";
		String dbURL = "jdbc:oracle:thin:@localhost:1521:xe";
		String dbunsme = "system";
		String dbpwd = "root";
		
		System.out.println("Connecting our java program to database");
		
		try {
			Class.forName(driver); //1st step -> Loading the driver
			Connection con = DriverManager.getConnection(dbURL, dbunsme, dbpwd);
			System.out.println("Connection created");
			con.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_pro1 obj = new Jdbc_pro1();
		obj.getConnection();
	}
}
