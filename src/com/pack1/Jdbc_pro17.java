package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;

public class Jdbc_pro17 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	String query1 = "update TrainSeatAvailability set AVAILABLE_SEATS = AVAILABLE_SEATS - 1 where TRAIN_ID = ? and JOURNEY_DATE = ? and CLASS = ? and AVAILABLE_SEATS > 0";
	String query2 = "insert into BookingDetails values (?, ?, ?, ?, ?)";
	String query3 = "select payment_status from CustomerPayment where customer_id = ?";
	String query4 = "update BookingDetails set status = 'Payment-Confirmed' where customer_id = ?";
	
	void meth1() {
		System.out.println("Implementing Transaction-Management\n");
		
		try {
			Class.forName(driver);
			Connection con= DriverManager.getConnection(url, userName, pwd);
			System.out.println("Connection created");
			
			System.out.println("getAutoCommit() : "+con.getAutoCommit());
			con.setAutoCommit(false);
			System.out.println("getAutoCommit() : "+con.getAutoCommit());
			
			
			PreparedStatement pstm1 = con.prepareStatement(query1);
			
			pstm1.setString(1, "12345");
			pstm1.setString(2, "2024-10-10");
			pstm1.setString(3, "Sleeper");
			
			int rowCount = pstm1.executeUpdate();
			
			if(rowCount == 0) {
				throw new SQLException("There are NO Seats");
			}
			System.out.println("Seat is Locked but NOT yet confirmed !!!");
			
			Savepoint sp1 = con.setSavepoint();
			
			PreparedStatement pstm2 = con.prepareStatement(query2);
			pstm2.setString(1, "B103");
			pstm2.setString(2, "12345");
			pstm2.setString(3, "C123");
			pstm2.setInt(4, 20);
			pstm2.setString(5, "Payment-Pending");
			
			int rowCount2 = pstm2.executeUpdate();
			
			if(rowCount2 == 0) {
				throw new SQLException("Booking record NOT created");
			}
			System.out.println("Booking record created and waiting for payment status");
			
			PreparedStatement pstm3 = con.prepareStatement(query3);
			pstm3.setString(1, "C124");
			
			ResultSet rs = pstm3.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals("Success")) {
					PreparedStatement pstm4 = con.prepareStatement(query4);
					pstm4.setString(1, "C123");
					
					int rowCount3 = pstm4.executeUpdate();
					if(rowCount3 == 0) {
						throw new SQLException("Payment Failed");
					}
					System.out.println("Seat Confirmed");
					System.out.println("Ticket Generated");
					con.commit();
					System.out.println("Save Point Released");
				}
				else {
					System.out.println("Payment NOT Done !!!");
					System.out.println("Transaction rollback to last Savepoint");
					con.rollback(sp1);
				}
			}
			else {
				System.out.println("Payment NOT Done !!!");
				System.out.println("Transaction rollback to last Savepoint");
				con.rollback(sp1);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro17().meth1();
	}
}
