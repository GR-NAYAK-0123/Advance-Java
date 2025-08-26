package com.pack1;

import java.sql.Connection;

public class Jdbc_pro19 {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Connection_Pooling cp = new Connection_Pooling(url, userName, pwd);
	
	void meth1() {
		System.out.println("Implementing Connection Pooling\n");
		
		cp.con_Initialization();
		
		System.out.println("\n-------User - 1--------");
		System.out.println("===>"+cp.v.size());
		Connection con1 = cp.con_Acquisition();
		System.out.println("Connection Acquired");
		System.out.println(con1);
		System.out.println("===>"+cp.v.size());
		
		System.out.println("\n-------User - 2--------");
		System.out.println("===>"+cp.v.size());
		Connection con2 = cp.con_Acquisition();
		System.out.println("Connection Acquired");
		System.out.println(con2);
		System.out.println("===>"+cp.v.size());
		
		System.out.println("\n-------User - 3--------");
		System.out.println("===>"+cp.v.size());
		Connection con3 = cp.con_Acquisition();
		System.out.println("Connection Acquired");
		System.out.println(con3);
		System.out.println("===>"+cp.v.size());
		
		cp.con_ReturningConnectionObject(con1);
		cp.con_ReturningConnectionObject(con2);
		cp.con_ReturningConnectionObject(con3);
	}
	public static void main(String[] args) {
		new Jdbc_pro19().meth1();
	}
}
