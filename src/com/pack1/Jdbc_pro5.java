package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro5 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	//String query1 = "delete from Employee where Eid = '150'";
	
	Scanner sc = new Scanner(System.in);
	
	void deleteData() {
		
		System.out.println("Enter the Emp Id : ");
		String EmpId = sc.next();
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("delete from Employee where Eid = '"+EmpId+"'");
			
			if(rowCount > 0) {
				System.out.println("Data is deleted successfully");
			}
			else {
				System.out.println("Data is NOT deleted");
			}
			
			ResultSet rs = stm.executeQuery("select * from Employee");
			
			while(rs.next()) {
				for(int i = 1;i<=5;i++) {
					if(i == 4)
						System.out.print(rs.getInt(i)+" ");
					else
						System.out.print(rs.getString(i)+" ");
				}
				System.out.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro5().deleteData();
	}
	
}
