package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

public class Jdbc_pro18 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	String query1 = "update TrainSeatAvailability set available_seats = available_seats - 1 where train_id = ? and journey_date = ? and class_name = ? and available_seats > 0";
	String query2 = "insert into BookingDetails values (?,?,?,?,'Payment-Pending')";
	String query3 = "insert into CustomerPayment values (?,?,?, 'Success')";
	String query4 = "select payment_status from CustomerPayment where customer_id = ?";
	String query5 = "update BookingDetails set status = 'Payment-Confirmed' where customer_id = ?";
	
	void meth1() {
		System.out.println("Implementing Transaction Management by using users input\n");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, userName, pwd);
			System.out.println("Connection Created\n");
			
			con.setAutoCommit(false);
			
			PreparedStatement pstm1 = con.prepareStatement(query1);
			
			System.out.println("Enter the Train Id : ");
			String train_id = sc.next();
			
			System.out.println("Enter the Journey Date : ");
			String journey_date = sc.next();
			
			System.out.println("Enter the Class : ");
			String class_name = sc.next();
			
			pstm1.setString(1, train_id);
			pstm1.setString(2, journey_date);
			pstm1.setString(3, class_name);
			
			int rowCount1 = pstm1.executeUpdate();
			
			if(rowCount1 == 0)
				throw new SQLException("Train is NOT available or Seat is NOT available");
			System.out.println("Seat is locked");
			
			Savepoint sp1 = con.setSavepoint();
			
			PreparedStatement pstm2 = con.prepareStatement(query2);
			
			System.out.println("Enter the Booking Id : ");
			String booking_id = sc.next();
			
			System.out.println("Enter the Train Id : ");
			String Train_id = sc.next();
			
			System.out.println("Enter the Customer Id : ");
			String Customer_id = sc.next();
			
			System.out.println("Enter the Seat Number : ");
			int seat_number = Integer.parseInt(sc.next());
			
			
			pstm2.setString(1, booking_id);
			pstm2.setString(2, Train_id);
			pstm2.setString(3, Customer_id);
			pstm2.setInt(4, seat_number);
			
			int rowCount2 = pstm2.executeUpdate();
			
			if(rowCount2 == 0) {
				throw new SQLException("Payment record NOT created");
			}
			System.out.println("Payment record created and waiting for the payment");
			
			PreparedStatement pstm3 = con.prepareStatement(query3);
			
			System.out.println("Enter the Payment Id : ");
			String payment_id = sc.next();
			
			System.out.println("Enter the Customer Id : ");
			String CUSTomer_id = sc.next();
			
			System.out.println("Enter the Amount : ");
			int amount = Integer.parseInt(sc.next());
			
			pstm3.setString(1, payment_id);
			pstm3.setString(2, CUSTomer_id);
			pstm3.setInt(3, amount);
			
			int rowCount4 = pstm3.executeUpdate();
			
			if(rowCount4 == 0) {
				throw new SQLException("Payment NOT Complete");
			}
			System.out.println("Payment Process Successfully Completed");
			
			PreparedStatement pstm4 = con.prepareStatement(query4);
			
			System.out.println("Enter the customer Id :");
			String CUstomer_id = sc.next();
			
			pstm4.setString(1, CUstomer_id);
			
			ResultSet rs = pstm4.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals("Success")) {
					PreparedStatement pstm5 = con.prepareStatement(query5);
					
					System.out.println("Enter the Customer Id : ");
					String CUStomer_id = sc.next();
					
					pstm5.setString(1, CUStomer_id);
					
					int rowCount3 = pstm5.executeUpdate();
					
					if(rowCount3 == 0)
						throw new SQLException("The enterd customer Id id NOT found");
					System.out.println("Seat Confirmed");
					System.out.println("Ticket Generated");
					con.commit();
				}
				else {
					System.out.println("Payment has NOT done yet");
					System.out.println("Transaction rollback to last save point");
					con.rollback(sp1);
				}
			}
			else {
				System.out.println("Payment has NOT done yet");
				System.out.println("Transaction rollback to last save point");
				con.rollback(sp1);
			}
			con.close();
			rs.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro18().meth1();
	}
}
