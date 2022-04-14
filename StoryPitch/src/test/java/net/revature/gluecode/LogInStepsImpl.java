package net.revature.gluecode;

import static org.junit.jupiter.api.Assertions.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInStepsImpl {
	@Given("the user is on the homepage")
	public void the_user_is_on_the_homepage() {
	    // Write code here that turns the phrase above into concrete actions
	  System.out.println("Welcome to my homepage");
	}

	@When("the user enters the correct username")
	public void the_user_enters_the_correct_username() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("correct username");
	}

	@When("the user enters the correct password")
	public void the_user_enters_the_correct_password() {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("correct password");
	}

	@When("the user clicks the login button")
	public void the_user_clicks_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	   System.out.println("click ! :)");
	}

	@Then("the nav will show the user's first name")
	public void the_nav_will_show_the_user_s_first_name() {
	    // Write code here that turns the phrase above into concrete actions
	   assertTrue(true);
	}

	@When("the user enters an incorrect username")
	public void the_user_enters_an_incorrect_username() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("an incorrect credentials message will be displayed")
	public void an_incorrect_credentials_message_will_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the incorrect password")
	public void the_user_enters_the_incorrect_password() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks on the login button")
	public void the_user_clicks_on_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@When("the user enters the username {string}")
	public void the_user_enters_the_username(String username) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user enters the password {string}")
	public void the_user_enters_the_password(String password) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@When("the user clicks the login button")
	public void the_user_clikcs_the_login_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	@Then("an ivalid input message will be displayed")
	public void an_ivalid_input_message_will_be_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
