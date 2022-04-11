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
import net.revature.models.Status;


public class StatusPostgres implements StatusDAO{
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

	@Override
	public int getByStatusName(String status) {
		Status s = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from status where status_name.id = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			// passing in a string to get the int value from db
			prepStatement.setString(1, status);

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

	@Override
	public String getByStatusId(int status_id) {
		Status status = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from status where status.id = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setInt(1, status_id);

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
