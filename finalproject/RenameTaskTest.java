package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.AddTaskRequest;
import com.nildesperandumcs3733.finalproject.http.AddTaskResponse;
import com.nildesperandumcs3733.finalproject.http.MarkTaskRequest;
import com.nildesperandumcs3733.finalproject.http.RenameTaskRequest;
import com.nildesperandumcs3733.finalproject.http.RenameTaskResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class RenameTaskTest extends LambdaTest {

	void testInputFail(RenameTaskRequest req, String outgoing) throws IOException {
		RenameTaskHandler handler = new RenameTaskHandler();
		RenameTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		Assert.assertEquals(400, myresponse.statusCode);
	}
	
	void testInput(RenameTaskRequest req, String outgoing) throws IOException {
		RenameTaskHandler handler = new RenameTaskHandler();
		RenameTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testNHFailInput(RenameTaskRequest req, String outgoing) throws IOException {
		RenameTaskHandler handler = new RenameTaskHandler();
		RenameTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		Assert.assertEquals(400, myresponse.statusCode);
	}

	@Test
	public void testRenameTaskInput() {
		try {
			String projectID = "myTestProject";
			String oldTaskName = "myOldTaskName";
			String newTaskName = "myNewTaskName";
			RenameTaskRequest req = new RenameTaskRequest();
			req.setnewTaskName(newTaskName);
			req.setoldTaskName(oldTaskName);
			req.setprojectID(projectID);
			testInputFail(req, "Task renamed");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRenameTaskAgain() {
		try {
			String projectName = "myTestProject";
			String taskID = "taskID";
			RenameTaskRequest req = new RenameTaskRequest(taskID,projectName);
			testInputFail(req, "Task added");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}