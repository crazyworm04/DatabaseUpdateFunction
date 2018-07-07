package com.amazonaws.lambda.demo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

//import org.json.JSONObject;
//import org.json.JSONStringer;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatabaseWordUpdate implements RequestHandler<JSONObject, JSONObject> {

	private final String url = "";
	private final String user =  "";
	private final String password = "";
	
	private Connection connection = null;
	
	JSONObject dataOutput = new JSONObject();
	JSONObject headersObject = new JSONObject();
	JSONObject bodyText = new JSONObject();
	
	String artworkID = "00";
			
	//Method for establishing connection to the PostgreSQL server
	public Connection connect() {
		if(connection == null) {
	        try {
	            connection = DriverManager.getConnection(url, user, password);
	            System.out.println("Connected to the PostgreSQL server successfully." + "\n");
	        } 
	        catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		}		
		return connection;
    }
	
	
	//Main handler method (the main function code) for getting the desired data from the database before returning a JSON object with the data within it
	@Override
    public JSONObject handleRequest(JSONObject input, Context context) {
		connect();
		
		String qsp = input.get("queryStringParameters").toString();
        artworkID = qsp.substring(qsp.indexOf("=")+1, qsp.length()-1);
		
		context.getLogger().log(url);
        context.getLogger().log(user);
        context.getLogger().log(password);
               
       try {    	    
    	    connection.createStatement().executeUpdate("UPDATE \"speechData\" SET \"artwork_data\" = '" + input + "' WHERE \"artwork_data\"->>'Artwork_ID' LIKE '%" + artworkID + "%'");
    	    
    	    headersObject.put("Access-Control-Allow-Origin", "*");
			bodyText.put("bodyText", "The function was successful.");
									
			dataOutput.put("isBase64Encoded", false);
			dataOutput.put("statusCode", 200);
            dataOutput.put("headers", headersObject);
            dataOutput.put("body", bodyText.toJSONString());
		}
        catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.print(dataOutput.toJSONString());
        return dataOutput;
    }
}