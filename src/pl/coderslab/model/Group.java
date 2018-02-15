package pl.coderslab.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	
	

	

}
