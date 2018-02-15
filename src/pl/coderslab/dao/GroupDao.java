package pl.coderslab.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pl.coderslab.model.Group;

public class GroupDao {
	/**
	 * loads all user_groups from database and creates an ArrayList of Group objects
	 * @param conn
	 * @return ArrayList <Group> groups
	 * @throws SQLException
	 */
	
	static public ArrayList<Group> loadAllGroups(Connection conn) throws SQLException {
		ArrayList<Group> groups = new ArrayList<Group>();
		String sql = "SELECT * FROM user_group";
		PreparedStatement preStm = conn.prepareStatement(sql);
		ResultSet rs = preStm.executeQuery();
		while (rs.next()) {
			Group loadedGroup = new Group();
			loadedGroup.setId(rs.getInt("id"));
			loadedGroup.setName(rs.getString("name"));
			groups.add(loadedGroup);			
		}
		return groups;
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
			loadedGroup.setId(rs.getInt("id"));
			loadedGroup.setName(rs.getString("name"));
			return loadedGroup;
		}
		return null;
	}
	
	/**
	 * saves or updates user_group to database
	 * @param conn
	 * @throws SQLException
	 */
	static public void saveGroupToDB(Connection conn, Group group) throws SQLException {
		if (group.getId() == 0) {
			String sql = "INSERT INTO user_group (name) VALUES (?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement preStm = conn.prepareStatement(sql, generatedColumns);
			preStm.setString(1, group.getName());
			preStm.executeUpdate();
			ResultSet rs = preStm.getGeneratedKeys();
			if (rs.next()) {
				group.setId(rs.getInt(1));
			}
		}
		else {
			String sql = "UPDATE user_group SET name=? where id=?";
			PreparedStatement preStm = conn.prepareStatement(sql);
			preStm.setString(1, group.getName());
			preStm.setInt(2, group.getId());
			preStm.executeUpdate();
		}
	}

}
