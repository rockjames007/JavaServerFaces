package com.xmplar.user;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Login {

	LoginService ls = new LoginService();
	LoginBean lb = new LoginBean();

	public LoginService getLs() {
		return ls;
	}

	public void setLs(LoginService ls) {
		this.ls = ls;
	}

	public LoginBean getLb() {
		return lb;
	}

	public void setLb(LoginBean lb) {
		this.lb = lb;
	}

	public String addUser() {
		if (ls.loginService(lb.userName, lb.Password)==true)
		{
			System.out.println("Login Sucessful");
	        return "success";
		}    
		else
		{
			System.out.println("failure");
			return "failure";
		}
	}
	public void error() {
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in login", ""));
    }

}
