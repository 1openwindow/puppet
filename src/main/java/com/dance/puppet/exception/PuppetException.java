package com.dance.puppet.exception;

public class PuppetException extends Exception {

	public PuppetException(String message) {
		super(message);
	}

	public PuppetException(Throwable t) {
		super(t);
	}

	public PuppetException(String message, Throwable t) {
		super(message, t);
	}

	public String getMessage() {
		return super.getMessage();
	}

}
