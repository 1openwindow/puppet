package com.dance.puppet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class App {
	// static Logger logger = Logger.getLogger(App.class);
	// private static final String CLASSNAME = App.class.getName();
	static Logger	logger	= LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {
		// System.out.println("Hello World! Alpha Submmit. Final Alpha Thinks");
		// logger.debug("This is debug message");
		// myMethod();
		// logger.info("this is info message");
		// Logger.logWarnMessage(CLASSNAME, "main",
		// "Error while processing result from the netscalar ");
		logger.debug("hello {}", "world");
	}

	public static void myMethod() {
		try {
			throw new Exception("My Exception");
		} catch (Exception e) {
			// logger.error("this is an exception", e);
		}
	}
}
