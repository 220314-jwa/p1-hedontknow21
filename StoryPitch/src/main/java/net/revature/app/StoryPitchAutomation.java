package net.revature.app;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StoryPitchAutomation {
	public static void main(String[] args) {
	//first create a file for your web driver in this case an edge driver
	File file = new File("src/main/resources/msedgedriver.exe");
	
	// Set the driver to to the file so we can make connections to edge websites
	System.setProperty("webdriver.edge.driver", file.getAbsolutePath());
	
	// create your web driver object (using EdgeDriver or whatever internet services you use)
	WebDriver driver = new EdgeDriver();
	
	//send the driver to the webpage of your choice
	// In this case the main page to my story pitch app
	driver.get("http://127.0.0.1:5500/StoryPitch/index.html");
	
	// by right clicking the page and selecting inspect we can find out the elements we need 
	// to write out our automation test
	WebElement userNameLogin = driver.findElement(By.id("usernameLogin"));
	WebElement passWordLogin = driver.findElement(By.id("passwordLogin"));
	WebElement logNButton = driver.findElement(By.id("logInButton"));
	
	// send the elements to the webpage using the sendKeys property
	// by selecting the ids your are passing in real information for the webpages features
	userNameLogin.sendKeys("avery");
	passWordLogin.sendKeys("pass");
	// using a button will require and action associated with a button i.e(click, clickOn)
	logNButton.click();
	
	
	//here we are creating a wait driver so we can wait for selenium to log in before closing the connection
	// There are 3 ways to make selenium wait : Implicit(the worse way), Explicit(an Okay way)
	// and Fluent(the name speaks for itself)
	Wait<WebDriver>fluentWait = new FluentWait<>(driver)
			.withTimeout(Duration.ofSeconds(2))
			.pollingEvery(Duration.ofMillis(500));
	fluentWait.until(ExpectedConditions.
			numberOfElementsToBe(By.id("usernameLogin"), 1));
	
	//* Rule number 1 !!!!!!! always remember to close your driver after running your selenium
	// test , if not your test will kepp that page open as if its still testing it
	//This closes the page and completes your test
	driver.close();
	
	
	}
}
