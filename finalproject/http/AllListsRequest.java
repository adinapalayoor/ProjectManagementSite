package com.nildesperandumcs3733.finalproject.http;

public class AllListsRequest {

	public String projectName;
	
	public void setProjectName(String name) { this.projectName = name;}
	public String getProjectName() { return this.projectName;}
	
	public AllListsRequest(String projectName) {
		this.projectName=projectName;
	}
	
	public AllListsRequest() {}
	

}
