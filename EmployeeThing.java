package com.ptc.biometric;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.json.JSONObject;

public class EmployeeThing {

	private String thingTemplate = "EmployeeTemplate";
	private String serverURL="http://localhost:8080/Thingworx/Resources/EntityServices/Services/";
	private String appKey="3f489976-1606-47df-8b66-5e2bd98a5a75";
	
	
	
	
	public int createEmployee(int empId){
		
		try {
			String employee = "Employee" + Integer.toString(empId);
			URL url = new URL(serverURL +"CreateThing");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setRequestProperty ("Content-Type", "application/json");
		    httpURLConnection.setRequestProperty ("appKey",appKey);
		    
		    JSONObject params = new JSONObject();
		    params.put("name", employee);
		    params.put("description", "employee thing for" + " " + Integer.toString(empId));
		    params.put("thingTemplateName", thingTemplate);
		    
		    String payload = params.toString();
		    System.out.println(payload);
		    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		    out.write(payload);
		    out.close();
		    httpURLConnection.getInputStream();
		    
		    return httpURLConnection.getResponseCode();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public int enableEmployee(int empId){
		
		try {
			String employee = "Employee" + Integer.toString(empId);
			URL url = new URL("http://localhost:8080/Thingworx/Things/" +employee +"/Services/EnableThing");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setRequestProperty ("Content-Type", "application/json");
		    httpURLConnection.setRequestProperty ("appKey",appKey);
		    

		    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		    out.close();
		    httpURLConnection.getInputStream();
		    
		    return httpURLConnection.getResponseCode();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
		public int restartEmployee(int empId){
		
		try {
			String employee = "Employee" + Integer.toString(empId);
			URL url = new URL("http://localhost:8080/Thingworx/Things/" +employee +"/Services/RestartThing");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setRequestProperty ("Content-Type", "application/json");
		    httpURLConnection.setRequestProperty ("appKey",appKey);
		    

		    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		    out.close();
		    httpURLConnection.getInputStream();
		    
		    return httpURLConnection.getResponseCode();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public int updateProperties (int empId) {
		
		try {
			String employee = "Employee" + Integer.toString(empId);
			URL url = new URL("http://localhost:8080/Thingworx/Things/" + employee +"/Services/UpdatePropertyValues");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("POST");
		    httpURLConnection.setRequestProperty ("Content-Type", "application/json");
		    httpURLConnection.setRequestProperty ("appKey",appKey);    
		    
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date currentDate = new Date();
		    JSONBuilder jbuilder = new JSONBuilder();
		    Collection<JSONObject> items = new ArrayList<JSONObject>();
		    JSONObject item1 = new JSONObject();
		    item1.put("name", "EmpId");
		    item1.put("value", empId);
		    item1.put("quality", "GOOD");
		    items.add(item1);
		    JSONObject item2 = new JSONObject();
		    item2.put("name", "EmpName");
		    item2.put("value", "TEST");
		    item2.put("quality", "GOOD");
		    items.add(item2);
		    JSONObject params = new JSONObject();
		   /* JSONObject item3 = new JSONObject();
		    item3.put("name", "InTime");
		    item3.put("value", df.format(currentDate));
		    item3.put("quality", "GOOD");
		    items.add(item3);*/
		    JSONObject item4 = new JSONObject();
		    item4.put("name", "Role");
		    item4.put("value", "TSE");
		    item4.put("quality", "GOOD");
		    items.add(item4);
		    params = jbuilder.finalJSON(items);
		 
		    System.out.println(params);
		    String payload = params.toString();
		    System.out.println(payload);
		    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		    out.write(payload);
		    System.out.println(out);
		    out.flush();
		    out.close();
		   System.out.println(httpURLConnection.getInputStream());
		    
		    BufferedReader in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
		    String inputLine=null;
		    StringBuffer response = new StringBuffer();
		    
		    while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
		    
		    return httpURLConnection.getResponseCode();
		    		    
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	public int updateEmployee(int empId){
		
		try {
			String employee = "Employee" + Integer.toString(empId);
			URL url = new URL("http://localhost:8080/Thingworx/Things/" + employee +"/Properties/InTime");
			HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
			httpURLConnection.setUseCaches(false);
			httpURLConnection.setDoOutput(true);
		    httpURLConnection.setRequestMethod("PUT");
		    httpURLConnection.setRequestProperty ("Content-Type", "application/json");
		    httpURLConnection.setRequestProperty ("appKey",appKey);
		    
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		    Date cuurentDate = new Date();
		    
		    JSONObject params = new JSONObject();
		    params.put("InTime", df.format(cuurentDate));
		    String payload = params.toString();
		    OutputStreamWriter out = new OutputStreamWriter(httpURLConnection.getOutputStream());
		    out.write(payload);
		    out.close();
		    httpURLConnection.getInputStream();
		    
		    return httpURLConnection.getResponseCode();
		    
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
		
	}
}
