package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class Jdbc_pro14 {
	

	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	void meth1() {
		System.out.println("Implementing Callable Statement-procedure\n");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			CallableStatement cstm = con.prepareCall("{call retrieveData(?,?,?,?,?)}");
			
			System.out.println("Enter the Employee Id : ");
			String empId = sc.nextLine();
			
			cstm.setString(1, empId);
			cstm.registerOutParameter(2, Types.VARCHAR);
			cstm.registerOutParameter(3, Types.VARCHAR);
			cstm.registerOutParameter(4, Types.INTEGER);
			cstm.registerOutParameter(5, Types.INTEGER);
			cstm.execute();
			
			System.out.println("--------Retrieving Data----------");
			System.out.println("empId : "+empId);
			System.out.println("Employee Name : "+cstm.getString(2));
			System.out.println("Employee Desg : "+cstm.getString(3));
			System.out.println("Employee BSal : "+cstm.getInt(4));
			System.out.println("Employee TSal : "+cstm.getInt(5));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro14().meth1();
	}
}
