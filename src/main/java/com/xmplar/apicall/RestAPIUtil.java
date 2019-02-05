package com.xmplar.apicall;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

public class RestAPIUtil {
	String name = "wbadmin";
	String password = "wbadmin";
	String authString = name + ":" + password;
	String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());

	/* Starts the fairhearing workflow and return process instance id */
	public String startProcess(String StartedBy) {

		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/processes/DCF.fairHearingWorkflow/instances";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"startedBy\":\"" + StartedBy + "\"}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc)
				.post(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;
	}
    
	/* Request fairhearing by given user using given task id */
	public String requestfairHearing(int tid, String CompletedBy) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/tasks/"+tid+"/states/completed?auto-progress=true";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"requestfh\":\""+CompletedBy+"\"}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;

	}
	
	/* Review fairhearing by Supervisor using given task id */
	public String reviewfairHearingSupervisor(int tid,int pid,String value,String CompletedBy) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/tasks/"+tid+"/states/completed?auto-progress=true";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"reviewsp\":\""+CompletedBy+"\"}";
		String res=setProcessVariable(pid,"approvesup",value);
		System.out.println(res);
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;

	}
	
	/* Review fairhearing by Supervisor using given task id */
	public String reviewfairHearingCommisioner(int tid,String CompletedBy) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/tasks/"+tid+"/states/completed?auto-progress=true";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"reviewcm\":\""+CompletedBy+"\"}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;

	}
	
	/* Review fairhearing by Supervisor using given task id */
	public String rerequestFairhearing(int tid,String CompletedBy) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/tasks/"+tid+"/states/completed?auto-progress=true";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"requestfh\":\""+CompletedBy+"\"}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;

	}
	/*Set fairhearing by Supervisor using given task id */
	public String setProcessVariable(int pid,String pr, String value) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/processes/instances/"+pid+"/variables";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\""+pr+"\":\""+value+"\"}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).post(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
		return output;

	}

    
	/* Retrives the list of process instances id and associated task summary */
	public List<ActiveProcess> activeProcess() {
		List<ActiveProcess> list = new ArrayList<ActiveProcess>();
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/processes/instances?page="
				+ 0 + "&pageSize=" + 50 + "&sortOrder=true";
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		} else {
			JSONObject jsonObj = new JSONObject(resp.getEntity(String.class));
			DateFormat formatter = new SimpleDateFormat("E dd/MM/yyyy hh:mm:ss");
			JSONArray result = jsonObj.getJSONArray("process-instance");
			for (int n = 0; n < result.length(); n++) {
				ActiveProcess actPrs = new ActiveProcess();
				JSONObject item = result.getJSONObject(n);
				long date = item.getJSONObject("start-date").getLong("java.util.Date");
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(date);
				actPrs.setProcessid(item.getInt("process-instance-id"));
				actPrs.setProccessState(item.getInt("process-instance-state"));
				ArrayList<String> arr = workItem(item.getInt("process-instance-id"));
				actPrs.setTaskName(arr.get(0));
				actPrs.setNodeName(arr.get(1));
				actPrs.setGroupId(arr.get(2));
				ArrayList<String> arr1 = taskSummary(item.getInt("process-instance-id"));
				actPrs.setTaskid(Integer.parseInt(arr1.get(0)));
				actPrs.setTaskStatus(arr1.get(1));
				actPrs.setRequestfairhearing(requestfh(arr1.get(1), arr.get(0)));
				actPrs.setRerequestfairhearing(rerequestfh(arr1.get(1), arr.get(0)));
				actPrs.setRequestSupervisor(requestsp(arr1.get(1), arr.get(0)));
				actPrs.setRequestCommissioner(requestcm(arr1.get(1), arr.get(0)));
				actPrs.setStartDate(formatter.format(calendar.getTime()));
				list.add(actPrs);

			}
		}
		return list;
	}

	/* Returns list of work item instances of given process instance id */
	public ArrayList<String> workItem(int pid) {
		ArrayList<String> list = new ArrayList<String>();
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.7/processes/instances/"
				+ pid + "/workitems";
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		} else {
			JSONObject jsonObj = new JSONObject(resp.getEntity(String.class));
			JSONArray result = jsonObj.getJSONArray("work-item-instance");
			JSONObject item = result.getJSONObject(0);
			list.add(item.getJSONObject("work-item-params").getString("TaskName"));
			list.add(item.getJSONObject("work-item-params").getString("NodeName"));
			list.add(item.getJSONObject("work-item-params").getString("GroupId"));
		}
		return list;
	}

	/*
	 * Returns list of task summary
	 */
	public ArrayList<String> taskSummary(int pid) {
		ArrayList<String> list = new ArrayList<String>();
		String url = "http://localhost:8080/kie-server/services/rest/server/queries/tasks/instances/process/" + pid;
		Client restClient = Client.create();
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.accept("application/json").header("Authorization", "Basic " + authStringEnc)
				.get(ClientResponse.class);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		System.out.println(pid);
		JSONObject jsonObj = new JSONObject(resp.getEntity(String.class));
		JSONArray result = jsonObj.getJSONArray("task-summary");
		JSONObject item = result.getJSONObject(0);
		System.out.println(item.getInt("task-id"));
		list.add(Integer.toString(item.getInt("task-id")));

		list.add(item.getString("task-status"));

		return list;

	}

	/*
	 * Returns boolean value to check if task status is active for requesting
	 * fairhearing and is ready to be claimed and start
	 */
	public boolean requestfh(String ts, String tn) {
		if (ts.equals("Ready")) {
			if (tn.equals("requestFairHearing"))
				return true;
			else
				return false;
		} else
			return false;

	}

	/*
	 * Returns boolean value to check if task status is active for re-requesting
	 * fairhearing
	 */
	public boolean rerequestfh(String ts, String tn) {
		if (ts.equals("Ready")) {
			if (tn.equals("reRequestFairHearing"))
				return true;
			else
				return false;
		} else
			return false;

	}
	
	/*
	 * Returns boolean value to check if task status is active for requesting
	 * fairhearing by supervisor
	 */
	public boolean requestsp(String ts, String tn) {
		if (ts.equals("Ready")) {
			if (tn.equals("requestFairHearingBySupervisor"))
				return true;
			else
				return false;
		} else
			return false;

	}
	
	/*
	 * Returns boolean value to check if task status is active for requesting
	 * fairhearing by supervisor
	 */
	public boolean requestcm(String ts, String tn) {
		if (ts.equals("Ready")) {
			if (tn.equals("requestFairHearingByCommissioner"))
				return true;
			else
				return false;
		} else
			return false;

	}
}