package net.revature.data;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.LinkedList;

import net.revature.models.Role;
import net.revature.models.User;

import net.revature.utils.ConnectionFactory;
import net.revature.data.DAOFactory;
import net.revature.data.RoleDAO;

public class UserPostgres implements UserDAO {
	// get the connection to database
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

	@Override
	public int create(User newObj) throws SQLException {

		Connection conn = connFactory.getConnection();
		try {
			String sql = "insert into users (id, user_name, password, first_name, last_name, role)" + " values (?,?,?,?,?)";
			PreparedStatement prepStatement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			//Passing in the sql statement of the  users table in order of column entries
			prepStatement.setInt(1, newObj.getId());
			prepStatement.setString(4, newObj.getFirstName());
			prepStatement.setString(5, newObj.getLastName());
			prepStatement.setString(2, newObj.getUserName());
			prepStatement.setString(3, newObj.getPassWord());
			prepStatement.setString(6, newObj.getRole().getRoleName());

			conn.setAutoCommit(false); // for ACID (transaction management)
			prepStatement.executeUpdate();
			ResultSet resultSet = prepStatement.getGeneratedKeys();

			if (resultSet.next()) {
				newObj.setId(resultSet.getInt(1));
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return newObj.getId();
	}

	@Override
	public User getById(int id) {
		User user = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from users join user_pitches on users_id.id=user_pitches.users_id"
					+ " where users.id = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setInt(1, id);

			ResultSet resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(id);

				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPassWord(resultSet.getString("password"));
				

				PitchDAO pitchDAO = DAOFactory.getPitchDAO();
				user.setPitches(pitchDAO.getByPitch(user));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<User> getAll() {
		List<User> users = new LinkedList<>();
		try (Connection conn = connFactory.getConnection()) {
			// left join because we want ALL the people even if they don't have any pitches.
			// a full join would be fine too since everything in the user_pitches table
			// will have a user associated with it, but a left join makes more sense
			// logically
			String sql = "select * from users left join user_pitches on user.id=users.users_id";
			Statement statment = conn.createStatement();

			ResultSet resultSet = statment.executeQuery(sql);
			while (resultSet.next()) {
				User user = new User();
				Role role = new Role();
				
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setUserName(resultSet.getString("user_name"));
				user.setPassWord(resultSet.getString("password"));
				role.setRoleName(resultSet.getString("role"));

				PitchDAO pitchDAO = DAOFactory.getPitchDAO();
				user.setPitches(pitchDAO.getByPitch(user));
				;

				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public void update(User updatedObj) {
		Connection conn = connFactory.getConnection();
		try {
			String sql = "update users set  username=?, password=?, first_name=?, last_name=?, role?" 
					+ "where id=?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(3, updatedObj.getFirstName());
			prepStatement.setString(4, updatedObj.getLastName());
			prepStatement.setString(1, updatedObj.getUserName());
			prepStatement.setString(2, updatedObj.getPassWord());
			prepStatement.setString(5, updatedObj.getRole().getRoleName());
			prepStatement.setInt(6, updatedObj.getId());

			conn.setAutoCommit(false); // for ACID (transaction management)
			int rowsUpdated = prepStatement.executeUpdate();

			if (rowsUpdated <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public void delete(User objToDelete) {
		Connection conn = connFactory.getConnection();
		try {
			String sql = "delete from users where id=?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setInt(1, objToDelete.getId());

			conn.setAutoCommit(false); // for ACID (transaction management)
			int rowsUpdated = prepStatement.executeUpdate();

			if (rowsUpdated <= 1) {
				conn.commit();
			} else {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public User getByUsername(String username) {
		User user = null;
		try (Connection conn = connFactory.getConnection()) {
			String sql = "select * from users join user_pitches on users.id=users_pitches.users_id"
					+ " where users.user_name = ?";
			PreparedStatement prepStatement = conn.prepareStatement(sql);
			prepStatement.setString(1, username);

			ResultSet resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setUserName(username);
				user.setPassWord(resultSet.getString("password"));

				PitchDAO pitchDAO = DAOFactory.getPitchDAO();
				user.setPitches(pitchDAO.getByPitch(user));
				;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}
