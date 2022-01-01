package com.nildesperandumcs3733.finalproject.http;

public class RenameTaskRequest {
	String newTaskName;
	String oldTaskName;
	String projectID;

	public String getnewTaskName() { return this.newTaskName; }
	public String getoldTaskName() { return this.oldTaskName; }
	public String getprojectID() { return this.projectID; }
	public void setnewTaskName(String newTaskName) { this.newTaskName = newTaskName; }
    public void setoldTaskName(String oldTaskName) { this.oldTaskName =oldTaskName;}
    public void setprojectID(String projectID) { this.projectID=projectID;}
    
	
	public RenameTaskRequest (String newTaskName,String oldTaskName) 
	{
		this.newTaskName=newTaskName;
		this.oldTaskName=oldTaskName;
	}
	
	public RenameTaskRequest() {
	}
}

