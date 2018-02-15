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
			loadedUser.setPassword(rs.getString("password"));
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
			loadedUser.setPassword(rs.getString("password"));
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
			loadedUser.setPassword(rs.getString("password"));
			loadedUser.setEmail(rs.getString("email"));
			users.add(loadedUser);			
		}
		return users;
	}

}
