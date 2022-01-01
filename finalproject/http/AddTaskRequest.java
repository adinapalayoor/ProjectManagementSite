
package com.nildesperandumcs3733.finalproject.http;


/** To work with AWS must not have final attributes, must have no-arg constructor, and all get/set methods. */
public class AddTaskRequest {
	String taskName;
	String projectID;
	String taskCounter;

	public String getTaskName() { return this.taskName; }
	public String getProjectID() { return this.projectID; }
	public String getTaskCounter() { return this.taskCounter; }

	public void setTaskName(String name) { this.taskName = name; }
	public void setProjectID(String name) { this.projectID = name; }
	public void setTaskCounter(String taskCounter) {this.taskCounter=taskCounter;}

//	public String toString() {
//		return "Add(" + arg1 + "," + arg2 + ")";
//	}
	
	public AddTaskRequest (String taskName) {
		this.taskName=taskName;
	}
	
	public AddTaskRequest() {
	}
}

