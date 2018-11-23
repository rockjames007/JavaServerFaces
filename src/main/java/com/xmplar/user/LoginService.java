package com.xmplar.user;

public class LoginService {
	
	public  int loginService(String user,String password) {
		DOA dao=new DOA();
		return dao.sqlConnection(user,password);
	}
	

}
