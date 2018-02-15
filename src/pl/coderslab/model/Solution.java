package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Solution {
	private int id;
	private int exercise;
	private int user;
	private String created;
	private String updated;
	private String description;
	
	/**
	 * Class constructor
	 * @param exercise
	 * @param user
	 * @param created
	 * @param updated
	 * @param description
	 */
	
	public Solution(int exercise, int user, String created, String updated, String description) {
		this.exercise = exercise;
		this.user = user;
		this.created = created;
		this.updated = updated;
		this.description = description;
	}
	
	/** 
	 * Class constructor with no paramss
	 */
	
	public Solution() {}
	
	public String toString() {
		return "ID: '" + id + "'\n" + "Exercise: '" + exercise + "'\n" + "User: '" + user + "'\n" + "Created: '" + created + "'\n" + "Updated: '" + updated + "'\n" + "Description: '" + description + "'";
	}
	
	public int getId() {
		return id;
	}
	
	public Solution setId(int id) {
		this.id = id;
		return this;
	}
	
	
	public int getExercise() {
		return exercise;
	}

	public Solution setExercise(int exercise) {
		this.exercise = exercise;
		return this;
	}

	public int getUser() {
		return user;
	}

	public Solution setUser(int user) {
		this.user = user;
		return this;
	}

	public String getCreated() {
		return created;
	}
	
	public Solution setCreated(String created) {
		this.created = created;
		return this;
	}
	
	public String getUpdated() {
		return updated;
	}
	
	public Solution setUpdated(String updated) {
		this.updated = updated;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Solution setDescription (String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * saves or updates a single solution in database
	 * @param conn
	 * @throws SQLException
	 */
	
	public void saveSolutionToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO solution (exercise_id, users_id, created, updated, description) VALUES (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setInt(1, this.exercise);
			preStm.setInt(2, this.user);
			preStm.setString(3, this.created);
			preStm.setString(4, this.updated);
			preStm.setString(5, this.description);
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}
		else {
			String sql = "UPDATE solution SET exercise_id=?, users_id=?, created=?, updated=?, description=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.exercise);
			preStm.setInt(2, this.user);
			preStm.setString(3, this.created);
			preStm.setString(4, this.updated);
			preStm.setString(5, this.description);
			preStm.setInt(6, this.id);
			preStm.executeUpdate();
		}
	} 
	
	/**
	 * deletes a single solution from database (by id)
	 * @param conn
	 * @throws SQLException
	 */
	
	public void deleteSolution(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM solution where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.id);
			preStm.executeUpdate();
			this.id = 0;
		}
	}
			
	/**
	 * loads all solutions from database and creates an array of Solution objects
	 * @param conn
	 * @return Solution[] array
	 * @throws SQLException
	 */
	
	static public Solution[] loadAllSolutions(Connection conn) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "SELECT * FROM solution";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.id = rs.getInt("id");
			loadedSolution.exercise = rs.getInt("exercise_id");
			loadedSolution.user = rs.getInt("users_id");
			loadedSolution.created = rs.getString("created");
			loadedSolution.updated = rs.getString("updated");
			loadedSolution.description = rs.getString("description");
			solutions.add(loadedSolution);			
		}
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}
	
	/**
	 * loads all solutions by exercise id, sorted from the newest to the oldest 
	 * @param conn
	 * @param exercise id
	 * @return Solution[] array
	 * @throws SQLException
	 */
	
	static public Solution[] loadAllSolutionsByExerciseId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> sortedSolutions = new ArrayList<Solution>();
		String sql = "select * from solution inner join exercise on solution.exercise_id=exercise.id where exercise.id=? order by created desc";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setExercise(rs.getInt("exercise_id"));
			loadedSolution.setUser(rs.getInt("users_id"));
			loadedSolution.setCreated(rs.getString("created"));
			loadedSolution.setUpdated(rs.getString("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			sortedSolutions.add(loadedSolution);
		}
		Solution[] uArray = new Solution[sortedSolutions.size()];
		uArray = sortedSolutions.toArray(uArray);
		return uArray;
	}
	
	
				

	

}
