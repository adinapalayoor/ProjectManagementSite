package com.nildesperandumcs3733.finalproject;

import java.util.Scanner;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;

import com.nildesperandumcs3733.finalproject.http.CreateProjectResponse;
import com.nildesperandumcs3733.finalproject.http.AddTaskRequest;
import com.nildesperandumcs3733.finalproject.http.AddTaskResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;


public class TaskHandler implements RequestHandler<AddTaskRequest,AddTaskResponse> {

	LambdaLogger logger;
	
	boolean addTask(String taskName,String projectID,String taskNumber) throws Exception {
		 
			if (logger != null) { logger.log("in addTask"); }
			TaskDAO dao = new TaskDAO();
			ProjectDAO daop=new ProjectDAO();
			Project project=daop.getProject(projectID);
			// check if present
			Task exist =dao.getTask(taskName,projectID);
			
			if (exist == null && project !=null) {
				return dao.addTask(taskName,projectID,taskNumber);
			} else {
				return false;   // same project name exists
			}
	}

	@Override
	public AddTaskResponse handleRequest(AddTaskRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		
		boolean fail= false;
		try {
			fail = addTask(req.getTaskName(),req.getProjectID(),req.getTaskCounter());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String failMessage = "";

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		AddTaskResponse response;
		if (!fail) {
			response = new AddTaskResponse(400, failMessage);
		} else {
			response = new AddTaskResponse("Task Added", 200);  // success
		}
		return response; 
	}
}