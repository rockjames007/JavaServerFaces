package com.xmplar.user;

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
		if (ls.loginService(lb.userName, lb.Password)==1)
		{
			System.out.println("Successfully Inserted");
	        return "successs";
		}    
		else
		{
			System.out.println("failure");
			return "failure";
		}
	}

}
