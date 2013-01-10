package com.dance.puppet.logger;

import org.apache.log4j.Priority;

/**
 * 
 * <code>Logger</code> is helper class to log application logs.
 * 
 * @author Chan Chen
 * 
 */
public class Logger {

	private String	className	= null;

	enum LogLevel {
		FINE, INFO, WARN, ERROR;
	}

	private Logger(String classNmae) {
		this.className = className;
	}

	public static Logger getLogger(String classNmae) {
		Logger logger = new Logger(classNmae);
		return logger;
	}

	public void log(LogLevel logLevel, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		Priority p = null;

		switch (logLevel) {
		case FINE:
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
	}

	public static void logFineMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.DEBUG, message);
	}

	public static void logInfoMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.INFO, message);
	}

	public static void logWarnMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.WARN, getMessageString(className, methodName, message));
	}

	public static void logErrorMessage(String className, String methodName, String message) {
		org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(className);
		logger.log(Priority.ERROR, getMessageString(className, methodName, message));
	}

	private static String getMessageString(String className, String methodName, String message) {
		return className + "\t" + methodName + "\t" + message;
	}

}
