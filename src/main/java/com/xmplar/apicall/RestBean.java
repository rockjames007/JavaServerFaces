package com.xmplar.apicall;

import com.xmplar.user.Login;
import com.xmplar.user.LoginBean;

public class RestBean {
	RestAPIUtil rs=new RestAPIUtil();
	
	
	public RestAPIUtil getRs() {
		return rs;
	}

	public void setRs(RestAPIUtil rs) {
		this.rs = rs;
	}
	
	public String start(Login loginObject)
	{
		rs.startProcess(loginObject.getUserName());
		return "Success";
	}
	
}
