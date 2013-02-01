package com.dance.puppet.logger;

public abstract class AbstractInternalLogger implements InternalLogger {

	/**
	 * Creates a new instance.
	 */
	protected AbstractInternalLogger() {
	}

	public boolean isEnabled(InternalLoggerLevel level) {
		switch (level) {
		case TRACE:
			return isTraceEnabled();
		case DEBUG:
			return isDebugEnabled();
		case INFO:
			return isInfoEnabled();
		case WARN:
			return isWarnEnabled();
		case ERROR:
			return isErrorEnabled();
		default:
			throw new Error();
		}
	}

	public void log(InternalLoggerLevel level, String msg, Throwable cause) {
		switch (level) {
		case TRACE:
			trace(msg, cause);
			break;
		case DEBUG:
			debug(msg, cause);
			break;
		case INFO:
			info(msg, cause);
			break;
		case WARN:
			warn(msg, cause);
			break;
		case ERROR:
			error(msg, cause);
			break;
		default:
			throw new Error();
		}
	}

	public void log(InternalLoggerLevel level, String msg) {
		switch (level) {
		case TRACE:
			trace(msg);
			break;
		case DEBUG:
			debug(msg);
			break;
		case INFO:
			info(msg);
			break;
		case WARN:
			warn(msg);
			break;
		case ERROR:
			error(msg);
			break;
		default:
			throw new Error();
		}
	}
}
