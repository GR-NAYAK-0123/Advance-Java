package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc_pro9 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Connection getConnect() {
		
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userName, pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	void meth1() {
		
		System.out.println("Implementing Scrollable ResultSet");
		
		try {
			Connection con = getConnect();
			
			//Statement stm = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			Statement stm = con.createStatement(1004, 1007);
			
			ResultSet rs = stm.executeQuery("select * from Employee");
			
			//Before the while loop that rs points to the before first row
			while(rs.next())
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
//			System.out.println("-----------------------");
//			rs.afterLast();  // Now the cursor pointing to the after last row
//			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5)); // Now i will get an exception
			
			System.out.println("-----------------------");
			rs.last();  // Now the points to the last row
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
			System.out.println("-----------------------");
			rs.beforeFirst(); // Now the cursor points to before first
			if(rs.next())  // Now the cursor points to the first row
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
			System.out.println("--------------------------");
			rs.first();
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
			System.out.println("----------------------------");
			//rs.last();
			rs.afterLast(); // Now the cursor points to the after last row
			while(rs.previous())
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
			System.out.println("---------------------------");
			rs.absolute(2);
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			rs.absolute(-5);
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
			System.out.println("----------------------------");
			rs.relative(3); // The cursor points to the number of row forward from the last pointing
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			rs.relative(-2); // The cursor points to the number of row backward from the last pointing
			System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4)+" "+rs.getString(5));
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro9().meth1();
	}
	
}
