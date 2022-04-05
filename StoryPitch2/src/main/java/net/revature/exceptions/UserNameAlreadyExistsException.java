package net.revature.exceptions;

public class UserNameAlreadyExistsException extends Exception{

	public UserNameAlreadyExistsException() {
		super("This username already exists");
	}
}
