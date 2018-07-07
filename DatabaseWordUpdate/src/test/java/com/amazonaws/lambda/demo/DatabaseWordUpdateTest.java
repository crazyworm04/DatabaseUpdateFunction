package com.amazonaws.lambda.demo;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DatabaseWordUpdateTest {

    private static JSONObject input;
    

    @BeforeClass
    public static void createInput() throws IOException {
    	JSONObject wordsToBeAdded = new JSONObject();
    	wordsToBeAdded.put("Project_ID", "03");
		wordsToBeAdded.put("Artwork_ID", "003");
	    wordsToBeAdded.put("label", "Lion");
		wordsToBeAdded.put("size", 1);
		wordsToBeAdded.put("label", "Dog");
		wordsToBeAdded.put("size", 2);
        input = wordsToBeAdded;
    }

    private Context createContext() {
        TestContext ctx = new TestContext();

         //TODO: customize your context here if needed.
        ctx.setFunctionName("Your Function Name");

        return ctx;
    }

    @Test
    public void testDatabaseOutputFunction() {
       DatabaseWordUpdate handler = new DatabaseWordUpdate();
       Context ctx = createContext();

       JSONObject output = handler.handleRequest(input, ctx);
       
        Assert.assertEquals("Hello from Lambda!", input);
    }
}
