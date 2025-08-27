package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_pro23 {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	void meth1() {
		System.out.println("Implementing Batch Processing");
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			Statement stm = con.createStatement();
			
			System.out.println("How many queries you want to add to the batch");
			int no_queries = Integer.parseInt(sc.nextLine());
			
			/*
			   1) create table emp11 (eid varchar2(20) primary key, ename varchar2(20));
			   2) create table pro11 (pid varchar2(20) primary key, pname varchar2(20));
			   3) insert into emp11 values ('101', 'Raja');
			   4) insert into pro11 values ('102', 'Mac14');
			   
			   => Whenever you give the query in the console no need to give the statement terminator
			 */
			
			for(int i = 1;i<=no_queries;i++) {
				System.out.println("Enter your"+i+" query : ");
				stm.addBatch(sc.nextLine());
			}
			System.out.println("\nAll the queries are added to the batch");
			
			int arr[] = stm.executeBatch();
			
			for(int x : arr)
				System.out.println("------"+x+"-------");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
   }
	public static void main(String[] args) {
		new Jdbc_pro23().meth1();
	}
}
