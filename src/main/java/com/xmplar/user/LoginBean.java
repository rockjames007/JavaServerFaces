package com.xmplar.user;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class LoginBean {
	LoginService ls = new LoginService();
	Login lb = new Login();
	String role;

	public String userLogin() {
		role=ls.loginService(lb.userName, lb.Password);
		if (role!="false")
		{
			System.out.println("Login Sucessful");
			lb.roles =role;
	        return "success";
		}    
		else
		{
			System.out.println("failure");
			return "failure";
		}
	}
	
	public void apiCall() {
		String status = ls.start(lb);
		System.out.println(status);
		
	}
	
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in login", ""));
    }
	

	public LoginService getLs() {
		return ls;
	}

	public void setLs(LoginService ls) {
		this.ls = ls;
	}

	public Login getLb() {
		return lb;
	}

	public void setLb(Login lb) {
		this.lb = lb;
	}

}
