package com.nildesperandumcs3733.finalproject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Request;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.nildesperandumcs3733.finalproject.http.AllListsRequest;
import com.nildesperandumcs3733.finalproject.http.AllProjectsResponse;
import com.nildesperandumcs3733.finalproject.http.AllTasksResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;


public class ListAllTasksHandler implements RequestHandler<AllListsRequest,AllTasksResponse> {

	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Task> getTasks(String projectName) throws Exception {
		logger.log("in getTasks");
		TaskDAO dao = new TaskDAO();
		ProjectDAO dao2 = new ProjectDAO();
		
		return dao.getAllTasks(projectName);
	}
	@Override
	public AllTasksResponse handleRequest(AllListsRequest req, Context context) {
		// TODO Auto-generated method stub
			logger = context.getLogger();
			logger.log("Loading Java Lambda handler to list all constants");

			AllTasksResponse response;
			try {
				// get all user defined constants AND system-defined constants.
				// Note that user defined constants override system-defined constants.
				List<Task> list = getTasks(req.getProjectName());
				response = new AllTasksResponse(list, 200);
			} catch (Exception e) {
				response = new AllTasksResponse(400, e.getMessage());
			}
			
			return response;
		}
	}
	