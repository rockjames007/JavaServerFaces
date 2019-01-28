package com.xmplar.user;

public class LoginService {
	
	public  Boolean loginService(String user,String password) {
		DOA dao=new DOA();
		return dao.sqlConnection(user,password);
	}
	

}
