package com.nildespernadumcs3733.finalproject.model;

import com.nildespernadumcs3733.finalproject.model.Project;

public class Task {
	public final String projectID;
	public final String taskID;
	public final Boolean isCompleted;
	public final Boolean isSubdivided;
	public final String taskOutlineNumber;

	

	public Task (String projectID, String taskID, Boolean isCompleted, Boolean isSubdivided, String taskOutlineNumber) {
		this.projectID = projectID;
		this.taskID=taskID;
		this.isCompleted = isCompleted;
		this.isSubdivided = isSubdivided;
		this.taskOutlineNumber = taskOutlineNumber;
	}

	
	public String getprojectID() { return this.projectID; }
	public String gettaskID() { return this.taskID; }
	public Boolean getisCompleted() { return this.isCompleted; }
	public Boolean isSubdivided() { return this.isSubdivided; }
	public String gettaskNumber() { return this.taskOutlineNumber; }



	// Override equals for tasks

//	public boolean equals (Object o) {
//		if (o == null) { return false; }
//		
//		if (o instanceof Project) {
//			Project other = (Project) o;
//			return name.equals(other.name);
//		}
//		
//		return false;  // not a Constant
//	}

//	public String toString() {
//		String sysString = "";
//		if (system) { sysString = " (system)"; }
//		return "[" + name+ " = " + value + sysString + "]";
//	}
}
