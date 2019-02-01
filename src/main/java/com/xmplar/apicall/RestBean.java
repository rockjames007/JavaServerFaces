package com.xmplar.apicall;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import com.xmplar.user.Login;

public class RestBean {
	RestAPIUtil rs=new RestAPIUtil();
	private List<ActiveProcess> activeProcessList = new ArrayList<ActiveProcess>();
	private int selectedTaskId;
	private String userName;
	

	public void display() {
		RestAPIUtil util = new RestAPIUtil();
		activeProcessList = util.activeProcess();
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public RestAPIUtil getRs() {
		return rs;
	}

	public void setRs(RestAPIUtil rs) {
		this.rs = rs;
	}
	
	public String start(Login loginObject)
	{
		
		return rs.startProcess(loginObject.getUserName());
	}
    
	public void requestfairhearing()
	{
		System.err.println(selectedTaskId);
		String status=rs.requestfairHearing(selectedTaskId,userName);
		
		if(status!=null)
		{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Fairhearing is requested:"+status, ""));
		}
		else
		{
	     FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in requesting fairhearing", ""));
		}

	}
	public List<ActiveProcess> getActiveProcessList()
	{
		return activeProcessList;
	}

	public void setActiveProcessList(List<ActiveProcess> activeProcessList) {
		this.activeProcessList = activeProcessList;
	}

	public int getSelectedTaskId() {
		return selectedTaskId;
	}

	public void setSelectedTaskId(int selectedTaskId) {
		this.selectedTaskId = selectedTaskId;
	}
	
	
}
