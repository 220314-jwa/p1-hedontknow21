package net.revature.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import net.revature.models.StoryPitch;
import net.revature.models.User;
import net.revature.services.ConnectionFactory;


public class StoryPitchDAOImpl implements StoryPitchDAO {
	//connection object, used to connect to the database
	Connection connection;
	
	// constructor, retrieve/get a connection from the connection factory
	public StoryPitchDAOImpl() {
		// calling the method that we made in ConnectionFactory:
		connection = ConnectionFactory.getConnection();
		
	}

	@Override
	// this method needs to insert the object into the database
	// so we need to connect to the database
	public int create(StoryPitch newObj) throws SQLException {
		// this stores our sql command, that we would normally write in DBeaver/command line
		String sql = "insert into story_pitch (id, tentative_title, exp_completion_date, length_type, one_sentence_blurb, description, status_id, role_id, genre_id)" +
		"values(default, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		// create a prepared statement
		PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		// set the fields
		preparedStatement.setString(1, newObj.getTenativeTitle());
		preparedStatement.setString(5, newObj.getDescription());
		preparedStatement.setString(3, newObj.getLengthType());
		preparedStatement.setString(4, newObj.getOneSentenceBlurb());
		preparedStatement.setDate(2, newObj.getExpCompletionDate());
		// instantiating the genre dao into the pitch
		GenreDAO genreDAO = DAOFactory.getGenreDAO();
		// passing in the genre dao string and returning as an id
		preparedStatement.setInt(7, genreDAO.getByGenreName(newObj.getGenre()));
		// instainting the role name dao into the pitch
		RoleNameDAO roleNameDAO = DAOFactory.getRoleNameDAO();
		// passing in the role name dao to return as an id
		preparedStatement.setInt(8, roleNameDAO.getByRoleName(newObj.getRole()));
		// shortcut for now
		int status_id;
		if (newObj.getStatus().equals( "unsubmitted")) {
			status_id = 1;
		}else {
			status_id = 2;
		}
		preparedStatement.setInt(6, status_id);
		// execute this command, return number of rows affected
		int count = preparedStatement.executeUpdate();
		// let us return the id that is auto-generated
		ResultSet resultSet = preparedStatement.getGeneratedKeys();
		// if we affected one or more rows:
		if(count > 0) {
		System.out.println("Pitch added");
		// return the generated id:
		//before we call resultSet.next(), its basically pointing to nothing
		// useful but moving that pointer allows us to get the information that we want
		resultSet.next();
		int id = resultSet.getInt(1);
		return id;
		}else {
			System.out.println("Something went wrong when trying to add a pitch");
			return -1;
		}
	}

	@Override
	public StoryPitch getById(int id) {
		// initialize our pitch object to be null:
		StoryPitch storyPitch = null;
		//placeholder for our final sql string
		// ? placeholder for our id:
		// * means all fields
		// but we specify an id so we only get one single pitch:
		String sql = "SELECT *  FROM story_pitch WHERE id = ?";
		try {
			PreparedStatement preparedStatment = connection.prepareStatement(sql);
			preparedStatment.setInt(1, id);
			ResultSet resultSet = preparedStatment.executeQuery();
			// if result set does'nt point to a next value something went wrong
			if(resultSet.next()) {
				storyPitch = new StoryPitch();
				// do something with the return value:
				storyPitch.setId( resultSet.getInt(1));
				storyPitch.setTenativeTitle(resultSet.getString(2));
				storyPitch.setLengthType(resultSet.getString(3));
				storyPitch.setDescription( resultSet.getString(4));
				storyPitch.setOneSentenceBlurb( resultSet.getString(5));
				storyPitch.setExpCompletionDate(resultSet.getDate(6));
				int status_id = resultSet.getInt(7);
				// get status from db:
				// ternary operator
				//				check the condition , if true,     if false
				
				String status = (status_id == 1) ? "unsubmitted" : "submitted";
				// exact same thing as this if condition
				if(status_id == 1) {
					status = "unsubmitted";
				}else {
					status = "submitted";
				}
				storyPitch.setStatus(status);
				// now, we've created a pitch Java object based on the info from our table
				return storyPitch;
				
				
				
			}else {
				System.out.println("Something went wrong when querying for a pitch");
				// return null in this case
				return storyPitch;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			return storyPitch;
		}
	}

	@Override
	public List<StoryPitch> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(StoryPitch updatedObj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(StoryPitch objToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<StoryPitch> getByRole(User roleName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StoryPitch> getByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
