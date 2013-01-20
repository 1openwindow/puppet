package com.dance.puppet.conf;

import java.util.ArrayList;

/**
 * Singleton Pattern Store Puppet Config Argument Singleton Pattern
 * 
 * @author Chan Chen
 * 
 */
public final class Config {

	private static String userName;
	private static String password;
	private static String serverPath;
	private static String scriptPath;
	private static String threadCount;
	private static String port = "22";
	private volatile static Config instance;

	private Config() {
	}

	public static Config getInstance() {
		if (instance == null) {
			synchronized (Config.class) {
				if (instance == null) {
					instance = new Config();
				}
			}
		}
		return instance;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Config.userName = userName;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		Config.password = password;
	}

	public static String getServerPath() {
		return serverPath;
	}

	public static void setServerPath(String serverPath) {
		Config.serverPath = serverPath;
	}

	public static String getScriptPath() {
		return scriptPath;
	}

	public static void setScriptPath(String scriptPath) {
		Config.scriptPath = scriptPath;
	}

	public static String getThreadCount() {
		return threadCount;
	}

	public static void setThreadCount(String threadCount) {
		Config.threadCount = threadCount;
	}

	public static String getPort() {
		return port;
	}

}
