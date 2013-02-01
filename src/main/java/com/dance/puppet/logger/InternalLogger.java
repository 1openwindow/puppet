package com.dance.puppet.logger;

/**
 * Puppet Logger Interface
 * 
 * @author Chan Chen
 * 
 */
public interface InternalLogger {
	/**
	 * Returns {@code true} if a TRACE level message is logged.
	 */
	boolean isTraceEnabled();

	/**
	 * Returns {@code true} if a DEBUG level message is logged.
	 */
	boolean isDebugEnabled();

	/**
	 * Returns {@code true} if an INFO level message is logged.
	 */
	boolean isInfoEnabled();

	/**
	 * Returns {@code true} if a WARN level message is logged.
	 */
	boolean isWarnEnabled();

	/**
	 * Returns {@code true} if an ERROR level message is logged.
	 */
	boolean isErrorEnabled();

	/**
	 * Returns {@code true} if the specified log level message is logged.
	 */
	boolean isEnabled(InternalLoggerLevel level);

	/**
	 * Logs a TRACE level message.
	 */
	void trace(String msg);

	/**
	 * Logs a TRACE level message.
	 */
	void trace(String msg, Throwable cause);

	/**
	 * Logs a DEBUG level message.
	 */
	void debug(String msg);

	/**
	 * Logs a DEBUG level message.
	 */
	void debug(String msg, Throwable cause);

	/**
	 * Logs an INFO level message.
	 */
	void info(String msg);

	/**
	 * Logs an INFO level message.
	 */
	void info(String msg, Throwable cause);

	/**
	 * Logs a WARN level message.
	 */
	void warn(String msg);

	/**
	 * Logs a WARN level message.
	 */
	void warn(String msg, Throwable cause);

	/**
	 * Logs an ERROR level message.
	 */
	void error(String msg);

	/**
	 * Logs an ERROR level message.
	 */
	void error(String msg, Throwable cause);

	/**
	 * Logs a message.
	 */
	void log(InternalLoggerLevel level, String msg);

	/**
	 * Logs a message.
	 */
	void log(InternalLoggerLevel level, String msg, Throwable cause);
}
