package net.revature.services;

import java.sql.SQLException;
import java.util.List;

import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.models.StoryPitch;
import net.revature.models.User;

/*- here we can lay out all of the behaviors that we want
 * users to be able to do. services are just about the
 * services, or tasks, that we want to provide to users,
 * and the Java that makes those methods work.
 * 
 * it also allows for a separation between database code (DAOs),
 * HTTP handling code (Javalin), and the "business logic" or actual
 * functionality that users are doing (services). this idea is called
 * "separation of concerns" and allows you to have cleaner, more
 * organized, and more maintainable code.
 */

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

	public User submittedPitch(User user, StoryPitch storyToPitch) throws AlreadySubmittedException;

	/**
	 * 
	 * @param id
	 * @return the pet with the specified ID
	 */
	public StoryPitch getPitchtById(int id);
	/**
	 * 
	 */
	public List<StoryPitch> viewSubmittedPitches();
	/**
	 * 
	 */
	public List<StoryPitch> searchPitchByStatus(String status);
	/**
	 * 
	 */

	
}
