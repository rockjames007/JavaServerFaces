package com.xmplar.apicall;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RestTest {
	static String name = "wbadmin";
	static String password = "wbadmin";
	static String authString = name + ":" + password;
	static String authStringEnc = Base64.getEncoder().encodeToString(authString.getBytes());

	public static void main(String args[]) {
		String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.5/tasks/37/states/completed?auto-progress=true";
		System.out.println("Base64 encoded auth string:" + authStringEnc);
		Client restClient = Client.create();
		String input = "{\"age\":25,\"person\":{\"Person\":{\"name\":\"wbadmin\"}}}";
		WebResource webResource = restClient.resource(url);
		ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc).put(ClientResponse.class, input);
		if (resp.getStatus() != 200) {
			System.err.println("Unable to connect to the server");
		}
		String output = resp.getEntity(String.class);
		System.out.println("response: " + output);
	}
		
}
