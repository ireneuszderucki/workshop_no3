package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.Exercise;

public class ExerciseDao {
	/**
	 * loads all exercises from database and creates a list of Exercise objects
	 * @param conn
	 * @return ArrayList<Exercise> exercises
	 * @throws SQLException
	 */
	
	static public ArrayList<Exercise> loadAllExercises(Connection conn) throws SQLException {
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.setId(rs.getInt("id"));
			loadedExercise.setTitle(rs.getString("title"));
			loadedExercise.setDescription(rs.getString("description"));
			exercises.add(loadedExercise);			
		}
		return exercises;
	}
	
	/**
	 * saves or updates exercise to database
	 * @param conn
	 * @throws SQLException
	 */
	static public void saveExerciseToDB(Connection conn, Exercise exercise) throws SQLException {
		if (exercise.getId() == 0) {
			String sql = "INSERT INTO exercise (title, description) VALUES (?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setString(1, exercise.getTitle());
			preStm.setString(2, exercise.getDescription());
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				exercise.setId(rs.getInt(1));
			}
		}
		else {
			String sql = "UPDATE exercise SET title=?, description=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, exercise.getTitle());
			preStm.setString(2, exercise.getDescription());
			preStm.setInt(3, exercise.getId());
			preStm.executeUpdate();
		}
	}
	
	/**
	 * loads a single exercise from database (by id) and creates Exercise object
	 * @param conn
	 * @param id
	 * @return Exercise object / null
	 * @throws SQLException
	 */
	static public Exercise loadExerciseById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM exercise where id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.setId(rs.getInt("id"));
			loadedExercise.setTitle(rs.getString("title"));
			loadedExercise.setDescription(rs.getString("description"));
			return loadedExercise;
		}
		return null;
	}

}
