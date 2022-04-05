package net.revature.services;

import java.sql.SQLException;
import java.util.List;

import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.models.Pitch;
import net.revature.models.User;


public interface UserService {
	
	/**
	 * returns the User if username and password are correct. otherwise throws an
	 * IncorrectCredentialsException.
	 * 
	 * @param username
	 * @param password
	 * @return User matching the given username/password
	 */
	public User logIn(String username, String password) throws IncorrectCredentialsException;

	/**
	 * creates a new user. if the username is available, returns the new user with
	 * their database-generated ID. otherwise, throws a
	 * UsernameAlreadyExistsException.
	 * 
	 * @param newUser
	 * @return User with newly generated ID
	 * @throws SQLException 
	 */
	public User register(User newUser) throws UserNameAlreadyExistsException, SQLException;
	/**
	 * 
	 * @param user
	 * @param storyToPitch
	 * @return
	 * @throws AlreadySubmittedException
	 */

	public User submittedPitch(User user, Pitch storyToPitch) throws AlreadySubmittedException;

	/**
	 * 
	 * @param id
	 * @return the pitch with the specified ID
	 */
	public Pitch getPitchtById(int id);
	/**
	 * @param id
	 * @return the pitch with the specified id
	 */
	public List<Pitch> viewSubmittedPitches();
	/**
	 * @return the list of submitted pitches
	 */
	public List<Pitch> searchPitchByStatus(String status);
	/**
	 * @param status
	 * 
	 * @return the list pitches depending on their status name
	 */

}
