package net.revature.gluecode;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.revature.pages.StoryPitchAppHomePage;


/*Now we use the information for our mock story pitch app home page
 * to test with out cucumber feature file using selenium 
 * this intergration of testing tools make for the test to be that much precise 
 * when creating an app using TDD and BDD - Test Driven Dev and Behavior Driven Dev
 * 
 * 
 * */

public class LogInStepsImpl {
	static WebDriver driver;
	static StoryPitchAppHomePage pitchApp;
	
	// Always when testing you have to setup your driver with a file 
	// and connect the driver as the path that test will run from
	@BeforeAll
	public static void setUp() {
		File file = new File("src/main/resources/msedgedriver.exe");
		System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
		// instantiate and declare your driver and app page
		driver = new EdgeDriver();
		pitchApp = new StoryPitchAppHomePage(driver);
	}
	
	@AfterAll
	public static void closeDriver() {
		driver.close();
		//Always rememeber to close driver or test will complete but the page will not close
	}
	
	
	@Given("the user is on the homepage")
	public void the_user_is_on_the_homepage() {
	    // Write code here that turns the phrase above into concrete actions
	  pitchApp.navigateTo();
	}

	@When("the user enters the correct username")
	public void the_user_enters_the_correct_username() {
	    // Write code here that turns the phrase above into concrete actions
	    pitchApp.inputUsername("avery");
	}

	@When("the user enters the correct password")
	public void the_user_enters_the_correct_password() {
	    // Write code here that turns the phrase above into concrete actions
	    pitchApp.inputPassword("pass");
	}

	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	   pitchApp.submitLogin();
	}

	@Then("the nav will show the user's first name")
	public void the_nav_will_show_the_user_s_first_name() {
	    // Write code here that turns the phrase above into concrete actions
	   assertTrue(pitchApp.getNavText().contains("avery"));
	}

	@When("the user enters an incorrect username")
	public void the_user_enters_an_incorrect_username() {
	    // Write code here that turns the phrase above into concrete actions
	    pitchApp.inputUsername("gugdohhep");
	}

	@Then("an incorrect credentials message will be displayed")
	public void an_incorrect_credentials_message_will_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    String message = pitchApp.getMessageBoxText().toLowerCase();
	    assertTrue(message.contains("incorrect credentials"));
	}

	@When("the user enters the incorrect password")
	public void the_user_enters_the_incorrect_password() {
	    // Write code here that turns the phrase above into concrete actions
	   pitchApp.inputPassword("oeuhfocbsn");
	}

	
	@When("the user enters the username {string}")
	public void the_user_enters_the_username(String username) {
	    // Write code here that turns the phrase above into concrete actions
	    pitchApp.inputUsername(username);
	}

	@When("the user enters the password {string}")
	public void the_user_enters_the_password(String password) {
	    // Write code here that turns the phrase above into concrete actions
	   pitchApp.inputPassword(password);
	}

	

	@Then("an ivalid input message will be displayed")
	public void an_ivalid_input_message_will_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	  String message = pitchApp.getMessageBoxText().toLowerCase();
	  assertTrue(message.contains("invalid input"));
	}


}
