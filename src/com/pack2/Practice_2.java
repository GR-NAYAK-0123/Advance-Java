package com.pack2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;

public class Practice_2 {
	
	void getConnection() {
		
		String drive = "oracle.jdbc.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String uName = "system";
		String pwd = "root";
		String query = "select * from Employee";
		
		System.out.println("Connecting our java code to the database");
		HashMap<String, Integer> map =new HashMap<String, Integer>();
		
		try {
			Class.forName(drive);
			Connection con = DriverManager.getConnection(url, uName, pwd);
			Statement sm = con.createStatement();
			ResultSet rs = sm.executeQuery(query);
			
			while(rs.next()) {
				map.put(rs.getString(1), rs.getInt(4));
			}
			
			HashSet<Object> hs = new HashSet<Object>(map.entrySet());
			Iterator<Object> i = hs.iterator();
			
			while(i.hasNext()) {
				Entry e = (Entry) i.next();
				System.out.println(e.getKey()+" - "+e.getValue());
			}
			
//			for(String x : map.keySet()) {
//				System.out.println(x+" "+map.get(x));
//			}
			//System.out.println(map);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Practice_2().getConnection();
	}
}
