package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.nildesperandumcs3733.finalproject.http.AllListsRequest;
import com.nildesperandumcs3733.finalproject.http.AllProjectsResponse;
import com.nildesperandumcs3733.finalproject.http.AllTasksResponse;
import com.nildesperandumcs3733.finalproject.http.AllTeammatesResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class ListAllTeammatesTest extends LambdaTest{

	void testInput(AllListsRequest req, String out) throws IOException
	{
		ListAllTeammatesHandler handler = new ListAllTeammatesHandler();
		AllTeammatesResponse response = handler.handleRequest(req, createContext("compute"));
		
		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}
	void testFailInput(AllListsRequest req, String out) throws IOException
	{
		ListAllTeammatesHandler handler = new ListAllTeammatesHandler();
		AllTeammatesResponse response = handler.handleRequest(req, createContext("compute"));
		
		Assert.assertEquals(400, response.statusCode);
	}
	
	@Test
	public void testListAllTeammates()
	{
		try
		{
			String tName = "Teammate T";
			AllListsRequest req = new AllListsRequest();
			req.setProjectName(tName);
			req.getProjectName();
			testInput(req,"teammateName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testListAllTeammatesAgain()
	{
		try
		{
			String tName = "Teammate T";
			AllListsRequest req = new AllListsRequest(tName);
			testFailInput(req,"teammateName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
