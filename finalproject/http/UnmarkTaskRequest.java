package com.nildesperandumcs3733.finalproject.http;

import com.nildespernadumcs3733.finalproject.model.Task;

public class UnmarkTaskRequest {

	String taskID;
	String projectID;


	public String getTaskID() { return this.taskID; }


	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public UnmarkTaskRequest() {
	}
	public UnmarkTaskRequest(String taskID, String projectID) {
		this.taskID=taskID;
		this.projectID=projectID;

	}
}
