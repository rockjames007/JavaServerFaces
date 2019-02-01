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
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Contact admin."));
			return null;
		}
	}
	
	public void apiCall() {
		String status = ls.start(lb);
		System.out.println(status);
		if(status!=null)
		{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Workflow Started with process instance id:"+status, ""));
		}
		else
		{
	     FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in starting workflow", ""));
		}
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
