package com.nildesperandumcs3733.finalproject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nildesperandumcs3733.finalproject.http.RenameTaskRequest;
import com.nildesperandumcs3733.finalproject.http.RenameTaskResponse;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Task;


public class RenameTaskHandler implements RequestHandler<RenameTaskRequest,RenameTaskResponse>{

	LambdaLogger logger;
	boolean renameTask(String newTaskName, String oldTaskName,String projectID) throws Exception {
		 
			if (logger != null) { logger.log("in renameTask"); }
			TaskDAO dao = new TaskDAO();
			// check if present
			Task exist = dao.getTask(oldTaskName,projectID);
			if (exist != null) {
				return dao.renameTask(newTaskName,exist);
			} else {
				return false;  
			}
	}

	@Override
	public RenameTaskResponse handleRequest(RenameTaskRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		boolean fail= false;
		try {
			fail = renameTask(req.getnewTaskName(),req.getoldTaskName(),req.getprojectID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	String failMessage = "";
//
//		// compute proper response and return. Note that the status code is internal to the HTTP response
//		// and has to be processed specifically by the client code.
	RenameTaskResponse response;
		if (!fail) {
			response = new RenameTaskResponse(400, failMessage);
		} else {
		response = new RenameTaskResponse("Task renamed", 200);  // success
		}
		return response; 
	}
}


