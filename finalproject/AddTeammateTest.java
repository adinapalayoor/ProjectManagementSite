package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.AddTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.AddTeammateResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TeammateDAO;
import com.nildespernadumcs3733.finalproject.model.Teammate;
import com.nildespernadumcs3733.finalproject.model.Project;

import com.google.gson.Gson;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddTeammateTest extends LambdaTest {

	void testInput(AddTeammateRequest req, String outgoing) throws IOException {
		TeammateHandler handler = new TeammateHandler();
		AddTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testSuccessInput(AddTeammateRequest req, String outgoing) throws IOException {
		TeammateHandler handler = new TeammateHandler();
		AddTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(myresponse.statusCode, 400);
	}
	void testAdd(AddTeammateRequest req, String outgoing) throws IOException {
		TeammateHandler handler = new TeammateHandler();
		AddTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));
		
		//ssert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(200, myresponse.statusCode);
	}


	// presumes the existence of a e constant -- which was created during tutorial
	@Test
	public void testAddTeammate() {
		try {
			String projectID = "myTestProjectID";
			String teammateName = "myTestTeammate";
			AddTeammateRequest req = new AddTeammateRequest();
			req.setTeammateName(teammateName);
			req.setProjectID(projectID);
			testInput(req,"Teammate added");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAddTeammateAgain() {
		try {
			ProjectDAO projDAO = new ProjectDAO();
			TeammateDAO teammateDAO = new TeammateDAO();

			String projectName = "myTestProject";
			String projectID = "myTestProjectID";
			String teammateID = "myTestTeammate";
			Project proj = new Project(projectName, projectID);

			String sampleString = "{\"newName\": \"" + teammateID + "\", \"projectName\": \"" + projectName + "\"}";
			String sampleResult = "Successfully created task: ";
			teammateDAO.getTeammate(teammateID, projectID);


			//add task and test input
			//testInput(sampleString, sampleResult);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("Invalid:" + e.getMessage());
		}
	}

	@Test
	public void testAddTeammateThree() {
		try {
			String projectID = "myTestProjectID";
			String teammateName = "myTestTeammate";
			AddTeammateRequest req = new AddTeammateRequest(teammateName);
			testSuccessInput(req,teammateName);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddTeammateDAO() {
		try {
			String projectID = "myTestProjectID";
			String teammateName = "myTestTeammate";
			AddTeammateRequest req = new AddTeammateRequest(teammateName);
			TeammateDAO teammateDAO = new TeammateDAO();
			teammateDAO.addTeammate(teammateName, projectID);
			
			testSuccessInput(req,teammateName);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
