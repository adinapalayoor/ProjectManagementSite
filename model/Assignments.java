package com.nildespernadumcs3733.finalproject.model;
import com.nildespernadumcs3733.finalproject.model.Project;

public class Assignments {

	public final String projectID;
	public final String taskID;
	public final String memberID;

	Project proj;
	
	public Assignments (String projectID, String taskID, String memberID) {
		this.projectID = projectID;
		this.taskID=taskID;
		this.memberID=memberID;
	}
	
	public String getprojectID() { return this.projectID; }
	public String gettaskID() { return this.taskID; }
	public String getmemberID() {return this.memberID;}

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