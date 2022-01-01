package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildesperandumcs3733.finalproject.http.CreateProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class CreateProjectTest extends LambdaTest{

	void testInput(CreateProjectRequest req, String out) throws IOException
	{
		ProjectHandler handler = new ProjectHandler();
		CreateProjectResponse response = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(400, response.statusCode);
	}
	void testInputAgain(CreateProjectRequest req, String out) throws IOException
	{
		ProjectHandler handler = new ProjectHandler();
		CreateProjectResponse response = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(400, response.statusCode);
	}
	void oldTestInput(String in, String out) throws IOException
	{
		ProjectHandler handler = new ProjectHandler();
		CreateProjectRequest request = new Gson().fromJson(in, CreateProjectRequest.class);
		CreateProjectResponse response = handler.handleRequest(request, createContext("compute"));
		
		Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}


	@Test
	public void testCreateProject()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		String Input = "{\"projectName\": \"" + pName + "\"}";
		String Result = "Successfully created project: " + p.toString();
		
		try
		{
			ProjectDAO pd = new ProjectDAO();
			pd.addProject(p.name);
			//testInputAgain(Input, Result);	
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
			oldTestInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}
	}

	@Test
	public void testCreateProjectAgain()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			CreateProjectRequest req = new CreateProjectRequest();
			req.setProjectName(pName);
			testInputAgain(req,"projectName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateProjectThree()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			CreateProjectRequest req = new CreateProjectRequest();
			req.setProjectName(pName);
			testInput(req,"projectName");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
