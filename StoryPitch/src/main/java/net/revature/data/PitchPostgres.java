package net.revature.data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


import net.revature.models.Pitch;
import net.revature.models.User;
import net.revature.utils.ConnectionFactory;
import net.revature.data.DAOFactory;


public class PitchPostgres implements PitchDAO { 
	//connection object, used to connect to the database
	private static ConnectionFactory connFactory = ConnectionFactory.getConnectionFactory();

		



public int create(Pitch newObj) throws SQLException {
	// this method needs to insert the object into the database
	// so we need to connect to the database
	
		Connection	connection = connFactory.getConnection();
		
		// this stores our sql command, that we would normally write in DBeaver/command line
		String sql = "insert into story_pitch (id, users_id, tentative_title, exp_completion_date, length_type, one_sentence_blurb, description, status_id, role_id, genre_id)" +
		"values(default,?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
		// create a prepared statement
		PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
		// set the fields
		preparedStatement.setString(2, newObj.getTenativeTitle());
		preparedStatement.setString(6, newObj.getDescription());
		preparedStatement.setString(4, newObj.getLengthType());
		preparedStatement.setString(5, newObj.getOneSentenceBlurb());
		preparedStatement.setDate(3, newObj.getExpCompletionDate());
		// instantiating the genre dao into the pitch
		GenreDAO genreDAO = DAOFactory.getGenreDAO();
		// passing in the genre dao string and returning as an id
		preparedStatement.setInt(8, genreDAO.getByGenreName(newObj.getGenre()));
		// instainting the role name dao into the pitch
		RoleDAO roleNameDAO = DAOFactory.getRoleDAO();
		// passing in the role name dao to return as an id
		preparedStatement.setInt(9, roleNameDAO.getByRoleName(newObj.getRole()));
		// instantiating the status dao in the pitch
		StatusDAO statusDAO = DAOFactory.getStatusDAO();
		//passing in the status by id
		preparedStatement.setInt(7, statusDAO.getByStatusName(newObj.getStatus()));
		//instantiating the user into the pitch
		// passing in the users id
		preparedStatement.setInt(1, newObj.getUsersId());
		
		
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
		newObj.setId(id);
		connection.commit(); // commit the changes to the DB
		// if 0 rows are affected, something went wrong:
		}else {
			System.out.println("Something went wrong when trying to add a pitch");
			connection.rollback(); 
		}
	}	catch (SQLException e){
        // print out what went wrong:
        e.printStackTrace();
        try {
        	connection.rollback();
            
        } catch (SQLException e1) {
			e1.printStackTrace();
		}
	}	finally {
    	try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		return newObj.getId();
	
}

public Pitch getById(int id) {
	// initialize our pitch object to be null:
			Pitch storyPitch = null;
			//placeholder for our final sql string
			// ? placeholder for our id:
			// * means all fields
			// but we specify an id so we only get one single pitch:
			String sql = "SELECT *  FROM story_pitch WHERE id = ?";
			try (Connection connection = connFactory.getConnection()){
				PreparedStatement prepStatement = connection.prepareStatement(sql);
				prepStatement.setInt(1, id);
				ResultSet resultSet = prepStatement.executeQuery();
				// if result set does'nt point to a next value something went wrong
				if(resultSet.next()) {
					storyPitch = parseResultSet(resultSet);
				}else {
					System.out.println("Something went wrong when querying the pitch");
				}
					}catch(SQLException e){
						e.printStackTrace();
					}
			return storyPitch;
				
}




public List<Pitch> getAll() {
	List<Pitch> pitches = new ArrayList<Pitch>();
	
	String sql = "SELECT * from story_pitch";
	
	try(Connection connection = connFactory.getConnection()){
		PreparedStatement prepStatement = connection.prepareStatement(sql);
		ResultSet resultSet = prepStatement.executeQuery();
		
		while(resultSet.next()) {
			Pitch pitch = parseResultSet(resultSet);
			pitches.add(pitch);
		}
	}catch(SQLException e) {
		e.printStackTrace();
	}
	
	return pitches;
}
	private Pitch parseResultSet(ResultSet resultSet) throws SQLException {
		Pitch pitch = new Pitch();
		
		pitch.setId(resultSet.getInt("id"));
		pitch.setTenativeTitle(resultSet.getString("tentative_title"));
		pitch.setExpCompletionDate(resultSet.getDate("exp_completion_date"));
		pitch.setLengthType(resultSet.getString("length_type"));
		pitch.setOneSentenceBlurb(resultSet.getString("one_sentence_blurb"));
		pitch.setDescription(resultSet.getString("description"));
		// instantiate the neccassary DAOS to get an id from a string
		StatusDAO statusDAO = DAOFactory.getStatusDAO();
		RoleDAO roleNameDAO = DAOFactory.getRoleDAO();
		GenreDAO genreDAO = DAOFactory.getGenreDAO();
		// Now use the DAOS to parse the string into ints
		pitch.setStatus(statusDAO.getByStatusId(resultSet.getInt("status_id")) );
		pitch.setRole(roleNameDAO.getByRoleId(resultSet.getInt("role_id")));
		pitch.setGenre(genreDAO.getByGenreId(resultSet.getInt("genre_id")));
		
		return pitch;
	}


public void update(Pitch updatedObj) {
	Connection connection = connFactory.getConnection();
	// we create the template for the SQL string:
	String sql = "update story_pitch set tentative_title = ?, exp_completion_date = ?, length_type = ?, one_sentence_blurb = ?"
			+ "description = ?,status_id = ?, role_id = ?,genre_id = ? where id = ?;";
	try {
    	PreparedStatement preparedStatement = connection.prepareStatement(sql);
    	// fill in the template:
    	preparedStatement.setString(1,updatedObj.getTenativeTitle());
    	preparedStatement.setDate(2,updatedObj.getExpCompletionDate());
    	preparedStatement.setString(3, updatedObj.getLengthType());
    	preparedStatement.setString(4,  updatedObj.getOneSentenceBlurb());
    	preparedStatement.setString(5, updatedObj.getDescription());        	
    	preparedStatement.setString(6, updatedObj.getStatus());
    	preparedStatement.setString(7, updatedObj.getRole());
    	preparedStatement.setString(8, updatedObj.getGenre());
    	preparedStatement.setInt(9, updatedObj.getId());
    	
    	connection.setAutoCommit(false);
    	// return a count of how many records were updated
    	int count = preparedStatement.executeUpdate();
    	if(count != 1) {
    		System.out.println("Oops! Something went wrong with the update!");
    		connection.rollback();
    	} else connection.commit();
    	
		
	} catch(SQLException e) {
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	
}

public void delete(Pitch objToDelete) {
	Connection connection = connFactory.getConnection();
	
	String sql = "delete from story_pitch where id = ?;";
	try {
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, objToDelete.getId());
		
		connection.setAutoCommit(false);
		int count = preparedStatement.executeUpdate();
		if (count != 1) {
			System.out.println("Something went wrong with the deletion!");
			connection.rollback();
		} else connection.commit();
	} catch (SQLException e) {
		e.printStackTrace();
		try {
			connection.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	} finally {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

public List<Pitch> getByRole(User role) {
	List<Pitch> pitches = new LinkedList<>();
	try (Connection conn = connFactory.getConnection()) {
		String sql = "select * from story_pitch where role_id=?";
		PreparedStatement prepStatement = conn.prepareStatement(sql);
		
		prepStatement.setString(8, role.toString());
		
		ResultSet resultSet = prepStatement.executeQuery();
		while (resultSet.next()) {
			Pitch pitch = parseResultSet(resultSet);
			pitches.add(pitch);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
    return pitches;
}

public List<Pitch> getByStatus(String status) {
	List<Pitch> pitches = new LinkedList<>();
	try (Connection conn = connFactory.getConnection()) {
		String sql = "select * from story_pitch where status_id=?";
		PreparedStatement prepStatement = conn.prepareStatement(sql);
		
		prepStatement.setString(7, status);
		
		ResultSet resultSet = prepStatement.executeQuery();
		while (resultSet.next()) {
			Pitch pitch = parseResultSet(resultSet);
			pitches.add(pitch);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
    return pitches;
}

@Override
public List<Pitch> getByPitch(User user) {
	List<Pitch> pitches = new LinkedList<>();
	try (Connection conn = connFactory.getConnection()) {
		String sql = "select * from users join user_pitches on users.id=user_pitches.users_id"
				+ " where user_pitches.users_id=?";
		PreparedStatement pStmt = conn.prepareStatement(sql);
		pStmt.setInt(1, user.getId());
		
		ResultSet resultSet = pStmt.executeQuery();
		while (resultSet.next()) {
			Pitch pitch = parseResultSet(resultSet);
			pitches.add(pitch);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
    return pitches;
}
}

