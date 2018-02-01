package com.ptc.biometric;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;
import org.json.JSONException;


public class JSONBuilder {
	
	public JSONObject finalJSON(Collection<JSONObject> items){
		
		JSONObject mainjson = new JSONObject();
		JSONObject value1 = new JSONObject();
		JSONObject value2 = new JSONObject();
		JSONObject value4 = new JSONObject();
		JSONObject value5 = new JSONObject();
		JSONObject value6 = new JSONObject();
		JSONObject value7 = new JSONObject();
		JSONObject value8 = new JSONObject();
		JSONObject dataShape = new JSONObject();
		JSONObject fieldDefinitions = new JSONObject();

		try {
			value8.put("aspects", value2);
			value8.put("description", "quality");
			value8.put("baseType", "STRING");
			value8.put("name","quality");
			value8.put("ordinal",0);	
			
			
			value7.put("aspects", value2);
			value7.put("description", "value");
			value7.put("baseType", "VARIANT");
			value7.put("name","value");
			value7.put("ordinal",0);

			

			value6.put("aspects", value2);
			value6.put("description", "time");
			value6.put("baseType", "DATETIME");
			value6.put("name","time");
			value6.put("ordinal",0);
			

			
			
			//value4.put("time", value6);
			value5.put("isPrimaryKey", true);
			value4.put("aspects", value5);
			value4.put("description", "Property name");
			value4.put("baseType", "STRING");
			value4.put("name","name");
			value4.put("ordinal",0);
			//value4.put("time", value6);

			
			fieldDefinitions.put("name", value4);
			fieldDefinitions.put("time", value6);
			fieldDefinitions.put("value", value7);
			fieldDefinitions.put("quality", value8);
			
			
			dataShape.put("name", "NamedVTQ");
			dataShape.put("description", "Property name, value, time, quality, state");
			dataShape.put("fieldDefinitions", fieldDefinitions);
			
			
			
			
			value1.put("created", Long.parseLong("1514902410419"));
			value1.put("description", "");
			value1.put("name", "Infotable");
			value1.put("dataShape", dataShape);
			value1.put("rows", items);
			mainjson.put("values", value1);
			System.out.println(mainjson.toString());
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return mainjson;

	}
		
	}
