package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro6 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String useName = "system";
	String pwd = "root";
	String query1 = "select * from ";
	
	Scanner sc = new Scanner(System.in);
	
	Connection getConnect() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, useName, pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	void retrieveData() {
		System.out.println("Retrieving the data from the table");
		System.out.println("Enter the table Name : ");
		String table = sc.next();
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(query1.concat(table));
			
			if(!rs.next()) {
				System.out.println("Table is Empty");
			}
			else {
				do{
					for(int i = 1;i<=5;i++) {
						if(i == 4)
							System.out.print(rs.getInt(i)+" ");
						else
							System.out.print(rs.getString(i)+" ");
					}
					System.out.println();
				}while(rs.next());
			}
		}
		catch(SQLSyntaxErrorException ssee) {
			System.out.println("There is no table with this name : "+table);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void insertData() {
		System.out.println("Inserting the data into the Employee table");
		
		System.out.println("Please enter the Employee Id : ");
		String empId = sc.next();
		System.out.println("Please enter the Employee FIrst Name : ");
		String empFName = sc.next();
		System.out.println("Please enter the Employee Last Name : ");
		String empLName = sc.next();
		System.out.println("Please enter the Employee Salary : ");
		int empSal = Integer.parseInt(sc.next());
		System.out.println("Please enter the Employee Address : ");
		String empAdd = sc.next();
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("insert into Employee values ('"+empId+"', '"+empFName+"', '"+empLName+"', "+empSal+", '"+empAdd+"')");
			if(rowCount > 0) {
				System.out.println("Data Inserted");
				retrieveData();
			} else {
				System.out.println("Data is not Inserted");
			}
		}
		catch(SQLIntegrityConstraintViolationException sicve) {
			System.out.println("Duplicate Employee ID are not allowed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	void retrievingSpecificData() {
		
		System.out.println("Retrieving the specific data from the table Employee");
		
		System.out.println("Please enter the Employee Id : ");
		String empId = sc.next();
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("Select * from Employee where Eid = '"+empId+"'");
			
			if(!rs.next()) {
				System.out.println("There is no record found in the Employee table for this Employee Id : "+empId);	
			}
			else {
				System.out.println("Data is successfully retrieved");
				for(int i = 1;i<=5;i++) {
					if(i == 4)
						System.out.print(rs.getInt(i)+" ");
					else
						System.out.print(rs.getString(i)+" ");
				}
				System.out.println();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Jdbc_pro6 obj = new Jdbc_pro6();
		//obj.retrieveData();
		//obj.insertData();
		obj.retrievingSpecificData();
	}
}










