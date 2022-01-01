package com.nildesperandumcs3733.finalproject;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.nildespernadumcs3733.finalproject.db.AssignmentDAO;
import com.nildespernadumcs3733.finalproject.model.Assignments;

import org.junit.Assert;
public class TestAssignmentsDAO {

	@Test
	public void test() {
		try {
			UUID uid = UUID.randomUUID();
			UUID uid2 = UUID.randomUUID();
			UUID uid3 = UUID.randomUUID();
			String projectID = uid.toString().substring(0, 5);
			String taskID = uid2.toString().substring(0, 5);
			String memberID = uid3.toString().substring(0, 5);
			
			//add assignment and get assignment
			AssignmentDAO dao = new AssignmentDAO();
			Assignments c = new Assignments(projectID, taskID, memberID);
			dao.getAssignment(projectID, taskID, memberID);
			Assert.assertTrue(c != null);
			
			Assignments c2 = new Assignments(projectID, taskID, memberID);
			dao.assignTeammate(c2.gettaskID(),c2.getprojectID(),c2.getmemberID());
			Assert.assertTrue(dao.assignTeammate(c2.taskID,c2.projectID,c2.memberID));
			
			
			Assignments c3 = new Assignments(projectID, taskID, memberID);
			dao.assignTeammate(c3.gettaskID(),c3.getprojectID(),c3.getmemberID());
			dao.unassignTeammate(c3.gettaskID(),c3.getprojectID(),c3.getmemberID());
			Assert.assertTrue(dao.unassignTeammate(c3.gettaskID(),c3.getprojectID(),c3.getmemberID()));
		
			
			//archive project
//			Assignments c3 = new Assignments(projectID,taskID, memberID);
//			dao.archiveProject(c3.getprojectName());
//			Assert.assertTrue(dao.archiveProject(c3.getprojectName()));
			
			//delete project
//			Project c4 = dao.getProject(projectName);
//			dao.deleteProject(c4);
//			Assert.assertTrue(dao.deleteProject(c4));
		
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}
	
	@Test
	public void testFailToUpdate() {
		try {
			UUID uid = UUID.randomUUID();
			String projectID = uid.toString().substring(0, 5);
			String taskID = uid.toString().substring(0, 5);
			String memberID = uid.toString().substring(0, 5);
			
			AssignmentDAO dao = new AssignmentDAO();
			
			Assignments c3 = new Assignments(projectID, taskID, memberID);
			Assert.assertFalse(dao.assignTeammate(c3.getprojectID(),c3.gettaskID(),c3.getmemberID()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}

}
