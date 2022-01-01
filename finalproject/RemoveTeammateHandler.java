package com.nildesperandumcs3733.finalproject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectResponse;
import com.nildesperandumcs3733.finalproject.http.RemoveTeammateRequest;
import com.nildesperandumcs3733.finalproject.http.RemoveTeammateResponse;
import com.nildespernadumcs3733.finalproject.db.AssignmentDAO;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TeammateDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Teammate;



/**
 * No more JSON parsing
 */
public class RemoveTeammateHandler implements RequestHandler<RemoveTeammateRequest,RemoveTeammateResponse> {

	public LambdaLogger logger = null;

	@Override
	public RemoveTeammateResponse handleRequest(RemoveTeammateRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to remove teammate");

		RemoveTeammateResponse response = null;
		logger.log(req.toString());

	     TeammateDAO dao=new TeammateDAO();
	     AssignmentDAO dao2 = new AssignmentDAO();
	  
         Teammate team= new Teammate(req.teammateName,req.projectID);
         
	     
		try {
			if (dao.removeTeammate(team))
			{
				response = new RemoveTeammateResponse(req.teammateName, 200);
				dao2.unassignTeammate2(req.teammateName, req.projectID);
				
			} else {
				response = new RemoveTeammateResponse(req.teammateName, 422, "Unable to remove teammate.");
			}
		} catch (Exception e) {
			response = new RemoveTeammateResponse(req.teammateName, 403, "Unable to remove teammate: " + req.teammateName + "(" + e.getMessage() + ")");
		}

		return response;
	}

}

