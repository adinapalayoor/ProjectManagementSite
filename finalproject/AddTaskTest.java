package com.nildesperandumcs3733.finalproject;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import com.nildesperandumcs3733.finalproject.http.AddTaskRequest;
import com.nildesperandumcs3733.finalproject.http.AddTaskResponse;
import com.nildesperandumcs3733.finalproject.http.MarkTaskRequest;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.google.gson.Gson;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddTaskTest extends LambdaTest {

	void testFailInput(AddTaskRequest req, String outgoing) throws IOException {
		TaskHandler handler = new TaskHandler();
		//AddTaskRequest req = new Gson().fromJson(incoming, AddTaskRequest.class);
		AddTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		//Assert.assertEquals(outgoing, myresponse.result);
		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testInput(AddTaskRequest req, String outgoing) throws IOException {
		TaskHandler handler = new TaskHandler();
		//AddTaskRequest req = new Gson().fromJson(incoming, AddTaskRequest.class);
		AddTaskResponse myresponse = handler.handleRequest(req, createContext(outgoing));

		Assert.assertEquals(400, myresponse.statusCode);
	}
	void testNHFailInput(String incoming, String outgoing) throws IOException {
		TaskHandler handler = new TaskHandler();
		AddTaskRequest req = new Gson().fromJson(incoming, AddTaskRequest.class);
		AddTaskResponse response = (AddTaskResponse) handler.handleRequest(req, createContext("compute"));

		Assert.assertEquals(400, response.statusCode);
	}


	// presumes the existence of a e constant -- which was created during tutorial
	@Test

	public void testAddTaskInput() {

		try {

			String projectName = "myTestProject";

			String taskID = "taskID";

			AddTaskRequest req = new AddTaskRequest();

			req.setProjectID(projectName);

			req.setTaskName(taskID);

			testFailInput(req, "Task added");

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}



	@Test

	public void testAddTaskAgain() {

		try {

			String projectName = "myTestProject";

			String taskID = "taskID";

			AddTaskRequest req = new AddTaskRequest(taskID);

			testFailInput(req, "Task added");

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}





	// presumes the existence of a e constant -- which was created during tutorial

	

	}

