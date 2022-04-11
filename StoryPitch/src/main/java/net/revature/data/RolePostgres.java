package net.revature.data;


import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.LinkedList;
import net.revature.utils.ConnectionFactory;
import net.revature.data.DAOFactory;
import net.revature.models.Role;


public class RolePostgres implements RoleDAO{
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

	public int  getByRoleName(String role_name) {
		Role role = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from user_role where user_role.id = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			// passing in a string to get the int value from db
			prepStatement.setString(1, role_name);

			ResultSet resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {
				
				
				resultSet.getInt("id");
				return resultSet.getInt("id");

				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getByRoleId(int role_id) {
		Role role = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from user_role where user_role.id = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setInt(1, role_id);

			ResultSet resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {
				resultSet.getString("id");
				return resultSet.getString("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "";
	}
}
