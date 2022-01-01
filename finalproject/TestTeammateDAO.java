package com.nildesperandumcs3733.finalproject;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;

import com.nildespernadumcs3733.finalproject.db.ProjectDAO;
import com.nildespernadumcs3733.finalproject.db.TaskDAO;
import com.nildespernadumcs3733.finalproject.db.TeammateDAO;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Teammate;

import org.junit.Assert;
public class TestTeammateDAO {

	@Test
	public void test() {
		try {
			UUID uid = UUID.randomUUID();
			UUID uid2 = UUID.randomUUID();
			String memberID = uid.toString().substring(0, 5);
			String projectID = uid2.toString().substring(0, 5);

			//add project and get project
			TeammateDAO dao = new TeammateDAO();
			Teammate c = new Teammate(memberID, projectID);
			Assert.assertTrue(dao.addTeammate(c.getmemberID(), c.getprojectID()));
			
			// get project
			Teammate c2 = dao.getTeammate(memberID, projectID);
			Assert.assertTrue(c2 != null);
			
			//mark task

			dao.removeTeammate(c2);
			Assert.assertTrue(dao.removeTeammate(c2));
			
			
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
			String memberID = uid.toString().substring(0, 5);
			String projectID = uid2.toString().substring(0, 5);
			
			TeammateDAO dao = new TeammateDAO();
			
			Teammate c3 = new Teammate(memberID,projectID);
			Assert.assertFalse(dao.addTeammate(c3.getmemberID(),c3.getprojectID()));
			
		} catch (Exception e) {
			e.printStackTrace();
			fail ("Error:" + e.getMessage());
		}
		
	}

}
