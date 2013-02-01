package com.dance.puppet.logger;

import org.slf4j.LoggerFactory;


/**
 * 
 * <code>Logger</code> is helper class to log application logs.
 * 
 * @author Chan Chen
 * 
 */
public class Logger {

	private String	className	= null;

	private Logger(String className) {
		this.className = className;
	}

	public static Logger getLogger(String className) {
		Logger logger = new Logger(className);
		return logger;
	}

/*	public void log(InternalLoggerLevel logLevel, String methodName, String message) {
		Logger logger = (Logger) LoggerFactory.getLogger(className);
		switch (logLevel) {
		case TRACE:
			p = Priority.DEBUG;
			break;
		case DEBUG:
			p = Priority.DEBUG;
			break;
		case INFO:
			p = Priority.INFO;
			break;
		case WARN:
			p = Priority.WARN;
			break;
		case ERROR:
			p = Priority.ERROR;
			break;
		default:
			p = Priority.INFO;
			break;
		}
		logger.log(p, getMessageString(className, methodName, message));
		// System.out.println(message);
	}

	public static void logTraceMessage(String className, String methodName, String message){
		Logger logger = (Logger) LoggerFactory.getLogger(className);
		logger.log(InternalLoggerLevel.TRACE, message);
	}
	public static void logFineMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.DEBUG, message);
		// System.out.println(message);
	}

	public static void logInfoMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.INFO, message);
		// System.out.println(message);
	}

	public static void logWarnMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.WARN, getMessageString(className, methodName, message));
		// System.out.println(message);
	}

	public static void logErrorMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.ERROR, getMessageString(className, methodName, message));
		// System.out.println(message);
	}

	private static String getMessageString(String className, String methodName, String message) {
		return className + "\t" + methodName + "\t" + message;
	}*/

}
