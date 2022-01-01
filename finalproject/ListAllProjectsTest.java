package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildesperandumcs3733.finalproject.http.ViewProjectResponse;
import com.nildesperandumcs3733.finalproject.http.AllListsRequest;
import com.nildesperandumcs3733.finalproject.http.AllProjectsResponse;
import com.nildesperandumcs3733.finalproject.http.AllTeammatesResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class ListAllProjectsTest extends LambdaTest{

	void testInput(AllListsRequest req, String out) throws IOException
	{
		ListAllProjectsHandler handler = new ListAllProjectsHandler();
		AllProjectsResponse response = handler.handleRequest(req, createContext("compute"));
		
		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}
	void testFailInput(AllListsRequest req, String out) throws IOException
	{
		ListAllProjectsHandler handler = new ListAllProjectsHandler();
		AllProjectsResponse response = handler.handleRequest(req, createContext("compute"));
		
		Assert.assertEquals(400, response.statusCode);
	}
	
	@Test
	public void testListAllProject()
	{
		try
		{
			String pName = "Project T";
			AllListsRequest req = new AllListsRequest();
			req.setProjectName(pName);
			req.getProjectName();
			testInput(req,"projectName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListAllProjectAgain()
	{
		try
		{
			String pName = "Project T";
			AllListsRequest req = new AllListsRequest(pName);
			testFailInput(req,"projectName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
