
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


import com.nildesperandumcs3733.finalproject.http.UnassignTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.UnassignTeammateResponse;
import com.nildespernadumcs3733.finalproject.db.AssignmentDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Assignments;
import com.nildespernadumcs3733.finalproject.model.Task;


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
public class UnassignTeammateHandler implements RequestHandler<UnassignTeammateRequest,UnassignTeammateResponse> {

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
	boolean unassignTeammate(String projectID, String taskID, String memberID) throws Exception {
		 
			if (logger != null) { logger.log("in unassignTeammate"); }
			AssignmentDAO dao = new AssignmentDAO();
			// check if present
			Assignments exist =dao.getAssignment(projectID, taskID, memberID);
			
			if (exist != null) {
				return dao.unassignTeammate(projectID, taskID, memberID);
			} else {
				return false;   // same project name exists
			}
	}

	@Override
	public UnassignTeammateResponse handleRequest(UnassignTeammateRequest req, Context context) {
		
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestHandler");
		logger.log(req.toString());
		boolean fail= false;
		try {
			fail = unassignTeammate(req.getProjectID(), req.getTaskID(),req.getMemberID());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String failMessage = "";

		// compute proper response and return. Note that the status code is internal to the HTTP response
		// and has to be processed specifically by the client code.
		UnassignTeammateResponse response;
		if (!fail) {
			response = new UnassignTeammateResponse(400, failMessage);
		} else {
			response = new UnassignTeammateResponse("Teammate Unassigned", 200);  // success
		}
		return response; 
	}
}
