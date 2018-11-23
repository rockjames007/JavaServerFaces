package com.xmplar.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DOA {
	
	
	public  int sqlConnection(String username,String password) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String uname=username,pas=password;
		int result;
		try {
			Class.forName("org.postgresql.Driver");			
		}catch(ClassNotFoundException e) {
			System.out.println("Error Class not found");
		} 
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/deepu", "postgres", "postgres");
		}catch (SQLException e) {
			System.out.println("Error Connection");
		}
		if(con!=null) {
			System.out.println("Connected");
			try {
				pstmt=con.prepareStatement("INSERT INTO person VALUES(?,?)");
				pstmt.setString(1, uname);
				pstmt.setString(2, pas);
				result=pstmt.executeUpdate(); 
				return result;
			}catch(Exception e) {
				System.out.println("Error in Insert");
			}
		}
		else {
			System.out.println("Not Connected");
		}
		
		return 0;
	}
}
