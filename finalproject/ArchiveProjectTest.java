package com.nildesperandumcs3733.finalproject;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import com.nildesperandumcs3733.finalproject.http.ArchiveProjectRequest;
import com.nildesperandumcs3733.finalproject.http.ArchiveProjectResponse;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

public class ArchiveProjectTest extends LambdaTest{

	void testInput(ArchiveProjectRequest req, String out) throws IOException
	{
		ArchiveProjectHandler handler = new ArchiveProjectHandler();
		ArchiveProjectResponse response = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(200, response.statusCode);
	}
	void testNHFailInput(ArchiveProjectRequest req, String out) throws IOException
	{
		ArchiveProjectHandler handler = new ArchiveProjectHandler();
		ArchiveProjectResponse response = handler.handleRequest(req, createContext("compute"));

		Assert.assertEquals(400, response.statusCode);
	}

	@Test
	public void testArchiveProject()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		String Input = "{\"projectName\": \"" + pName + "\"}";
		String Result = "Successfully archived project: " + p.toString();

		try
		{
			ProjectDAO pd = new ProjectDAO();
			pd.addProject(p.name);
			pd.archiveProject(p.name);
			//testInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}

	}
	
	@Test
	public void testArchiveProjectAgain()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			ArchiveProjectRequest req = new ArchiveProjectRequest();
			req.setProjectName(pName);
			testInput(req,"projectName");

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testArchiveProjectThree()
	{
		try
		{
			String pName = "Project T";
			String id = "a";
			Project p = new Project(pName, id);
			ArchiveProjectRequest req = new ArchiveProjectRequest(pName);
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
