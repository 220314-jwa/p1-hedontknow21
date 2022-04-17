package net.revature.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.time.Duration;
import org.openqa.selenium.By;



//following the page object model design pattern,
//this class will represent what the story pitch app home page
//is like and what interactions we might want to do

public class StoryPitchAppHomePage {
	// Have to create the webDriver object to connect to our home page
	WebDriver driver;
	
	
	

	// PageFactory allows you to use annotations to have Selenium instantiate your
	// WebElement fields for you
	@FindBy(id="usernameLogin")
	WebElement usernameInput;
	@FindBy(id="passwordLogin")
	WebElement passwordInput;
	@FindBy(id="logInButton")
	WebElement logInButton;
	@FindBy(id="messageBox")
	WebElement messageBox;
	
	
	
	
	public StoryPitchAppHomePage(WebDriver driver) {
		this.driver = driver;
		
		// using the PageFactory, selenium finds the elements by their id
		PageFactory.initElements( driver, this);
	}
	
	/* Since we are mocking the home page of the story pitch app we have to get all of the users
	 * interactions that happens with the homepage
	 * First we  need to navigate to the home page (line 62 )
	 * next we want the input of the username and password so we need the sendKeys() keyword to send to the
	 * homepage (lines 66, 60), Next we need to submit the login with logInButton (line 74) the click() function
	 * we also need to log out (line 78) so using an if statement we check the text of the login button
	 * to see about logging out, next we create our function to get the message box text (line 78)using 
	 * the Wait<WebDriver> FluentWait object so we can control precisley and effeciently the wait 
	 * of your app (line 84) using the Expected Conditions, last we create a function to get the nav text (line 94)
	 * also with a wait function because with automated testing in selenium it can move fast so we setup these 
	 * fluent waits so that we can actually see our test in motion
	 * 
	 * 
	 * */
	
	
	
	
	public void navigateTo() {
		driver.get("http://127.0.0.1:5500/StoryPitch/index.html");
	}
	
	public void inputUsername(String username) {
		usernameInput.sendKeys(username);
	}
	
	public void inputPassword(String password) {
		passwordInput.sendKeys(password);
	}
	
	public void submitLogin() {
		logInButton.click();
	}
	
	public void logOut() {
		if (logInButton.getText().equals("Log Out")) {
			logInButton.click();
		}
	}
	
	public String getMessageBoxText() {
		Wait<WebDriver> fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500));
		fluentWait.until(ExpectedConditions
				.textToBePresentInElement(messageBox, "i"));
		
		return messageBox.getText();
	}
	
	public String getNavText() {
		Wait<WebDriver> fluentWait = new FluentWait<>(driver)
				.withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofMillis(500));
		fluentWait.until(ExpectedConditions
				.numberOfElementsToBe(By.id("nameDisplay"), 1));
		
		return driver.findElement(By.id("nameDisplay")).getText();
	}
}




