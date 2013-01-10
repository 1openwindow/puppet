package com.dance.puppet.logger;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4JTest {
	static Logger								logger				= Logger.getLogger(Log4JTest.class);
	private static final String	CLASSNAME	= Log4JTest.class.getName();

	public static void main(String[] args) {
		BasicConfigurator.configure();
		logger.debug("This is debug message");
		myMethod();
		logger.info("this is info message");
	}

	public static void myMethod() {
		try {
			throw new Exception("My Exception");
		} catch (Exception e) {
			logger.error("this is an exception", e);
		}
	}
}
