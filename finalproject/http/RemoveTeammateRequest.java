package com.nildesperandumcs3733.finalproject.http;

public class RemoveTeammateRequest {

	public String teammateName;
	public String projectID;
	
	public void setTeammateName(String name) { this.teammateName = name;}
	public String getTeammateName() { return this.teammateName;}
	
	public RemoveTeammateRequest(String teammateName, String projectID) {
		this.teammateName=teammateName;
		this.projectID=projectID;
	}
	
	public RemoveTeammateRequest() {}
	

}