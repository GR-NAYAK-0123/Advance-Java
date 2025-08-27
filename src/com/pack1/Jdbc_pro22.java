package com.pack1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_pro22 {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	String query1 = "insert into mydata2 values (?,?)";
	String query2 = "select * from mydata2 where id = ?";
	
	void meth1() {
		System.out.println("Inserting the text file Into the database by using CLOB");
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			
			PreparedStatement pstm = con.prepareStatement(query1);
			pstm.setString(1, "101");
			
			BufferedReader br = new BufferedReader(new FileReader("D:\\Core Java\\file1.txt"));
			pstm.setClob(2, br);
			
			int rowCount  = pstm.executeUpdate();
			
			if(rowCount == 0) {
				throw new SQLException("Data is NOT Inserted");
			}
			System.out.println("Data is Inserted successfully");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void meth2() {
		System.out.println("Retrieving the data from the database");
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			
			PreparedStatement pstm = con.prepareStatement(query2);
			pstm.setString(1, "101");
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()) {
				Clob b = rs.getClob(2);
				Reader data = b.getCharacterStream();
				BufferedReader br = new BufferedReader(data);
				FileWriter fw = new FileWriter("D:\\Core Java\\file3.txt");
				//String line;
				int i;
//				while((line = br.readLine()) != null) {
//					fw.write(line);
//					fw.write("\n");
//				}
				while((i = br.read()) != -1) {
					fw.write(i);
				}
				br.close();
				fw.close();
				System.out.println("Clob data retrieved");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_pro22 obj = new Jdbc_pro22();
		//obj.meth1();
		obj.meth2();
	}
}
