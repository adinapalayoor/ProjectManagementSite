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
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;

public class ProjectHandler implements RequestHandler<CreateProjectRequest,CreateProjectResponse> {

	LambdaLogger logger;
	
	boolean createProject(String projectName) throws Exception {
		 
			if (logger != null) { logger.log("in createProject"); }
			ProjectDAO dao = new ProjectDAO();
			// check if present
			Project exist = dao.getProject(projectName);
			//Project newProject = new Project (projectName, projectName);
			if (exist == null) {
				return dao.addProject(projectName);
			} else {
				return false;   // same project name exists
			}
	}

	@Override
	public CreateProjectResponse handleRequest(CreateProjectRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		boolean fail= false;
		try {
			fail = createProject(req.getProjectName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String failMessage = "";

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		CreateProjectResponse response;
		if (!fail) {
			response = new CreateProjectResponse(400, failMessage);
		} else {
			response = new CreateProjectResponse("Project Added", 200);  // success
		}
		return response; 
	}
}

