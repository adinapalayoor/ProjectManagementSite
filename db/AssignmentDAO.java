package com.nildespernadumcs3733.finalproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;
import com.nildespernadumcs3733.finalproject.model.Teammate;
import com.nildespernadumcs3733.finalproject.model.Assignments;

public class AssignmentDAO {

	java.sql.Connection conn;

	final String tblName="Assignments";

	public AssignmentDAO()
	{
		try  {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public Assignments getAssignment(String projectID, String taskID, String memberID) throws Exception {

		try {
			Assignments assign2 = null;

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE taskID = ? "+ " AND "+ " projectID= ? "+" AND "+ " memberID=?; ");

			ps.setString(1, taskID);
			ps.setString(2, projectID);
			ps.setString(3,memberID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				assign2 = generateAssignments(resultSet);
			}
			resultSet.close();
			ps.close();

			return assign2;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting constant: " + e.getMessage());
		}
	}


	public List<Assignments> getAllAssignments(String projectName) throws Exception {

		List<Assignments> allAssignments = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName +" WHERE projectID = ?;");
			ps.setString(1, projectName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Assignments c = generateAssignments(resultSet);
				allAssignments.add(c);
			}
			resultSet.close();
			ps.close();
			return allAssignments;

		} catch (Exception e) {
			throw new Exception("Failed in getting constants: " + e.getMessage());
		}
		}

	public boolean assignTeammate(String taskID, String projectID, String memberID) throws Exception {
		try {

			PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tblName + " (memberID, projectID, taskID) values(?,?,?);");

			ps.setString(1,memberID);
			ps.setString(2, projectID);
			ps.setString(3,taskID);


			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to assign teammate: " + e.getMessage());
		}}
	
	public boolean unassignTeammate(String taskID, String projectID, String memberID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE taskID = ?"+ " AND "+ "projectID=?"+" AND "+ "memberID=?;");
			ps.setString(1,taskID);
			ps.setString(2, projectID);
			ps.setString(3, memberID);


			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to unassign teammate: " + e.getMessage());
		}}
	
	public boolean unassignTeammate2(String projectID, String memberID) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE projectID = ?"+" AND "+ "memberID=?;");

			ps.setString(1, projectID);
			ps.setString(2, memberID);


			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to unassign teammate: " + e.getMessage());
		}}
//	
//	public List<Assignments> getAllTasks(Assignments assign) throws Exception {
//
//		List<Assignments> allTasks = new ArrayList<>();
//		try {
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE taskID = ?;");
//			ps.setString(1, assign.taskID);
//			ResultSet resultSet = ps.executeQuery();
//
//			while (resultSet.next()) {
//				Assignments c = generateAssignments(resultSet);
//				allTasks.add(c);
//			}
//			resultSet.close();
//			ps.close();
//			return allTasks;
//
//		} catch (Exception e) {
//			throw new Exception("Failed in getting constants: " + e.getMessage());
//		}
//	}
//	
//	public List<Assignments> getAllTeammates(Assignments assign) throws Exception {
//
//		List<Assignments> allTeammates = new ArrayList<>();
//		try {
//			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberID = ?;");
//			ps.setString(1, assign.memberID);
//			ResultSet resultSet = ps.executeQuery();
//
//			while (resultSet.next()) {
//				Assignments c = generateAssignments(resultSet);
//				allTeammates.add(c);
//			}
//			resultSet.close();
//			ps.close();
//			return allTeammates;
//
//		} catch (Exception e) {
//			throw new Exception("Failed in getting constants: " + e.getMessage());
//		}
//	}

	private Assignments generateAssignments(ResultSet resultSet) throws Exception{
		String memberID  = resultSet.getString("memberID");
		String projectID = resultSet.getString("projectID");
		String taskID = resultSet.getString("taskID");
		return new Assignments(projectID,taskID,memberID);
	}
}