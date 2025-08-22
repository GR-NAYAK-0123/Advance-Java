package com.pack1;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class Jdbc_pro15 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	void meth1() {
		System.out.println("Implementing Callable statement by using function\n");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			CallableStatement cstm = con.prepareCall("{call ?:=retrieveEmpSal(?)}");
			
			System.out.println("Enter the Employee Id : ");
			String empId = sc.next();
			
			
			cstm.registerOutParameter(1, Types.FLOAT);
			cstm.setString(2, empId);
			cstm.execute();
			
			System.out.println("------Employee Details-------");
			System.out.println("Employee Id : "+empId);
			System.out.println("Employee Total Salary : "+cstm.getFloat(1));
			
			con.close();
		}
		catch(SQLException e) {
			System.out.println("There is NO Data");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro15().meth1();
	}
}
