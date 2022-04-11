package net.revature.services;

//static import of the Assertions methods
//this allows us to call the methods like this:
//assertTrue(result);
//instead of this:
//Assertions.assertTrue(result);
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.User;
import net.revature.models.Pitch;


public class UserServiceTest {
	//Create a field for the class we are testing
	private UserService userService = new UserServiceImpl();
	
	// create a test method that is void and always has no parameters
	@Test
	public void exampleTest() {
		assertTrue(true);
	}
	
	// Testing the username and password and giving correct credentials
	@Test
	public void logInCorrectly() throws IncorrectCredentialsException {
		// have to setup with parameters, arguments , expected results
		String username = "avery";
		String password = "pass";
		
		
		// have to call upon the method that we are trying to test
		User result = userService.logIn(username, password);
		
		
		// using the assertions to test the input
		assertEquals(username, result.getUserName());
	}
	
	// testing username and password but passing incorrect values
	@Test
	public void logInWrongUsername() {
		String username = "agbuiij";
		String password = "367682";
		
		assertThrows(IncorrectCredentialsException.class, () ->{
			// this where we place the code that is expected to throw the exception
			userService.logIn(username, password);
		});
		
	}
	
	// testing the wrong password of the user
	public void logInWrongPassword() {
		String username = "avery";
		String password = "9786796";
		
		assertThrows(IncorrectCredentialsException.class, () ->{
			// this where we place the code that is expected to throw the exception(same as wrong username)
			userService.logIn(username, password);
		});
	}
	
	// registering the user correctly
	@Test
	public void registerUserSuccessfully() throws UserNameAlreadyExistsException {
		User newUser = new User();
		
		User result = userService.register(newUser);
		

		// the behavior that i'm looking for is that the
		// method returns the User with their newly generated ID,
		// so i want to make sure the ID was generated (not the default)
		
		assertNotEquals(1, result.getId());
	}
	
	// seeing the username is take but passing in one that already exist to test if it actuallt take in the program
	@Test
	public void registerUsernameTaken() {
		User newUser = new User();
		newUser.setUserName("avery");
		
		assertThrows(UserNameAlreadyExistsException.class, () ->{
			userService.register(newUser);
		});
		
		
	}
	
	
	// seeing all the pitches in the list
	@Test
	public void viewPitchesSuccessfully() {
		List<Pitch> pitches = userService.viewSubmittedPitches();
		
		
		// i just want to make sure that the pitches are returned -
				//  don't need to check that the pitches are all available
				// because that filtering happens in the database. i just
				// need to check that the pitches list isn't null
		assertNotNull(pitches); // -checks the object of the pitches list to see if it is not null
	}
	
	
	@Test
	public void checkByStatus() {
		String status = "submitted";
		List<Pitch> pitchByStatus = userService.searchPitchByStatus(status);
		
		boolean onlySubmitsList = true;
		for(Pitch pitch :pitchByStatus) {
			String pitchStatus = pitch.getStatus().toLowerCase();
			if(!pitchStatus.contains(status)) {
				// then set boolean to false
				onlySubmitsList = false;
				// the stop the loop because the status is not available
				break;
			}
		}
	}
	@Test
	public void submitPitchSuccessfully() throws Exception {
		// instantiate both objects to test this method
		User testUser = new User();
		Pitch testPitch = new Pitch();
		
		User result = userService.submittedPitch(testUser, testPitch);
		
		// there are two behaviors i'm looking for:
				// that the user now has the pitch in their list of pitches,
				// and that the pitch in the list has its status updated.
				// to check this, i'm checking that the pitch with the Submitted
				// status is in the user's list.
		
		testPitch.setStatus("Submitted");
		List<Pitch> userPitches = result.getPitches();
		assertTrue(userPitches.contains(testPitch));
		
		
		
		
	}
	@Test
	public void pitchAlreadySubmitted() {
		//check to see if the pitch is already submitted
		User testUser = new User();
		Pitch testPitch = new Pitch();
		
		testPitch.setStatus("Submitted");
		
		assertThrows(Exception.class, () ->{
			userService.submittedPitch(testUser, testPitch);
		});
	}
	
	

}
