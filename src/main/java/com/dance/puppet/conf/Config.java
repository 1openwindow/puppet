package com.dance.puppet.conf;

/**
 * Singleton Pattern Store Puppet Config Argument Singleton Pattern
 * 
 * @author Chan Chen
 * 
 */
public final class Config {

	private String									userName;
	private String									password;
	private String									serverPath;
	private String									scriptPath;
	private String									threadCount;
	private String									port	= "22";

	private volatile static Config	instance;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	public String getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(String threadCount) {
		this.threadCount = threadCount;
	}

	public String getPort() {
		return port;
	}

}
