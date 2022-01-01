package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class DeleteProjectTest extends LambdaTest{

	void testInput(DeleteProjectRequest req, String out) throws IOException
	{
		DeleteProjectHandler handler = new DeleteProjectHandler();
		DeleteProjectResponse response = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}
	void testNHFailInput(DeleteProjectRequest req, String out) throws IOException
	{
		DeleteProjectHandler handler = new DeleteProjectHandler();
		DeleteProjectResponse response = handler.handleRequest(req, createContext("compute"));

		Assert.assertEquals(400, response.statusCode);
	}

	@Test
	public void testDeleteProject()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		String Input = "{\"projectName\": \"" + pName + "\"}";
		String Result = "Successfully deleted project: " + p.toString();

		try
		{
			ProjectDAO pd = new ProjectDAO();
			pd.addProject(p.name);
			pd.deleteProject(p);
			//testInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}

	}
	
	@Test
	public void testDeleteProjectAgain()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			DeleteProjectRequest req = new DeleteProjectRequest();
			req.setProjectName(pName);
			testInput(req,"projectName");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDeleteProjectThree()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			DeleteProjectRequest req = new DeleteProjectRequest(pName);
			req.setProjectName(pName);
			req.getProjectName();
			testInput(req,"projectName");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
