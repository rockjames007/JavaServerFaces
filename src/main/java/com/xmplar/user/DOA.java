package com.xmplar.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DOA {
	
	
	public  Boolean sqlConnection(String username,String password) {
		Connection con=null;
		PreparedStatement pstmt=null;
		String uname=username,pas=password;
		ResultSet rs;
		try {
			Class.forName("org.postgresql.Driver");			
		}catch(ClassNotFoundException e) {
			System.out.println("Error Class not found");
		} 
		try {
			con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "postgres");
		}catch (SQLException e) {
			System.out.println("Error Connection");
		}
		if(con!=null) {
			System.out.println("Connected");
			try {
				pstmt=con.prepareStatement("SELECT name, password FROM users WHERE name=? AND  password=?");
				pstmt.setString(1, uname);
				pstmt.setString(2, pas);
				rs=pstmt.executeQuery(); 
				if(!rs.next())
				return false;
				else
					return true;
			}catch(Exception e) {
				System.out.println("Error in Query");
			}
		}
		else {
			System.out.println("Not Connected");
		}
		
		return false;
	}
}
