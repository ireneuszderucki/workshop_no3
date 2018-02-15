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

}
