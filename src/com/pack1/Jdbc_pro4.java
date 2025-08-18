package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro4 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	void dataInsert() {
		
		System.out.println("Enter the Employee Id : ");
		String empId = sc.next();
		System.out.println("Enter the Employee First Name : ");
		String empFName = sc.next();
		System.out.println("Enter the Employee Last Name : ");
		String empLName = sc.next();
		System.out.println("Enter the Employee Salary : ");
		int empSal = Integer.parseInt(sc.next());
		System.out.println("Enter the Employee Address : ");
		String empAdd = sc.next();
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("insert into Employee values('"+empId+"', '"+empFName+"', '"+empLName+"', '"+empSal+"', '"+empAdd+"')");
			
			if(rowCount > 0) {
				System.out.println("Data is Inseted");
			}
			else {
				System.out.println("Data is not inserted");
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
		Jdbc_pro4 obj = new Jdbc_pro4();
		obj.dataInsert();
	}
}
