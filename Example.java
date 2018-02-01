package com.ptc.biometric;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thingworx.communications.client.ClientConfigurator;
import com.thingworx.communications.client.ConnectedThingClient;
import com.thingworx.relationships.RelationshipTypes.ThingworxEntityTypes;
import com.thingworx.types.InfoTable;
import com.thingworx.types.collections.ValueCollection;

public class Example extends ConnectedThingClient {

	 private static final Logger LOG = LoggerFactory.getLogger(Example.class);
		
	 public Example(ClientConfigurator config) throws Exception {
	        super(config);
	 }
	 
	 private static String ThingName = "Testing";
	 
	public static void main (String args[])
	
	{
		 	ClientConfigurator config = new ClientConfigurator();

	        // Set the URI of the server that we are going to connect to.
	        // You must include the port number in the URI.
	        config.setUri("ws://localhost:8080/Thingworx/WS");

	        // Set the ApplicationKey. This will allow the client to authenticate with the server.
	        // It will also dictate what the client is authorized to do once connected.
	        config.setAppKey("18f8d06c-d87c-4fca-a0e9-ac85e10d642a");

	        // This will allow us to test against a server using a self-signed certificate.
	        // This should be removed for production systems.
	        config.ignoreSSLErrors(true); // All self signed certs
	        
	        try {

	            // Create our client.
	            Example client = new Example(config);

	            // Start the client. The client will connect to the server and authenticate
	            // using the ApplicationKey specified above.
	            client.start();
	            
	            InfoTable TestInfo = new InfoTable();
	            ValueCollection values = new ValueCollection();
	            
	            values.SetNumberValue("Id", 1);
	            values.SetStringValue("Name", "abc");
	            TestInfo.addRow(values);
	            
	            client.writePropertiesVTQ(ThingworxEntityTypes.Things, ThingName, TestInfo,
	            		5000 );

	            // Wait for the client to connect.
	            if (client.waitForConnection(30000)) {

	                LOG.info("The client is now connected.");
	            }
	}catch(Exception e)
	        {
				e.printStackTrace();
	        }

	}
}
