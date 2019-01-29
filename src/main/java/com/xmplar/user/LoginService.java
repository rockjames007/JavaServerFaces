package com.xmplar.user;
import com.xmplar.apicall.RestBean;

public class LoginService {
	
	public  String loginService(String user,String password) {
		DOA dao=new DOA();
		return dao.sqlConnection(user,password);
	}
	
	public String start(Login loginObject) {
		
		RestBean obj = new RestBean();
		String status = obj.start(loginObject);
		return status;
		
	}

}
