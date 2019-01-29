package com.xmplar.apicall;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.util.Base64;
 
public class RestAPIUtil {
 
    public String startProcess(String StartedBy){
         
        String url = "http://localhost:8080/kie-server/services/rest/server/containers/DCF_1.0.5/processes/DCF.fairHearingWorkflow/instances";
        String name = "wbadmin";
        String password = "wbadmin";
        String authString = name + ":" + password;
        String authStringEnc=Base64.getEncoder().encodeToString(authString.getBytes());
        System.out.println("Base64 encoded auth string:"+authStringEnc);
        Client restClient = Client.create();
        String input="{\"startedBy\":\""+StartedBy+"\"}";
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.type("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .post(ClientResponse.class, input);
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);
        System.out.println("response: "+output);
        return output;
    }
}