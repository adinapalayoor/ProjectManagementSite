package com.nildesperandumcs3733.finalproject.http;


/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class AddTeammateRequest {
	String teammateName;
	String projectID;

	public String getTeammateName() { return this.teammateName; }
	public String getProjectID() { return this.projectID; }

	public void setTeammateName(String name) { this.teammateName = name; }
	public void setProjectID(String projectname) {
		this.projectID=projectname;
	}

//	public String toString() {
//		return "Add(" + arg1 + "," + arg2 + ")";
//	}
	
	public AddTeammateRequest (String teammateName) {
		this.teammateName=teammateName;
	}
	
	public AddTeammateRequest() {
	}
}
