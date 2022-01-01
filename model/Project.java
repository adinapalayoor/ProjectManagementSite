package com.nildespernadumcs3733.finalproject.model;
import java.util.ArrayList;
import java.util.List;


public class Project {
	public final String name;
	public final String id;
	public final boolean isArchived;      // when TRUE this is actually stored in S3 bucket
	public final List<Task> tasks;
	public final List<Teammate> teammates;
	
	public Project (String name, String id) {
		this.name = name;
		this.id = id;
		this.isArchived = false;
		this.tasks=new ArrayList();
		this.teammates=new ArrayList();
	}
	
	public Project (String name, String id, boolean isArchived) {
		this.name = name;
		this.id = id;
		this.isArchived = isArchived;
		this.tasks=new ArrayList();
		this.teammates=new ArrayList();
	}
	
	public boolean getisArchived() { return isArchived; }
	public String getprojectName() { return this.name; }
	public String getprojectID() { return this.id;}
	
	/**
	 * Equality of Constants determined by name alone.
	 */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		
		if (o instanceof Project) {
			Project other = (Project) o;
			return name.equals(other.name);
		}
		
		return false;  // not a Constant
	}


//	public String toString() {
//		String sysString = "";
//		if (system) { sysString = " (system)"; }
//		return "[" + name+ " = " + value + sysString + "]";
//	}
}
