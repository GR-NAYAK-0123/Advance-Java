package com.pack1;

import java.sql.ResultSet;

public class Jdbc_pro8 {
	
	void meth1() {
		System.out.println("FORWARD_ONLY : "+ResultSet.TYPE_FORWARD_ONLY);
		System.out.println("SCROLL_INSENSITIVE : "+ResultSet.TYPE_SCROLL_INSENSITIVE);
		System.out.println("SCROLL_SENSITIVE : "+ResultSet.TYPE_SCROLL_SENSITIVE);
		
		System.out.println("READ_ONLY : "+ResultSet.CONCUR_READ_ONLY);
		System.out.println("UPDATABLE : "+ResultSet.CONCUR_UPDATABLE);
	}
	public static void main(String[] args) {
		new Jdbc_pro8().meth1();
	}
}
