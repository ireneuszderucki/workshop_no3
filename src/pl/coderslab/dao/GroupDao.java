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

}
