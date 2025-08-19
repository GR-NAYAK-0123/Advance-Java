package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;
import java.util.Scanner;

public class Library_Management {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	Connection getConnect(){
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
	
	void addBook() {
		
		System.out.println("Adding new Book in the Library");
		
		System.out.println("Please Enter the Book Id : ");
		int bookId = Integer.parseInt(sc.nextLine());
		
		System.out.println("Enter the Book Name : ");
		String bookName = sc.nextLine();
		
		System.out.println("Enter the Author Name : ");
		String authorName = sc.nextLine();
		
		System.out.println("Enter the Book price : ");
		int bookPrice = Integer.parseInt(sc.next());
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("insert into Library values ("+bookId+", '"+bookName+"', '"+authorName+"', "+bookPrice+")");
			
			if(rowCount > 0) {
				System.out.println("Data has successfully added to the Library");
				retrieveBook();
			}
	
		}
		catch(SQLIntegrityConstraintViolationException sicve) {
			System.out.println("The data is NOT added because of duplicate record : "+bookId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void retrieveBook() {
		
		System.out.println("Retrieving Book from the Library : ");
		
		System.out.println("Please enter the Library Name : ");
		String libraryName = sc.next();
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select * from "+libraryName+"");
			
			if(!rs.next()) {
				System.out.println("Data is NOT there");
			}
			else {
				do {
					for(int i = 1;i<=4;i++) {
						if(i == 1 || i == 4)
							System.out.print(rs.getInt(i)+" ");
						else
							System.out.print(rs.getString(i)+" ");
					}
					System.out.println();
				}while(rs.next());
			}
		}
		catch(SQLSyntaxErrorException ssee) {
			System.out.println("You have given the wrong library name : "+libraryName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	void deleteBook() {
		
		System.out.println("Deleting the Book from the Library : ");
		
		System.out.println("Enter the Book Id : ");
		int bookId = Integer.parseInt(sc.next());
		
		try {
			Connection con = getConnect();
			Statement stm = con.createStatement();
			int rowCount = stm.executeUpdate("delete from Library where bookId = "+bookId+"");
			
			if(rowCount > 0) {
				System.out.println("The book is deletd form the Library ");
				retrieveBook();
			}
			else {
				System.out.println("The record has not found : "+bookId);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		Library_Management obj = new Library_Management();
		//obj.addBook();
		//obj.retrieveBook();
		obj.deleteBook();
	}
}
