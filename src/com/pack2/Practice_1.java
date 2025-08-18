package com.pack2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.TreeSet;

public class Practice_1 {
	
	void getConnection() {
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "system";
		String pwd = "root";
		String query = "select * from Employee";
		
		System.out.println("Connecting our java program to the database");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			System.out.println("Connection created");
			Statement sm = con.createStatement();
			ResultSet rs = sm.executeQuery(query);
			
			TreeSet<String> set = new TreeSet<String>();
			while(rs.next()) {
				set.add(rs.getString(1));
			}
			System.out.println("Retrieving the Employee Id from the tree set");
			for(String data : set)
				System.out.println(data);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Practice_1().getConnection();
	}
}
