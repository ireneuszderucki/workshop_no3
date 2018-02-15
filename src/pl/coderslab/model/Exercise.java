package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Exercise {
	private int id;
	private String title;
	private String description;
	
	/**
	 * Class constructor
	 * @param title
	 * @param description
	 */
	
	public Exercise(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	/**
	 * Class constructor with no params
	 */
	
	public Exercise() {}
	
	public String toString() {
		return "ID: '" + id + "'\n" + "Title: '" + title + "'\n" + "Description: '" + description + "'";
	}
	
	public int getId() {
		return id;
	}
	
	public Exercise setId(int id) {
		this.id = id;
		return this;
	}
	
	public String getTitle() {
		return title;
	}
	
	public Exercise setTitle(String title) {
		this.title = title;
		return this;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Exercise setDescription(String description) {
		this.description = description;
		return this;
	}
	
	/**
	 * saves or updates exercise to database
	 * @param conn
	 * @throws SQLException
	 */
	public void saveExerciseToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO exercise (title, description) VALUES (?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setString(1, this.title);
			preStm.setString(2, this.description);
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}
		else {
			String sql = "UPDATE exercise SET title=?, description=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, this.title);
			preStm.setString(2, this.description);
			preStm.setInt(3, this.id);
			preStm.executeUpdate();
		}
	} 
	
	/**
	 * deletes exercise from database (by id)
	 * @param conn
	 * @throws SQLException
	 */
	
	public void deleteExercise(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM exercise where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.id);
			preStm.executeUpdate();
			this.id = 0;
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
			loadedExercise.id = rs.getInt("id");
			loadedExercise.title = rs.getString("title");
			loadedExercise.description = rs.getString("description");
			return loadedExercise;
		}
		return null;
	}

	
	/**
	 * loads all exercises from database and creates an array of Exercise objects
	 * @param conn
	 * @return Exercise[] array
	 * @throws SQLException
	 */
	
	static public Exercise[] loadAllExercises(Connection conn) throws SQLException {
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = "SELECT * FROM exercise";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.id = rs.getInt("id");
			loadedExercise.title = rs.getString("title");
			loadedExercise.description = rs.getString("description");
			exercises.add(loadedExercise);			
		}
		Exercise[] uArray = new Exercise[exercises.size()];
		uArray = exercises.toArray(uArray);
		return uArray;
	}
	
	/**
	 * loads all solutions from database (by User id)
	 * @param conn
	 * @param id
	 * @return Solution[] array
	 * @throws SQLException
	 */
	
	static public Solution[] loadAllSolutionsByUserId(Connection conn, int id) throws SQLException{
		ArrayList<Solution> solutions = new ArrayList<Solution>();
		String sql = 	"select * from solution inner join exercise on exercise.id=solution.exercise_id inner join"
						+ " users on solution.users_id=users.id where users.id=?";
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
		Solution[] uArray = new Solution[solutions.size()];
		uArray = solutions.toArray(uArray);
		return uArray;
	}
	
	static public ArrayList<Exercise> loadAllExercisesByUserId(Connection conn, int id) throws SQLException{
		ArrayList<Exercise> exercises = new ArrayList<Exercise>();
		String sql = 	"select * from exercise"
						+ " inner join solution on exercise.id=solution.exercise_id"
						+ " inner join users on solution.users_id=users.id where users.id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		while(rs.next()) {
			Exercise loadedExercise = new Exercise();
			loadedExercise.setId(rs.getInt("exercise.id"));
			loadedExercise.setTitle(rs.getString("title"));
			loadedExercise.setDescription(rs.getString("description"));
			exercises.add(loadedExercise);
		}
		return exercises;
	}
	
}
