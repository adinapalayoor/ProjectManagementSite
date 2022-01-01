package com.nildesperandumcs3733.finalproject.http;

import com.nildespernadumcs3733.finalproject.model.Assignments;

/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class AssignTeammateRequest {
	String projectID;
	String taskID;
	String memberID;

	public AssignTeammateRequest() {
	}
	public AssignTeammateRequest(String projectID, String taskID, String memberID) {
		this.projectID=projectID;
		this.taskID=taskID;
		this.memberID=memberID;
	}

	public String getProjectID() {
		return projectID;
	}

	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public String getMemberID() {
		return memberID;
	}

	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}
}