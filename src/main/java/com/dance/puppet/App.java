package com.dance.puppet;

import com.dance.puppet.logger.Logger;


/**
 * Hello world!
 * 
 */
public class App {
	//static Logger	logger	= Logger.getLogger(App.class);
	private static final String	CLASSNAME	= App.class.getName();
	
	public static void main(String[] args) {
		System.out.println("Hello World! Alpha Submmit");
		//logger.debug("This is debug message");
		//myMethod();
		//logger.info("this is info message");
		Logger.logWarnMessage(CLASSNAME, "main",
				"Error while processing result from the netscalar ");
	}

	public static void myMethod() {
		try {
			throw new Exception("My Exception");
		} catch (Exception e) {
			//logger.error("this is an exception", e);
		}
	}
}
