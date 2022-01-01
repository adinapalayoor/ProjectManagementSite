package com.nildesperandumcs3733.finalproject.http;

import com.nildespernadumcs3733.finalproject.model.Project;

public class ArchiveProjectRequest {

	String projectName;

	public String getProjectName() { return this.projectName; }

	public void setProjectName(String name) { this.projectName = name; }

//	public String toString() {
//		return "Add(" + arg1 + "," + arg2 + ")";
//	}
	
	public ArchiveProjectRequest (String projectName) {
		this.projectName=projectName;
	}
	
	public ArchiveProjectRequest() {
	}
}