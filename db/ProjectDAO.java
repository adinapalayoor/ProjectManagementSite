package com.nildespernadumcs3733.finalproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nildespernadumcs3733.finalproject.model.Project;


public class ProjectDAO {

	java.sql.Connection conn;

	final String tblName="Projects";

	public ProjectDAO()
	{
		try  {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public Project getProject(String project) throws Exception {

		try {
			Project project2 = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name=?;");
			ps.setString(1, project);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				project2 = generateProject(resultSet);
			}
			resultSet.close();
			ps.close();

			return project2;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting constant: " + e.getMessage());
		}
	}


	public List<Project> getAllProjects() throws Exception {

		List<Project> allProjects = new ArrayList<>();
		try {
			Statement statement = conn.createStatement();
			String query = "SELECT * FROM " + tblName + ";";
			ResultSet resultSet = statement.executeQuery(query);

			while (resultSet.next()) {
				Project c = generateProject(resultSet);
				allProjects.add(c);
			}
			resultSet.close();
			statement.close();
			return allProjects;

		} catch (Exception e) {
			throw new Exception("Failed in getting constants: " + e.getMessage());
		}
	}

	public boolean deleteProject(Project project) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE name = ?;");
			ps.setString(1,project.getprojectName());
			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);

		} catch (Exception e) {
			throw new Exception("Failed to insert constant: " + e.getMessage());
		}
	}

	public boolean archiveProject(String name) throws Exception {

		try {
			PreparedStatement ps = conn.prepareStatement("UPDATE " + tblName +" SET isArchived= ? "+" WHERE name = ?; ");
			ps.setInt(1, 1);
			ps.setString(2, name);
			
			ps.executeUpdate();

			return true;

		} catch (Exception e) {
			throw new Exception("Failed to archive project: " + e.getMessage());
		}} 

	public boolean addProject(String project) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE name = ?;");
			ps.setString(1, project);
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				Project c = generateProject(resultSet);
				resultSet.close();
				return false;
			}

			ps = conn.prepareStatement("INSERT INTO " + tblName + " (name,id,isArchived) values(?,?,?);");
			ps.setString(1,  project);
			ps.setString(2,  project);
			ps.setInt(3, 0);
			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert project: " + e.getMessage());
		}}

	private Project generateProject(ResultSet resultSet) throws Exception {
		String name  = resultSet.getString("name");
		String id = resultSet.getString("id");
		Boolean isArchived = resultSet.getBoolean("isArchived");
		return new Project(name, id, isArchived);
	}
}
