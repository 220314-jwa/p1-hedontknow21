package net.revature.exceptions;



public class AlreadySubmittedException extends Exception {
	
	public AlreadySubmittedException(){
	super("The pitch has been submitted already.");
	}
}
	
