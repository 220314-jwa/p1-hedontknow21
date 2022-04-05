package net.revature.services;
//static import of the Assertions methods
//this allows us to call the methods like this:
//assertTrue(result);
//instead of this:
//Assertions.assertTrue(result);

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;



import net.revature.exceptions.IncorrectCredentialsException;
import net.revature.exceptions.AlreadySubmittedException;
import net.revature.exceptions.UserNameAlreadyExistsException;
import net.revature.models.StoryPitch;
import net.revature.models.User;







public class UserServiceTest {
	// we need a field for the class that we're testing
		private UserService userServ;
		
		// test methods always have no parameters and return void
		@Test
		public void exampleTest() {
			assertTrue(true);
		}


@Test
public void logInSuccessfully() throws IncorrectCredentialsException {
	// setup (arguments, expected result, etc.)
	String username = "avery";
	String password = "pass";
	
	// call the method we're testing
	User result = userServ.logIn(username, password);
	
	// assertion
	assertEquals(username, result.getUserName());
}

@Test
public void logInWrongUsername() {
	String username = "abc123";
	String password = "1234567890";
	
	assertThrows(IncorrectCredentialsException.class, () -> {
		// put the code that we're expecting to throw the exception
		userServ.logIn(username, password);
	});
}

@Test
public void logInWrongPassword() {
	String username = "avery";
	String password = "1234567890";
	
	assertThrows(IncorrectCredentialsException.class, () -> {
		// put the code that we're expecting to throw the exception
		userServ.logIn(username, password);
	});
}

@Test
public void registerSuccessfully() throws UserNameAlreadyExistsException, SQLException {
	User newUser = new User();
	
	User result = userServ.register(newUser);
	
	// the behavior that i'm looking for is that the
	// method returns the User with their newly generated ID,
	// so i want to make sure the ID was generated (not the default)
	assertNotEquals(0, result.getId());
}

@Test
public void registerUsernameTaken() {
	User newUser = new User();
	newUser.setUserName("avery");
	
	assertThrows(UserNameAlreadyExistsException.class, () -> {
		userServ.register(newUser);
	});
}

@Test
public void submitStorySuccessfully() throws AlreadySubmittedException {
	User testUser = new User();
	StoryPitch testPitch = new StoryPitch();
	
	User result = userServ.submittedPitch(testUser, testPitch);
	
	// there are two behaviors i'm looking for:
	// that the user now has the pet in their list of pets,
	// and that the pet in the list has its status updated.
	// to check this, i'm checking that the pet with the Adopted
	// status is in the user's list.
	testPitch.setStatus("Submitted");
	List<StoryPitch> usersPitches = result.getPitches();
	assertTrue(usersPitches.contains(testPitch));
}
}
