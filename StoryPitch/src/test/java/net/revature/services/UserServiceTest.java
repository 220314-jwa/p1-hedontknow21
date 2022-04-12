package net.revature.services;

//static import of the Assertions methods
//this allows us to call the methods like this:
//assertTrue(result);
//instead of this:
//Assertions.assertTrue(result);
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.revature.data.PitchDAO;
import net.revature.data.UserDAO;
import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.User;
import net.revature.models.Pitch;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	//Create a field for the class we are testing
	@Mock // says that we want Mockito to create a mock version of the object
	private UserDAO userDAO;
	@Mock
	private PitchDAO pitchDAO;
	
	// we need a field for the class that we're testing
	@InjectMocks // this is where Mockito needs to inject the mocks
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
		
		// mocking : we need to mock userDAO.getUsername(username)
		// we're expecting a user with matching username & password
		User mockUser = new User();
		mockUser.setUserName(username);
		mockUser.setPassWord(password);
		when(userDAO.getByUsername(username)).thenReturn(mockUser);
		
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
		
		
		// we need to mock userDao. getBYusername(username);
		when(userDAO.getByUsername(username)).thenReturn(null);
		
		assertThrows(IncorrectCredentialsException.class, () ->{
			// this where we place the code that is expected to throw the exception
			userService.logIn(username, password);
		});
		
	}
	
	// testing the wrong password of the user
	public void logInWrongPassword() {
		String username = "avery";
		String password = "9786796";
		
		
		// mocking : we need to mock userDAO.getUsername(username)
				// we're expecting a user with matching username & password
				User mockUser = new User();
				mockUser.setUserName(username);
				mockUser.setPassWord("fake_password");
				when(userDAO.getByUsername(username)).thenReturn(mockUser);
		
		
		
		assertThrows(IncorrectCredentialsException.class, () ->{
			// this where we place the code that is expected to throw the exception(same as wrong username)
			userService.logIn(username, password);
		});
	}
	
	// registering the user correctly
	@Test
	public void registerUserSuccessfully() throws UserNameAlreadyExistsException {
		User newUser = new User();
		
		User result = userService.registerUserSuccessfully(newUser);
		

		// the behavior that i'm looking for is that the
		// method returns the User with their newly generated ID,
		// so i want to make sure the ID was generated (not the default)
		
		assertNotEquals(0, result.getId());
	}
	
	// seeing the username is take but passing in one that already exist to test if it actuallt take in the program
	@Test
	public void registerUsernameTaken() {
		User newUser = new User();
		newUser.setUserName("avery");
		
		assertThrows(UserNameAlreadyExistsException.class, () ->{
			userService.registerUserSuccessfully(newUser);
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
		
		// pitchDAO.getById : return testPitch
		when(pitchDAO.getById(testPitch.getId())).thenReturn(testPitch);
		//userDAO.getById : return testUser
		when(userDAO.getById(testUser.getId())).thenReturn(testUser);
		//pitchDAO.update : do nothing
		// when Pitch DAO update is called with any pitch object, do nothing
		doNothing().when(pitchDAO).update(any(Pitch.class));
		//userDAO.update: do nothing
		doNothing().when(userDAO).update(any(User.class));
		//userDAO.updatePitches: do nothing
		
		User result = userService.submittedPitch(testUser, testPitch);
		
		// there are two behaviors i'm looking for:
				// that the user now has the pitch in their list of pitches,
				// and that the pitch in the list has its status updated.
				// to check this, i'm checking that the pitch with the Submitted
				// status is in the user's list.
		
		testPitch.setStatus("Unsubmitted");
		List<Pitch> userPitches = result.getPitches();
		assertTrue(userPitches.contains(testPitch));
		
		// Mockito.verify allows you to make sure that a particular mock method
		// was called (or that it was never called, or how many times, etc.)
		verify(pitchDAO, times(1)).update(any(Pitch.class));

		
		
		
		
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
