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
import com.nildesperandumcs3733.finalproject.http.AllProjectsResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;


public class ListAllProjectsHandler implements RequestHandler<Object,AllProjectsResponse> {

	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	List<Project> getProjects() throws Exception {
		logger.log("in getProjects");
		ProjectDAO dao = new ProjectDAO();
		
		return dao.getAllProjects();
	}
	@Override
	public AllProjectsResponse handleRequest(Object input, Context context) {
		// TODO Auto-generated method stub
			logger = context.getLogger();
			logger.log("Loading Java Lambda handler to list all constants");

			AllProjectsResponse response;
			try {
				// get all user defined constants AND system-defined constants.
				// Note that user defined constants override system-defined constants.
				List<Project> list = getProjects();
				response = new AllProjectsResponse(list, 200);
			} catch (Exception e) {
				response = new AllProjectsResponse(400, e.getMessage());
			}
			
			return response;
		}
	}
	