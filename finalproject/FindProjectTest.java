package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildesperandumcs3733.finalproject.http.ViewProjectResponse;

/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class FindProjectTest extends LambdaTest {

    void testInput(CreateProjectRequest req) throws IOException {
    	ViewProjectHandler handler = new ViewProjectHandler();
    	ViewProjectResponse response = handler.handleRequest(req, createContext("compute"));

        Assert.assertEquals(200, response.statusCode);
    }
	
   
 // presumes the existence of a e constant -- which was created during tutorial
    @Test
    public void testValid() {
    	CreateProjectRequest req = new CreateProjectRequest("roopsa");
    	try {
			testInput(req);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
