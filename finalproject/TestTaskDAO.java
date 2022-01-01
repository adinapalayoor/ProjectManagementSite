package com.nildesperandumcs3733.finalproject;
import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;

import org.junit.Assert;
public class TestTaskDAO {

	@Test
	public void test() {
		try {
			UUID uid = UUID.randomUUID();
			UUID uid2 = UUID.randomUUID();
			UUID uid3 = UUID.randomUUID();
			String taskID = uid.toString().substring(0, 5);
			String projectID = uid2.toString().substring(0, 5);
			String taskOutlineNumber = uid2.toString().substring(0,5);
			String newTaskName = uid3.toString().substring(0,5);
			
			//add project and get project
			TaskDAO dao = new TaskDAO();
			Task c = new Task(projectID, taskID, false,false,taskOutlineNumber);
			Assert.assertTrue(dao.addTask(c.gettaskID(), c.getprojectID(),c.gettaskID()));
			
			// get project
			Task c2 = dao.getTask(taskID, projectID);
			Assert.assertTrue(c2 != null);
			
			//mark task
			Task c3 = new Task(projectID, taskID, false, false, taskOutlineNumber);
			dao.markTask(c3);
			Assert.assertTrue(dao.markTask(c3));
			
			//rename task
			Task c4 = new Task(projectID, taskID, false, false, taskOutlineNumber);
			dao.renameTask(newTaskName,c4);
			Assert.assertTrue(dao.renameTask(newTaskName,c4));
		
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}
	
	@Test
	public void testFailToUpdate() {
		try {
			UUID uid = UUID.randomUUID();
			UUID uid2 = UUID.randomUUID();
			UUID uid3 = UUID.randomUUID();
			String taskID = uid.toString().substring(0, 5);
			String projectID = uid2.toString().substring(0, 5);
			String taskOutlineNumber = uid2.toString().substring(0,5);
			String newTaskName = uid3.toString().substring(0,5);
			
			TaskDAO dao = new TaskDAO();
			
			Task c3 = new Task(projectID, taskID, false, false, taskOutlineNumber);
			Assert.assertFalse(dao.addTask(c3.gettaskID(),c3.getprojectID(),c3.gettaskID()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}

}
