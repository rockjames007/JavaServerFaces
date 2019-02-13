package com.xmplar.user;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.xmplar.apicall.RestBean;

public class LoginService {
	
	public  String loginService(String user,String password) {
		String role;
		DOA dao=new DOA();
		role=dao.sqlConnection(user,password);
		HttpSession session;
		FacesContext context = FacesContext.getCurrentInstance();
	    session = (HttpSession) context.getExternalContext().getSession(true);
	    session.setAttribute("role",role);
		return role;
	}
	
	public String start(Login loginObject) {
		
		RestBean obj = new RestBean();
		String status = obj.start(loginObject);
		return status;
		
	}

}
