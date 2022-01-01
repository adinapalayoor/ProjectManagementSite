package com.nildesperandumcs3733.finalproject.http;


public class UnassignTeammateRequest {
	String projectID;
	String taskID;
	String memberID;
	
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
	public UnassignTeammateRequest() {
	}
	public UnassignTeammateRequest(String projectID, String taskID, String memberID) {
		this.projectID=projectID;
		this.taskID=taskID;
		this.memberID=memberID;
	}
	public UnassignTeammateRequest(String projectID,String memberID) {
		this.projectID=projectID;
		this.memberID=memberID;
	}
	
	
}