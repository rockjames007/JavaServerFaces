package com.xmplar.apicall;

import java.io.Serializable;

public class ActiveProcess implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	public int processid;
	public String startDate;
	public int proccessState;
    public String taskName;
    public String nodeName;
    public String groupId;
    public int taskid;
    public Boolean requestfairhearing;
    public Boolean rerequestfairhearing;
    public Boolean requestSupervisor;
    public Boolean requestCommissioner;
    
    public String taskStatus;
    public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public Boolean getRequestfairhearing() {
		return requestfairhearing;
	}

	public Boolean getRerequestfairhearing() {
		return rerequestfairhearing;
	}

	public void setRerequestfairhearing(Boolean rerequestfairhearing) {
		this.rerequestfairhearing = rerequestfairhearing;
	}

	public Boolean getRequestSupervisor() {
		return requestSupervisor;
	}

	public void setRequestSupervisor(Boolean requestSupervisor) {
		this.requestSupervisor = requestSupervisor;
	}

	public Boolean getRequestCommissioner() {
		return requestCommissioner;
	}

	public void setRequestCommissioner(Boolean requestCommissioner) {
		this.requestCommissioner = requestCommissioner;
	}

	public void setRequestfairhearing(Boolean requestfairhearing) {
		this.requestfairhearing = requestfairhearing;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public int getProcessid() {
		return processid;
	}

	public void setProcessid(int processid) {
		this.processid = processid;
	}

	public String getStartDate() {
		return this.startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getProccessState() {
		return proccessState;
	}

	public void setProccessState(int proccessState) {
		this.proccessState = proccessState;
	}

}
