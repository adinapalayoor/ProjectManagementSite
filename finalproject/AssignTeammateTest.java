package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.AssignTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.AssignTeammateResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TeammateDAO;
import com.nildespernadumcs3733.finalproject.model.Teammate;
import com.nildespernadumcs3733.finalproject.model.Project;

import com.google.gson.Gson;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AssignTeammateTest extends LambdaTest {

	void testInput(AssignTeammateRequest req, String outgoing) throws IOException {
		AssignTeammateHandler handler = new AssignTeammateHandler();
		AssignTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testSuccessInput(AssignTeammateRequest req, String outgoing) throws IOException {
		AssignTeammateHandler handler = new AssignTeammateHandler();
		AssignTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(myresponse.statusCode, 400);
	}
	void testAdd(AssignTeammateRequest req, String outgoing) throws IOException {
		AssignTeammateHandler handler = new AssignTeammateHandler();
		AssignTeammateResponse myresponse = handler.handleRequest(req, createContext("compute"));
		
		Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(200, myresponse.statusCode);
	}


	// presumes the existence of a e constant -- which was created during tutorial
	@Test
	public void testAssignTeammate() {
		try {
			String projectID = "myTestProjectID";
			String taskID = "myTaskID";
			String memberID = "memberID";
			AssignTeammateRequest req = new AssignTeammateRequest();
			req.setProjectID(projectID);
			req.setTaskID(taskID);
			req.setMemberID(memberID);
			testInput(req,"Teammate Assigned");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAssignTeammateAgain() {
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
	public void testAssignTeammateThree() {
		try {
			String projectID = "myTestProjectID";
			String taskID = "myTaskID";
			String memberID = "memberID";
			AssignTeammateRequest req = new AssignTeammateRequest(projectID,taskID,memberID);
			req.setProjectID(projectID);
			req.setTaskID(taskID);
			req.setMemberID(memberID);
			testSuccessInput(req,"Teammate Assigned");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
