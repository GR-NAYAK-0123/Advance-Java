package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Savepoint;

public class Jdbc_pro16 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	void meth1() {
		System.out.println("meth1() called");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			
			System.out.println("getAutoCommit : "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("getAutoCommit : "+con.getAutoCommit());
			
//			Savepoint sp1 = con.setSavepoint();
//			
//			Savepoint sp2 = con.setSavepoint();
//			
			//con.releaseSavepoint(sp1);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro16().meth1();
	}
}
