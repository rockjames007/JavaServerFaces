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
	private int selectedProcessId;
	private String supDecision;
	private String userName;
	
	public String getSupDecision() {
		return supDecision;
	}

	public void setSupDecision(String supDecision) {
		this.supDecision = supDecision;
	}

	public int getSelectedProcessId() {
		return selectedProcessId;
	}

	public void setSelectedProcessId(int selectedProcessId) {
		this.selectedProcessId = selectedProcessId;
	}

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
		display();

	}
	
	public void requestfairHearingSupervisor()
	{
        String status=rs.reviewfairHearingSupervisor(selectedTaskId,selectedProcessId,supDecision,userName);
		
		if(status!=null)
		{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Fairhearing is requested by Supervisor:"+status, ""));
		}
		else
		{
	     FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in requesting fairhearing", ""));
		}  
		display();
	}
	
	public void requestfairHearingCommisioner()
	{
        String status=rs.reviewfairHearingCommisioner(selectedTaskId,userName);
		
		if(status!=null)
		{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Fairhearing is approved by Commissioner:"+status, ""));
		}
		else
		{
	     FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in approving fairhearing", ""));
		}  
		display();
	}
	
	public void rerequestfairHearing()
	{
        String status=rs.rerequestFairhearing(selectedTaskId,userName);
		
		if(status!=null)
		{
		FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Fairhearing is Re-requested:"+status, ""));
		}
		else
		{
	     FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error in re-requesting fairhearing", ""));
		}  
		display();
	}
    public String logout()
    {
    	return "logout";
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
