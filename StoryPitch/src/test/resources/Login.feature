#First we start off with what the feature is that we are writing a test for
#then we write out certain scenarios that a user may encounter when logging in
#the test structure is always (Given, When(And is an extension of When), lastly then which concludes the actions
#you can have reusability with the WHEN or AND if they are exact before you conclude the action (ex. line - 19,25,32,39)
#Only after a Scenario Outline can you give examples that cucumber will use as inputs in the test





Feature: user login

# you can use this when all of your scenarios have a certain prerequisite
#Background: the user is not logged in

Scenario: successful login
Given the user is on the homepage
When the user enters the correct username
And the user enters the correct password
And the user clicks the login button
Then the nav will show the user's first name

Scenario: username does not exist
Given the user is on the homepage
When the user enters an incorrect username
And the user clicks the login button
Then an incorrect credentials message will be displayed

Scenario: incorrect password
Given the user is on the homepage
When the user enters the correct username
And the user enters the incorrect password
And the user clicks on the login button
Then an incorrect credentials message will be displayed

Scenario Outline: invalid input
Given the user is on the homepage
When the user enters the username "<username>"
And the user enters the password "<password>"
And the user clicks the login button
Then an invalid input message will be displayed

		Examples:
		|username | password |
		|a				| p4ssw0rd |
		|user123  | a        |
		|%$&&)%   |p4sswo0rd |

