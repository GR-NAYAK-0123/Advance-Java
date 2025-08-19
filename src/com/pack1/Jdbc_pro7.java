package com.pack1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Jdbc_pro7 {
	
	String driver = "oracle.jdbc.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String userName = "system";
	String pwd = "root";
	
	Scanner sc = new Scanner(System.in);
	
	Connection getConnect() {
		
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
	
	void operation() {
		
		try {
			Connection con = getConnect();
			
			PreparedStatement pstm1 = con.prepareStatement("insert into Patient values(?, ?, ?, ?)");
			PreparedStatement pstm2 = con.prepareStatement("select * from Patient");
			PreparedStatement pstm3 = con.prepareStatement("select * from Patient where pId = ?");
			PreparedStatement pstm4 = con.prepareStatement("update Patient set pAge = ? where pId = ?");
			PreparedStatement pstm5 = con.prepareStatement("delete from Patient where pId = ?");
			
			while(true) {
				
				
				System.out.println("------------Welcome to Patient Database-------------");
				System.out.println();
				System.out.println("|--------------------------------------------------|");
				System.out.println("|  Press 1 : Inserting the patient Data            |");
				System.out.println("|  Press 2 : View Patient data                     |");
				System.out.println("|  Press 3 : Retrieve patient data                 |");
				System.out.println("|  Press 4 : Update Patient Data                   |");
				System.out.println("|  Press 5 : Delete Patient data                   |");
				System.out.println("|  Press 6 : Exit                                  |");
				System.out.println("|--------------------------------------------------|");
				System.out.println();
				System.out.println("Enter your choice : ");
				int choice = sc.nextInt();
				
				switch(choice) {
				      
				        case 1 : 
				        	System.out.println("Inserting Patient Data");
				        	System.out.println("Enter patient Id : ");
				        	sc.nextLine();
				        	String pId1 = sc.nextLine();
				        	
				        	System.out.println("Enter Patient Name : ");
				        	String pName = sc.nextLine();
				        	
				        	System.out.println("Enter patient Age :");
				        	int pAge = Integer.parseInt(sc.nextLine());
				        	
				        	System.out.println("Enter patient contact Number : ");
				        	long pContact = Long.parseLong(sc.nextLine());
				        	
				        	pstm1.setString(1, pId1);
				        	pstm1.setString(2, pName);
				        	pstm1.setInt(3, pAge);
				        	pstm1.setLong(4, pContact);
				        	
				        	int rowCount = pstm1.executeUpdate();
				        	
				        	if(rowCount > 0)
				        		System.out.println("Data Inserted");
				        	else
				        		System.out.println("Data NOT Inserted");
				        	break;
				       case 2 :
				    	   System.out.println("Viewing the Patient Table : ");
				    	   
//				    	   System.out.println("Please enter the table Name : ");
//				    	   String table = sc.next();
//				    	   
//				    	   pstm2.setString(1, table);   // We can't give the table name as a parameter
				    	   
				    	   ResultSet rs1 = pstm2.executeQuery();
				    	   
				    	   if(!rs1.next()) {
				    		   System.out.println("There is NO data is present");
				    	   } 
				    	   else {
				    		   do {
				    			   for(int i = 1;i<=4;i++) {
				    				   if(i == 3)
				    					   System.out.print(rs1.getInt(i)+" ");
				    				   else if(i == 4)
				    					   System.out.print(rs1.getLong(i)+" ");
				    				   else
				    					   System.out.print(rs1.getString(i)+" ");
				    			   }
				    			   System.out.println();
				    		   }while(rs1.next());
				    	   }
				    	   
				    	   break;
				       case 3 :
				    	   System.out.println("------Retrieving the specific patient data---------");
				    	   System.out.println("Enter the patient Id : ");
				    	   String pId2 = sc.next();
				    	   
				    	   pstm3.setString(1, pId2);
				    	   
				    	   ResultSet rs2 = pstm3.executeQuery();
				    	   
				    	   if(rs2.next())
				    		   System.out.println(rs2.getString(1)+" "+rs2.getString(2)+" "+rs2.getInt(3)+" "+rs2.getLong(4));
				    	   else
				    		   System.out.println("There is NO record found for this patient Id : "+pId2);
				    	   
				    	   break;
				       case 4 :
				    	   System.out.println("------Updating the specific patient data--------");
				    	   System.out.println("Enter the patient Id : ");
				    	   String pId3 = sc.next();
				    	   System.out.println("Enter the patient age : ");
				    	   int pAge2 = sc.nextInt();
				    	   
				    	   pstm4.setInt(1, pAge2);
				    	   pstm4.setString(2, pId3);
				    	   
				    	   int rowCount2 = pstm4.executeUpdate();
				    	   
				    	   if(rowCount2 > 0) {
				    		   System.out.println("The specific data is updated");
				    	   }
				    	   else {
				    		   System.out.println("There is NO such record is found for this PId : "+pId3);
				    	   }
				    	   
				    	   break;
				       case 5 :
				    	   System.out.println("------Deleting the specific data from Patient------");
				    	   System.out.println("Enter the patient Id : ");
				    	   String pId4 = sc.next();
				    	   
				    	   pstm5.setString(1, pId4);
				    	   
				    	   int rowCount3 = pstm5.executeUpdate();
				    	   
				    	   if(rowCount3 > 0)
				    		   System.out.println("The specific data has deleted from the patient");
				    	   else
				    		   System.out.println("There is NO record found for this patient Id : "+pId4);
				    	   break;
				       case 6 :
				    	   System.out.println("Thank you see you soon :) ");
				    	   System.exit(0);
				    	   break;
				       default : 
				    	   System.out.println("Invalid Input !!!");  			
				}
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Jdbc_pro7 obj = new Jdbc_pro7();
		obj.operation(); 
	}
}
