package com.nildesperandumcs3733.finalproject;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectRequest;
import com.nildesperandumcs3733.finalproject.http.DeleteProjectResponse;
import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;



/**
 * No more JSON parsing
 */
public class DeleteProjectHandler implements RequestHandler<DeleteProjectRequest,DeleteProjectResponse> {

	public LambdaLogger logger = null;

	@Override
	public DeleteProjectResponse handleRequest(DeleteProjectRequest req, Context context) {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete");

		DeleteProjectResponse response = null;
		logger.log(req.toString());

	     ProjectDAO dao=new ProjectDAO();
         Project project= new Project(req.projectName,req.projectName);
		try {
			if (dao.deleteProject(project))
			{
				response = new DeleteProjectResponse(req.projectName, 200);
			} else {
				response = new DeleteProjectResponse(req.projectName, 422, "Unable to delete constant.");
			}
		} catch (Exception e) {
			response = new DeleteProjectResponse(req.projectName, 403, "Unable to delete constant: " + req.projectName + "(" + e.getMessage() + ")");
		}

		return response;
	}

}

