package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.nildesperandumcs3733.finalproject.http.AssignTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectResponse;
import com.nildesperandumcs3733.finalproject.http.RemoveTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.RemoveTeammateResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TeammateDAO;
import com.nildespernadumcs3733.finalproject.model.Teammate;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;

import org.junit.Test;

public class RemoveTeammateTest extends LambdaTest{

	void testInput(RemoveTeammateRequest req, String out) throws IOException
	{
		RemoveTeammateHandler handler = new RemoveTeammateHandler();
		RemoveTeammateResponse response = handler.handleRequest(req, createContext("compute"));
		
		//Assert.assertEquals(out, response.result);
		Assert.assertEquals(response.statusCode, 400);
	}
	void testAnotherInput(RemoveTeammateRequest req, String out) throws IOException
	{
		RemoveTeammateHandler handler = new RemoveTeammateHandler();
		RemoveTeammateResponse response = handler.handleRequest(req, createContext("compute"));
		
		Assert.assertEquals(response.statusCode, 400);
	}
	
	@Test
	public void testRemoveTeammate()
	{
		String pName = "Project T";
		String id = "a";
		Project p = new Project(pName, id);
		
		String pID = id;
		String mID = "Teammate T";
		Teammate t = new Teammate(mID, pID);
		
		String Input = "{\"teammateName\": \"" + mID + "\"}";
		String Result = "Successfully removed teammate: " + t.toString();
		
		try
		{
			ProjectDAO pd = new ProjectDAO();
			TeammateDAO td = new TeammateDAO();
			pd.addProject(p.name);
			td.addTeammate(t.memberID, p.name);
			td.removeTeammate(t);
			pd.deleteProject(p);
			//testInput(Input, Result);	
		}
		catch (Exception ioe)
		{
			Assert.fail("Invalid:" + ioe.getMessage());
		}
		
	}
	
	@Test
	public void testRemoveTeammateAgain()
	{
		try {
			String teammateName = "myTeammateName";
			RemoveTeammateRequest req = new RemoveTeammateRequest();
			req.setTeammateName(teammateName);
			req.getTeammateName();
			testInput(req,"Teammate Removed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRemoveTeammateThree()
	{
		try {
			String teammateName = "myTeammateName";
			RemoveTeammateRequest req = new RemoveTeammateRequest(teammateName);
			testInput(req,"Teammate Removed");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
