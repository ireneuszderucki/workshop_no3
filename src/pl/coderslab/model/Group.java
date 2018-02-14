package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Group {
	private int id;
	private String name;
	
	/**
	 * Class constructor
	 * @param name
	 */
	
	public Group(String name) {
		this.name = name;
	}
	
	/**
	 * Class constructor with no params
	 */
	
	public Group() {}
	
	public String toString() {
		return "ID: '" + id +"'\n" + "User_group name: '" + name + "'";
	}
	
	public int getId() {
		return id;
	}
	
	public Group setId (int id) {
		this.id = id;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Group setName(String name) {
		this.name = name;
		return this;
	}
	
	/**
	 * saves or updates user_group to database
	 * @param conn
	 * @throws SQLException
	 */
	public void saveGroupToDB(Connection conn) throws SQLException {
		if (this.id == 0) {
			String sql = "INSERT INTO user_group (name) VALUES (?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setString(1, this.name);
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				this.id = rs.getInt(1);
			}
		}
		else {
			String sql = "UPDATE user_group SET name=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, this.name);
			preStm.setInt(2, this.id);
			preStm.executeUpdate();
		}
	} 
	
	/**
	 * deletes user_group from database (by id)
	 * @param conn
	 * @throws SQLException
	 */
	
	public void deleteGroup(Connection conn) throws SQLException {
		if (this.id != 0) {
			String sql = "DELETE FROM user_group where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setInt(1, this.id);
			preStm.executeUpdate();
			this.id = 0;
		}
	}
	
	/**
	 * loads a single user_group from database (by id) and creates Group object
	 * @param conn
	 * @param id
	 * @return Group object / null
	 * @throws SQLException
	 */
	static public Group loadGroupById(Connection conn, int id) throws SQLException {
		String sql = "SELECT * FROM user_group where id=?";
		PreparedStatement preStm = conn.prepareStatement(sql);
		preStm.setInt(1, id);
		ResultSet rs = preStm.executeQuery();
		if (rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.id = rs.getInt("id");
			loadedGroup.name = rs.getString("name");
			return loadedGroup;
		}
		return null;
	}
	

	

}
