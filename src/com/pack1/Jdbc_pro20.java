package com.pack1;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Scanner;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Jdbc_pro20 {
	
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	String query1 = "select efname, elname from Employee where eid = ?";
	String query2 = "select * from Employee";
	
	public void meth1() {
		System.out.println("Implementing Metadata in JDBC\n");
		
		try {
			Connection con = DriverManager.getConnection(url, userName, pwd);
			System.out.println("DataBase MetaData");
			DatabaseMetaData dmdt = con.getMetaData();
			System.out.println("-------Database Metadata---------");
			System.out.println(dmdt.getDatabaseProductName());
			System.out.println(dmdt.getDatabaseProductVersion());
			System.out.println(dmdt.getDriverName());
			System.out.println(dmdt.supportsStoredProcedures());
			
			PreparedStatement pstm = con.prepareStatement(query1);
			ParameterMetaData pmtdt = pstm.getParameterMetaData();
			System.out.println("\n--------Parameter MetaData---------");
			System.out.println("getParameterCount() : "+pmtdt.getParameterCount());
			System.out.println("getParameterType() : "+pmtdt.getParameterType(1));
			System.out.println("getParameterMode(int param) : "+pmtdt.getParameterMode(1));
			System.out.println("isNullable(int param) : "+pmtdt.isNullable(1));
			
			pstm.setString(1, "20");
			ResultSet rs = pstm.executeQuery();
			ResultSetMetaData rsmtdt = rs.getMetaData();
			System.out.println("\n---------ResultSet Metadata-----------");
			System.out.println("getColumnCount() : "+rsmtdt.getColumnCount());
			System.out.println("getColumnName(int column) : "+rsmtdt.getColumnName(1));
			System.out.println("getColumnDisplaySize(int column) : "+rsmtdt.getColumnDisplaySize(1));
			System.out.println("isAutoIncrement(int column) : "+rsmtdt.isAutoIncrement(2));
			
			RowSetFactory rsf = RowSetProvider.newFactory();
			CachedRowSet crs = rsf.createCachedRowSet();
			crs.setUrl(url);
			crs.setUsername(userName);
			crs.setPassword(pwd);
			crs.setCommand(query2);
			crs.execute();
			
			RowSetMetaData rowSetmtdt = (RowSetMetaData)crs.getMetaData();
			System.out.println("\n-------RowSet Metadata----------");
			System.out.println("getColumnCount() : "+rowSetmtdt.getColumnCount());
			System.out.println("getColumnName(int column) : "+rowSetmtdt.getColumnName(4));
			System.out.println("getColumnType(int column) : "+rowSetmtdt.getColumnType(2));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Jdbc_pro20().meth1();
	}
}
