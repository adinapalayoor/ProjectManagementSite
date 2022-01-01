package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildesperandumcs3733.finalproject.http.ViewProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class ViewProjectTest extends LambdaTest{

	void testInput(String in, String out) throws IOException
	{
		ViewProjectHandler handler = new ViewProjectHandler();
		CreateProjectRequest request = new Gson().fromJson(in, CreateProjectRequest.class);
		ViewProjectResponse response = handler.handleRequest(request, createContext("compute"));
		
		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}
	void testNHFailInput(String in, String out) throws IOException
	{
		ViewProjectHandler handler = new ViewProjectHandler();
		CreateProjectRequest request = new Gson().fromJson(in, CreateProjectRequest.class);
		ViewProjectResponse response = handler.handleRequest(request, createContext("compute"));
		
		Assert.assertEquals(400, response.statusCode);
	}
	
	@Test
	public void testViewProject()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		String Input = "{\"projectName\": \"" + pName + "\"}";
		String Result = "Successfully viewed project: " + p.toString();
		
		try
		{
			ProjectDAO pd = new ProjectDAO();
			pd.addProject(p.name);
			testInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
	}
	@Test
	public void testCreateProjectAlreadyExists()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		Project p2 = new Project(pName, id);
		String Input = "{\"projectName\": \"" + pName + "\"}";
		String Result = "There is already a project of this name: " + p.toString();
		
		try
		{
			ProjectDAO pd = new ProjectDAO();
			pd.addProject(p.name);
			pd.addProject(p2.name);
			testInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

}
