package com.nildespernadumcs3733.finalproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Teammate;

public class TaskDAO {

	java.sql.Connection conn;

	final String tblName="Tasks";

	public TaskDAO()
	{
		try  {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}


	public Task getTask(String taskID, String projectID) throws Exception {
		try {
			Task task2 = null;

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE projectID = ?"+" AND "+"taskID = ? ;");
			ps.setString(1, projectID);
			ps.setString(2, taskID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				task2 = generateTask(resultSet);
			}
			resultSet.close();
			ps.close();

			return task2;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting constant: " + e.getMessage());
		}
	}


	public List<Task> getAllTasks(String projectName) throws Exception {

		List<Task> allTasks = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName +" WHERE projectID = ?;");
			ps.setString(1, projectName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Task c = generateTask(resultSet);
				allTasks.add(c);
			}
			resultSet.close();
			ps.close();
			return allTasks;

		} catch (Exception e) {
			throw new Exception("Failed in getting constants: " + e.getMessage());
		}
	}
	public boolean markTask(Task task) throws Exception{

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName +" SET isCompleted= ? "+" WHERE taskID = ? "+ " AND "+ " projectID=?; ");
			ps.setInt(1, 1);
			ps.setString(2, task.taskID);
			ps.setString(3, task.projectID);
			ps.executeUpdate();


			return true;

		} catch (Exception e) {
			throw new Exception("Failed to mark task: " + e.getMessage());
		}} 
	public boolean unmarkTask(Task task) throws Exception{

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName +" SET isCompleted= ? "+" WHERE taskID = ? "+ " AND "+ " projectID=?; ");
			ps.setBoolean(1, false);
			ps.setString(2, task.taskID);
			ps.setString(3, task.projectID);
			ps.executeUpdate();


			return true;

		} catch (Exception e) {
			throw new Exception("Failed to unmark task: " + e.getMessage());
		}} 

	public boolean addTask(String task,String projectID,String taskNumber) throws Exception {
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE projectID = ?"+" AND "+"taskID = ? " + " AND "+"taskOutlineNumber= ? ;");
			ps.setString(1, projectID);
			ps.setString(2, task);
			ps.setNString(3, taskNumber);
			
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				Task c = generateTask(resultSet);
				resultSet.close();
				return false;
			}

			ps = conn.prepareStatement("INSERT INTO " + tblName + " (projectID, taskID, isCompleted, isSubdivided, taskOutlineNumber) values(?,?,?,?,?);");
			ps.setString(1,projectID);
			ps.setString(2, task);
			ps.setBoolean(3,false);
			ps.setBoolean(4,false);
			ps.setString(5,taskNumber );
			ps.execute();
			ps.close();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert task: " + e.getMessage());
		}}

	public boolean decomposeTask() {
		return false;}
	
	public boolean renameTask(String newTaskName,Task task) throws Exception {
		try {
			
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName + " SET taskID = ?"+" WHERE "+"taskID = ?"+ " AND " + "projectID = ? ;");
			ps.setString(1,newTaskName);
			ps.setString(2,task.taskID);
			ps.setString(3,task.projectID);
			ps.executeUpdate();
			ps.close();
			return true;
		}
		
		catch(Exception e)
		{
			throw new Exception("Failed to rename Task :" + e.getMessage());
		}
	}
	

	private Task generateTask(ResultSet resultSet) throws Exception {
		String projectID  = resultSet.getString("projectID");
		String taskID = resultSet.getString("taskID");
		Boolean isCompleted = resultSet.getBoolean("isCompleted");
		Boolean isSubdivided = resultSet.getBoolean("isSubdivided");

		String taskOutlineNumber=resultSet.getString("taskOutlineNumber");
		return new Task(projectID, taskID, isCompleted, isSubdivided,taskOutlineNumber);

	}
}