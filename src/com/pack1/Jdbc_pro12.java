package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro12 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	String query = "select Eid, EFName, ESal from Employee";
	
	void meth1() {
		
		System.out.println("Implementing Catched RowSet Interface");
		
		try {
			RowSetFactory rsf = RowSetProvider.newFactory();
			CachedRowSet crs= rsf.createCachedRowSet();
			crs.setUrl(url);
			crs.setUsername(userName);
			crs.setPassword(pwd);
			crs.setCommand(query);
			crs.execute();
			
			
			while(crs.next()) {
				String id = crs.getString(1);
				if(id.equals("10")) {
					System.out.println("Updating the salary for Employee : "+id);
					crs.updateInt(3, 3500);
					crs.updateRow();
				}
			}
			Connection con = DriverManager.getConnection(url, userName, pwd);
			con.setAutoCommit(false);
			crs.acceptChanges(con);
			con.close();

//			crs.acceptChanges();  // Before, it works perfectly but right now we need to give the explicit connection that given at the above
			System.out.println("Data is updated");
			
			System.out.println("----------------");
			
			crs.beforeFirst();
			while(crs.next())
				if(crs.getString(1).equals("10"))
					System.out.println(crs.getString(1)+" "+crs.getString(2)+" "+crs.getInt(3));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro12().meth1();
	}
}
