package com.nildesperandumcs3733.finalproject;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.model.Project;

import org.junit.Assert;
public class TestProjectDAO {

	@Test
	public void test() {
		try {
			UUID uid = UUID.randomUUID();
			String projectID = uid.toString().substring(0, 5);
			String projectName = projectID;
			
			//add project and get project
			ProjectDAO dao = new ProjectDAO();
			Project c = new Project(projectID, projectName, false);
			Assert.assertTrue(dao.addProject(c.getprojectName()));
			
			// get project
			Project c2 = dao.getProject(projectName);
			Assert.assertTrue(c2 != null);
			
			//archive project
			Project c3 = new Project(projectID,projectName, false);
			dao.archiveProject(c3.getprojectName());
			Assert.assertTrue(dao.archiveProject(c3.getprojectName()));
			
			//delete project
			Project c4 = dao.getProject(projectName);
			dao.deleteProject(c4);
			Assert.assertTrue(dao.deleteProject(c4));
		
			
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
			String projectName = projectID;
			
			ProjectDAO dao = new ProjectDAO();
			
			Project c3 = new Project(projectName, projectID, false);
			Assert.assertFalse(dao.addProject(c3.getprojectName()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}

}
