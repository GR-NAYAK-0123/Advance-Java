package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro3 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	String query1 = "insert into Employee values('102', 'Elon', 'Musk', 10000, 'America')";
	String query2 = "select * from Employee";
	
	Scanner sc = new Scanner(System.in);
	
	void insertData() {
		
		System.out.println("Inserting the data into the database");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			System.out.println("Connection created");
			Statement stm = con.createStatement();
			int countRow = stm.executeUpdate(query1);
			
			if(countRow > 0) {
				System.out.println("Data is Inserted");
				System.out.println("Do you want to see the data (Yes/No) ");
				
				switch(sc.next()) {
				
				     case "Yes" : 
				    	 ResultSet rs = stm.executeQuery(query2);
				    	 while(rs.next()) {
				    		 System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
				    	 }
				    	 break;
				     case "No" :
				    	 System.out.println("Thank You !!!!");
				    	 break;
				     default : 
				    	 System.out.println("Invalid Input");
				}
			}
			else {
				System.out.println("Data is NOT Inserted");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_pro3 obj = new Jdbc_pro3();
		obj.insertData();
	}
}
