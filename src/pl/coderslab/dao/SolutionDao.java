package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.Solution;

public class SolutionDao {
	
	/**
	 * loads the latest solutions from database LIMIT CONTEXT PARAM
	 * @param conn
	 * @param id
	 * @return ArrayList<Solution> solutions
	 * @throws SQLException
	 */
	static public ArrayList<Solution> loadAllSolutionsWithLimit(Connection conn, int id) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql ="SELECT * FROM solution ORDER BY updated DESC LIMIT ?";
		PreparedStatement preSt = conn.prepareStatement(sql);
		preSt.setInt(1, id);
		ResultSet rs = preSt.executeQuery();
		while (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setExercise(rs.getInt("exercise_id"));
			loadedSolution.setUser(rs.getInt("users_id"));
			loadedSolution.setCreated(rs.getString("created"));
			loadedSolution.setUpdated(rs.getString("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			solutions.add(loadedSolution);
		}
		return solutions;
	}
	
	/**
	 * loads a single solution from database (by id) and creates a Solution object
	 * @param conn
	 * @param id
	 * @return Solution object / null
	 * @throws SQLException
	 */
	
	static public Solution loadSolutionById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM solution where id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			Solution loadedSolution = new Solution();
			loadedSolution.setId(rs.getInt("id"));
			loadedSolution.setExercise(rs.getInt("exercise_id"));
			loadedSolution.setUser(rs.getInt("users_id"));
			loadedSolution.setCreated(rs.getString("created"));
			loadedSolution.setUpdated(rs.getString("updated"));
			loadedSolution.setDescription(rs.getString("description"));
			return loadedSolution;
		}
		return null;
	}
	
	/**
	 * loads all solutions assigned to a specific user
	 * @param conn
	 * @param id
	 * @return ArrayList<Solution> solutions
	 * @throws SQLException
	 */
	
	static public ArrayList<Solution> loadAllSolutionsByUserId(Connection conn, int id) throws SQLException {
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = "select * from solution inner join users on users.id=solution.users_id where users.id=?";
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
			solutions.add(loadedSolution);
		}
		return solutions;
	}
	
	
}
