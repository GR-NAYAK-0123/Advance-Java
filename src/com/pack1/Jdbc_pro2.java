package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Jdbc_pro2 {
	void getConnection() {
		
		System.out.println("Implementing Statement Interface");
		
		String driver = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uname = "system";
		String pwd = "root";
		String query = "select * from Employee";
		String query1 = "select * from Employee where Eid = '101'";
		
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, uname, pwd);
			System.out.println("Connection created");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query);
			//con.close();
			
			/*
			if(rs.next()) {
				System.out.println("Data is present in the result set");
			} else {
				System.out.println("Data is not present");
			}
			*/
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_pro2 obj = new Jdbc_pro2();
		obj.getConnection();
	}
}
