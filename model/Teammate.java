package com.nildespernadumcs3733.finalproject.model;
import com.nildespernadumcs3733.finalproject.model.Project;

public class Teammate {
	public final String memberID;
	public final String projectID;


	Project proj;
	
	public Teammate (String memberID, String projectID) {
		this.memberID = memberID;
		this.projectID= projectID;

	}
	
	
	public String getmemberID() { return this.memberID; }
	public String getprojectID() {return this.projectID;}


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
