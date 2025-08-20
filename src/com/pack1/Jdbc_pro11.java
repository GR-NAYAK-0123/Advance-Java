package com.pack1;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro11 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	String query = "select * from Employee";
	
	void meth1() {
		System.out.println("Implementing Jdbc RowSet Interface");
		
		try {
			RowSetFactory rsf = RowSetProvider.newFactory();
			JdbcRowSet jrs = rsf.createJdbcRowSet();
			jrs.setUrl(url);
			jrs.setUsername(userName);
			jrs.setPassword(pwd);
			jrs.setCommand(query);
			jrs.execute();
			
			//jrs.close();  // In jdbc RowSet it needs to be connected 
			
			while(jrs.next())
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getInt(4)+" "+jrs.getString(5));
			
			System.out.println("-------------------------");
			jrs.afterLast();
			while(jrs.previous())
				System.out.println(jrs.getString(1)+" "+jrs.getString(2)+" "+jrs.getString(3)+" "+jrs.getInt(4)+" "+jrs.getString(5));
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro11().meth1();
	}
}
