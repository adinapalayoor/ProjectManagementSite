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
import com.nildesperandumcs3733.finalproject.http.ArchiveProjectRequest;
import com.nildesperandumcs3733.finalproject.http.ArchiveProjectResponse;
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;

//import edu.wpi.cs.heineman.demo.db.ConstantsDAO;
//import edu.wpi.cs.heineman.demo.http.AddRequest;
//import edu.wpi.cs.heineman.demo.http.AddResponse;
//import edu.wpi.cs.heineman.demo.model.Constant;

/**
 * Final version of calculator.
 * 
 * If using just double values as strings, then returns the result.
 * If any of the strings do not parse as a number, they are searched for as a constant.
 * First we search the RDS database.
 * Second, we attempt to load up from S3 bucket.
 * 
 * Note: I have stopped using com.fasterxml.jackson.databind.JsonNode and instead use two different
 * JSon packages. SimpleJson is just that -- Simple!. GSon is a google package that is quite useful
 * 
 * @author heineman
 */
public class ArchiveProjectHandler implements RequestHandler<ArchiveProjectRequest,ArchiveProjectResponse> {

	LambdaLogger logger;
	

	/**
	 * Try to get from RDS first. Then get from bucket.
	 * 
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	boolean archiveProject(String projectName) throws Exception {
		 
			if (logger != null) { logger.log("in archiveProject"); }
			ProjectDAO dao = new ProjectDAO();
			// check if present
			Project exist = dao.getProject(projectName);
			//Project newProject = new Project (projectName, projectName);
			if (exist != null) {
				return dao.archiveProject(projectName);
			} else {
				return false;   // same project name exists
			}
	}

	@Override
	public ArchiveProjectResponse handleRequest(ArchiveProjectRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		boolean fail= false;
		try {
			fail = archiveProject(req.getProjectName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String failMessage = "";

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		ArchiveProjectResponse response;
		if (!fail) {
			response = new ArchiveProjectResponse(400, failMessage);
		} else {
			response = new ArchiveProjectResponse("Project Archived", 200);  // success
		}
		return response; 
	}
}
