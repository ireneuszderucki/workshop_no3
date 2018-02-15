package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.User;

public class UserDao {
	
	/**
	 * loads all (users) members of a single group
	 * @param conn
	 * @param id
	 * @return ArrayList<User> users
	 * @throws SQLException
	 */

	static public ArrayList<User> loadAllUsersByGroupId(Connection conn, int id) throws SQLException{
		ArrayList<User> users = new ArrayList<User>();
		String sql = "select * from users inner join user_group on users.user_group_id=user_group.id where user_group.id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			User loadedUser = new User();
			loadedUser.setId(rs.getInt("id"));
			loadedUser.setGroup(rs.getInt("user_group_id"));
			loadedUser.setUsername(rs.getString("username"));
			loadedUser.setHashedPassword(rs.getString("password"));
			loadedUser.setEmail(rs.getString("email"));
			users.add(loadedUser);
		}
		return users;
	}
	
	/**
	 * loads a single user from database (by id) and creates an User object
	 * @param conn
	 * @param id
	 * @return User object / null
	 * @throws SQLException
	 */
	
	static public User loadUserById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM users where id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			User loadedUser = new User();
			loadedUser.setId(rs.getInt("id"));
			loadedUser.setGroup(rs.getInt("user_group_id"));
			loadedUser.setUsername(rs.getString("username"));
			loadedUser.setHashedPassword(rs.getString("password"));
			loadedUser.setEmail(rs.getString("email"));
			return loadedUser;
		}
		return null;
	}
	
	/**
	 * loads all users from database and creates a list of User objects
	 * @param conn
	 * @return ArrayList<User> users
	 * @throws SQLException
	 */
	
	static public ArrayList<User> loadAllUsers(Connection conn) throws SQLException {
		ArrayList<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			User loadedUser = new User();
			loadedUser.setId(rs.getInt("id"));
			loadedUser.setGroup(rs.getInt("user_group_id"));
			loadedUser.setUsername(rs.getString("username"));
			loadedUser.setHashedPassword(rs.getString("password"));
			loadedUser.setEmail(rs.getString("email"));
			users.add(loadedUser);			
		}
		return users;
	}
	
	/**
	 * saves or updates single user data in database
	 * @param conn
	 * @throws SQLException
	 */
	static public void saveUserToDB(Connection conn, User user) throws SQLException {
		if (user.getId() == 0) {
			String sql = "INSERT INTO users (user_group_id, username, password, email) VALUES (?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setInt(1, user.getGroup());
			preStm.setString(2, user.getUsername());
			preStm.setString(3, user.getPassword());
			preStm.setString(4, user.getEmail());
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				user.setId(rs.getInt(1));
			}
		}
		else {
			String sql = "UPDATE users SET user_group_id=?, username=?, password=?, email=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, user.getGroup());
			preStm.setString(2, user.getUsername());
			preStm.setString(3, user.getPassword());
			preStm.setString(4, user.getEmail());
			preStm.setInt(5, user.getId());
			preStm.executeUpdate();
		}
	} 

}
