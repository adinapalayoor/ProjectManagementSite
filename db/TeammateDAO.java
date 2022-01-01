package com.nildespernadumcs3733.finalproject.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.nildespernadumcs3733.finalproject.model.Teammate;
import com.nildespernadumcs3733.finalproject.model.Assignments;
import com.nildespernadumcs3733.finalproject.model.Project;
import com.nildespernadumcs3733.finalproject.model.Task;

public class TeammateDAO {

	java.sql.Connection conn;

	final String tblName="Teammates";

	public TeammateDAO()
	{
		try  {
			conn = DatabaseUtil.connect();
		} catch (Exception e) {
			conn = null;
		}
	}

	public Teammate getTeammate(String name,String projectID) throws Exception {

		try {
			Teammate teammate = null;
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberID = ?"+" AND "+"projectID = ?;");
			ps.setString(1,  name); 
			ps.setNString(2, projectID);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				teammate = generateTeammate(resultSet);
			}
			resultSet.close();
			ps.close();

			return teammate;

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Failed in getting constant: " + e.getMessage());
		}
	}


	public List<Teammate> getAllTeammates(String projectName) throws Exception {

		List<Teammate> allTeammates = new ArrayList<>();
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE projectID = ?;");
			ps.setString(1, projectName);
			ResultSet resultSet = ps.executeQuery();

			while (resultSet.next()) {
				Teammate c = generateTeammate(resultSet);
				allTeammates.add(c);
			}
			resultSet.close();
			ps.close();
			return allTeammates;

		} catch (Exception e) {
			throw new Exception("Failed in getting constants: " + e.getMessage());
		}
	}

	public boolean addTeammate(String teammate,String projectID) throws Exception {
		try {

			PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + tblName + " WHERE memberID = ?"+" AND "+"projectID = ?;");
			ps.setString(1, teammate);
			ps.setString(2,projectID);
			ResultSet resultSet = ps.executeQuery();

			// already present?
			while (resultSet.next()) {
				Teammate c = generateTeammate(resultSet);
				resultSet.close();
				return false;
			}


			ps = conn.prepareStatement("INSERT INTO " + tblName + " (memberID, projectID) values(?,?);");

			ps.setString(1,teammate);
			ps.setString(2, projectID);


			ps.execute();
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to insert teammate: " + e.getMessage());
		}}

	public boolean removeTeammate(Teammate teammate) throws Exception {
		try {
			PreparedStatement ps = conn.prepareStatement("DELETE FROM " + tblName + " WHERE memberID = ?"+" AND "+ "projectID = ?;");
			ps.setString(1,teammate.getmemberID());
			ps.setString(2,teammate.getprojectID());


			int numAffected = ps.executeUpdate();
			ps.close();

			return (numAffected == 1);
		} catch (Exception e) {
			throw new Exception("Failed to remove teammate: " + e.getMessage());
		}}


	private Teammate generateTeammate(ResultSet resultSet) throws Exception {
		String memberID  = resultSet.getString("memberID");
		String projectID = resultSet.getString("projectID");
		return new Teammate(memberID, projectID);
	}
	

}
