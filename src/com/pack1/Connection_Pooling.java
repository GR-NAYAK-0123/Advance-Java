package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Vector;

public class Connection_Pooling {
	
	String url,userName,pwd;

	public Connection_Pooling(String url, String userName, String pwd) {
		this.url = url;
		this.userName = userName;
		this.pwd = pwd;
	}
	
	Vector<Connection> v = new Vector<Connection>();
	
	void con_Initialization() {
		System.out.println("Initializing Connection Pool");
		System.out.println("Connection Pool is Empty\n");
		
		while(v.size() < 5) {
		   try {
			  Connection con = DriverManager.getConnection(url, userName, pwd);
			  v.addElement(con);
		  }
		  catch(Exception e) {
			e.printStackTrace();
		  }
		}
		for(Connection o : v)
			System.out.println(o);
	}
	
	Connection con_Acquisition() {
		System.out.println("Assigning Connection Object");
		Connection con = v.get(0);
		v.remove(0);
		return con;
	}
	
	void con_ReturningConnectionObject(Connection con) {
		System.out.println("\nAdding connection objet back into Connection Pool");
		v.addElement(con);
		System.out.println("--------------------------");
		for(Connection o : v)
			System.out.println(o);
		System.out.println("--------------------------");
	}
	
	
}
