package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_pro10 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	void meth1() {
		
		System.out.println("Implementing Updatable on Scrollable ResultSet");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			ResultSet rs = stm.executeQuery("select Eid, EFName, ESal from Employee");
			
			while(rs.next()) {
				String id = rs.getString(1);
				if(id.equals("10")) {
					System.out.println("Updating the salary for the EMployee : "+id);
					rs.updateInt(3, 900000);
					rs.updateRow();
				}
			}
			System.out.println("Data Updated !!!");
			
			ResultSet rs2 = stm.executeQuery("select * from Employee");
			rs2.first();
			if(rs2.next())
				System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getString(3)+" "+rs2.getInt(4)+" "+rs2.getString(5));
			
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro10().meth1();
	}
}
