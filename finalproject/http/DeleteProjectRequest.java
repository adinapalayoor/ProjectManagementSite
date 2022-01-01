package com.nildesperandumcs3733.finalproject.http;

public class DeleteProjectRequest {

	public String projectName;
	
	public void setProjectName(String name) { this.projectName = name;}
	public String getProjectName() { return this.projectName;}
	
	public DeleteProjectRequest(String projectName) {
		this.projectName=projectName;
	}
	
	public DeleteProjectRequest() {}
	

}
