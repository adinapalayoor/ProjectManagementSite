package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.AddTaskRequest;
import com.nildesperandumcs3733.finalproject.http.AddTaskResponse;
import com.nildesperandumcs3733.finalproject.http.MarkTaskRequest;
import com.nildesperandumcs3733.finalproject.http.MarkTaskResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class MarkTaskTest extends LambdaTest {

	void testInputFail(MarkTaskRequest req, String outgoing) throws IOException {
		MarkTaskHandler handler = new MarkTaskHandler();
		MarkTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		Assert.assertEquals(400, myresponse.statusCode);
	}
	
	void testInput(MarkTaskRequest req, String outgoing) throws IOException {
		MarkTaskHandler handler = new MarkTaskHandler();
		MarkTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testNHFailInput(MarkTaskRequest req, String outgoing) throws IOException {
		MarkTaskHandler handler = new MarkTaskHandler();
		MarkTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		Assert.assertEquals(400, myresponse.statusCode);
	}

	@Test
	public void testMarkTaskInput() {
		try {
			String projectName = "myTestProject";
			String taskID = "taskID";
			MarkTaskRequest req = new MarkTaskRequest();
			req.setProjectID(projectName);
			req.setTaskID(taskID);
			testInputFail(req, "Task added");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMarkTaskAgain() {
		try {
			String projectName = "myTestProject";
			String taskID = "taskID";
			MarkTaskRequest req = new MarkTaskRequest(taskID,projectName);
			testInputFail(req, "Task added");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// presumes the existence of a e constant -- which was created during tutorial
		@Test
		public void testMarkTask() {
			try {
				String projectName = "myTestProject";
				String projectID = "myTestProjectID";
				Project proj = new Project(projectName, projectID);

				String taskID = "myTestTask";

				ProjectDAO projDAO = new ProjectDAO();
				TaskDAO taskDAO = new TaskDAO();
				Task task = new Task(projectID, taskID, false, false,taskID);

				taskDAO.markTask(task);
				Task myNewTask = new Task(projectID, taskID, true, false,taskID);
				Assert.assertEquals(task, myNewTask);

			} catch (Exception e) {
				Assert.fail("Invalid:" + e.getMessage());
			}
		}





}