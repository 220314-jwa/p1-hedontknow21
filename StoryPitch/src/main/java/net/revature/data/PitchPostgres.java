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
		String sql = "insert into storypitch (id, tentative_title, exp_completion_date, length_type, one_sentence_blurb, description, status_id, role_id, genre_id)" +
		"values(default, ?, ?, ?, ?, ?, ?, ?, ?)";
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
	// TODO Auto-generated method stub
	return null;
}

public List<Pitch> getAll() {
	// TODO Auto-generated method stub
	return null;
}

public void update(Pitch updatedObj) {
	// TODO Auto-generated method stub
	
}

public void delete(Pitch objToDelete) {
	// TODO Auto-generated method stub
	
}

public List<Pitch> getByRole(User role) {
	// TODO Auto-generated method stub
	return null;
}

public List<Pitch> getByStatus(String status) {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<Pitch> getByPitch(User user) {
	
	return null;
}
}
