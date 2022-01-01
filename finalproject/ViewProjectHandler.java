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
import com.nildesperandumcs3733.finalproject.http.CreateProjectRequest;
import com.nildesperandumcs3733.finalproject.http.CreateProjectResponse;
import com.nildesperandumcs3733.finalproject.http.ViewProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;


public class ViewProjectHandler implements RequestHandler<CreateProjectRequest,ViewProjectResponse> {

	public LambdaLogger logger;
	
	/** Load from RDS, if it exists
	 * 
	 * @throws Exception 
	 */
	Project getProject(String projectName) throws Exception {
		logger.log("in getProjects");
		ProjectDAO dao = new ProjectDAO();
		return dao.getProject(projectName);
	}
	@Override
	public ViewProjectResponse handleRequest(CreateProjectRequest req, Context context) {
		// TODO Auto-generated method stub
			logger = context.getLogger();
			logger.log("Loading Java Lambda handler to list all constants");
			ViewProjectResponse response=null;
			try {
				// get all user defined constants AND system-defined constants.
				// Note that user defined constants override system-defined constants.
				Project proj = getProject(req.getProjectName());
				if(proj !=null)
				{
				response = new ViewProjectResponse(proj,200);
				}
				else
				{
					response = new ViewProjectResponse(400, "Project name not found");
				}
			} catch (Exception e) {
				response = new ViewProjectResponse(400, e.getMessage());
			}
			
			return response;
		}
	}
	