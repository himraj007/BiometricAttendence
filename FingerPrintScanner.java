package com.ptc.biometric;

import java.util.Iterator;
import java.util.Scanner;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.relationships.RelationshipTypes.ThingworxEntityTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;
import com.thingworx.types.collections.ValueCollectionList;

public class FingerPrintScanner extends ConnectedThingClient{

	public FingerPrintScanner(ClientConfigurator config) throws Exception {
		super(config);
		
	}
	
	private static String thingName = "UserInfoDataTable";
	

	
	public static void main (String args[]) {
		
		//Scanner scn = new Scanner(System.in);
		//System.out.println("Enter the EmpID");
		//String empId = scn.next();
		
		EmployeeThing employeeThing = new EmployeeThing();
		
		String empId="9";
		int count=0;
		int response=0;
		String values;
		ClientConfigurator config = new ClientConfigurator();
		config.setUri("ws://localhost:8080/Thingworx/WS");
		config.setAppKey("3f489976-1606-47df-8b66-5e2bd98a5a75");
		config.ignoreSSLErrors();
		
		ValueCollectionList param = new ValueCollectionList();
		ValueCollection params = new ValueCollection();
		try{
		FingerPrintScanner client = new FingerPrintScanner(config);
		client.start();
		
		if (client.waitForConnection(30000)) {
			System.out.println("The client is connected");
			InfoTable result;
			System.out.println("Invoking service");
			result= client.invokeService(ThingworxEntityTypes.Things, thingName, "GetDataTableEntries",params,5000);
			//rows saved in collection list
			param = result.getRows();
			System.out.println(param.toString());
			
			//iterating collection list
			Iterator itr = param.iterator();
			while(itr.hasNext())
			{
				values=itr.next().toString();
				values=values.substring(values.indexOf("key"));
				values=values.substring(0,values.indexOf(',')).substring(4);
				System.out.println(values);
				if(values.equalsIgnoreCase(empId + ".0")) {
					count++;
					//update the employee thing with properties
					response=employeeThing.updateEmployee(Integer.parseInt(empId));
					System.out.println(response);
				}
				
			}
			
			if(count==0)
			{
				//create employee using restAPI
				response=employeeThing.createEmployee(Integer.parseInt(empId));
				System.out.println(response);
				response=employeeThing.enableEmployee(Integer.parseInt(empId));
				System.out.println(response);
				response=employeeThing.restartEmployee(Integer.parseInt(empId));
				System.out.println(response);
				response=employeeThing.updateProperties(Integer.parseInt(empId));
				System.out.println(response);
			}
			
			
			
		}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
