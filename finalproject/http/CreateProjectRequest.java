package com.nildesperandumcs3733.finalproject.http;


/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class CreateProjectRequest {
	String projectName;


	public String getProjectName() { return this.projectName; }

	public void setProjectName(String name) { this.projectName = name; }

//	public String toString() {
//		return "Add(" + arg1 + "," + arg2 + ")";
//	}
	
	public CreateProjectRequest (String projectName) {
		this.projectName=projectName;
	}
	
	public CreateProjectRequest() {
	}
}

