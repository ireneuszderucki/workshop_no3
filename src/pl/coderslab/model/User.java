package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class User {
	private int id;
	private int group;
	private String username;
	private String password;
	private String email;
	
	
	/**
	 * Class constructor
	 * @param user_group_id
	 * @param username
	 * @param password
	 * @param email
	 */
	
	public User(int user_group_id, String username, String password, String email) {
		this.group = user_group_id;
		this.username = username;
		this.setPassword(password);
		this.email = email;
	}
	
	/**
	 * Class constructor with no params
	 */
	public User() {}
	
	public String toString() {
		return "ID: '" + id + "'\n" + "user_group_id: '" + group+ "'\n" + "Username: '" + username + "'\n" + "Password: '" + password + "'\n" + "Email: '" + email + "'";
	}
	
	public int getId() {
		return id;
	}
	
	public User setId(int id) {
		this.id = id;
		return this;
		
	}
	
	public int getGroup() {
		return group;
	}
	
	public User setGroup(int user_group_id) { //not sure if necessary?
		this.group = user_group_id;
		return this;
	}
	
	public String getUsername() {
		return username;
	}
	
	public User setUsername(String username) {
		this.username = username;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public User setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	/***
	 * hashes the password using BCrypt
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	/**
	 * saves or updates single user data in database
	 * @param conn
	 * @throws SQLException
	 */
	public void saveUserToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO users (user_group_id, username, password, email) VALUES (?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setInt(1, this.group);
			preStm.setString(2, this.username);
			preStm.setString(3, this.password);
			preStm.setString(4, this.email);
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}
		else {
			String sql = "UPDATE users SET user_group_id=?, username=?, password=?, email=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.group);
			preStm.setString(2, this.username);
			preStm.setString(3, this.password);
			preStm.setString(4, this.email);
			preStm.setInt(5, this.id);
			preStm.executeUpdate();
		}
	} 
	
	/**
	 * deletes a single user from database (by id)
	 * @param conn
	 * @throws SQLException
	 */
	
	public void deleteUser(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM users where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.id);
			preStm.executeUpdate();
			this.id = 0;
		}
	}
		
	/**
	 * loads a single user from database (by unique email) and creates an User object
	 * @param conn
	 * @param email
	 * @return User objects / null
	 * @throws SQLException
	 */
	
	static public User loadUserByEmail(Connection conn, String email) throws SQLException {
		String sql = "SELECT * FROM users where email=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setString(1, email);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			User loadedUser = new User();
			loadedUser.id = rs.getInt("id");
			loadedUser.group = rs.getInt("user_group_id");
			loadedUser.username = rs.getString("username");
			loadedUser.password = rs.getString("password");
			loadedUser.email = rs.getString("email");
			return loadedUser;
		}
		return null;
	}



}
