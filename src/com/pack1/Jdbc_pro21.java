package com.pack1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Jdbc_pro21 {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	String query1 = "insert into mydata values (?,?)";
	String query2 = "select * from mydata";
	
	void meth1() {
		System.out.println("Inserting an image into the datatbase");
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			
			PreparedStatement pstm = con.prepareStatement(query1);
			
			pstm.setString(1, "101");
			
			FileInputStream fis = new FileInputStream("C:\\Users\\rajan\\Desktop\\RAJA DOCUMENTS\\IMG_9619.JPG");
			
			pstm.setBlob(2, fis, fis.available());
			
			int rowCount = pstm.executeUpdate();
			
			if(rowCount == 0)
				throw new SQLException("Data is NOT inserted!!!");
			System.out.println("Data Inserted Successfully");
			
			PreparedStatement pstm2 = con.prepareStatement(query2);
			
			ResultSet rs = pstm2.executeQuery();
			
			if(rs.next()) {
				Blob b = rs.getBlob(2);
				byte arr[] = b.getBytes(1, (int)b.length());
				
				FileOutputStream fos = new FileOutputStream("C:\\Users\\rajan\\Desktop\\RAJA DOCUMENTS\\img10.JPG");
				
				fos.write(arr);
				
				System.out.println("Image Retrieved");
				fos.close();
			}
				
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro21().meth1();
	}
}
