package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Jdbc_pro13 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String useName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	void meth1() {
		System.out.println("Implementing Callabale Statement\n");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, useName, pwd);
			CallableStatement cstm = con.prepareCall("{call insertData(?,?,?,?,?)}");
			
			System.out.println("Enter the Employee Id : ");
			String empId = sc.next();
			
			System.out.println("Enter the Employee Name : ");
			String empName = sc.next();
			
			System.out.println("Enter the Employee Desg : ");
			String empDesg = sc.next();
			
			System.out.println("Enter the Employee Basic Sal : ");
			int empBSal = Integer.parseInt(sc.next());
			
			float empTSal = empBSal + (0.35f*empBSal) + (0.15f*empBSal);
			
			cstm.setString(1, empId);
			cstm.setString(2, empName);
			cstm.setString(3, empDesg);
			cstm.setInt(4, empBSal);
			cstm.setFloat(5, empTSal);
			cstm.execute();
			
			System.out.println("Data is inserted !!!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro13().meth1();
	}
}
