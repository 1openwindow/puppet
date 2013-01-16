package com.dance.puppet.conf;

import java.util.Properties;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Not use so far
 * 
 * AppConfig - a drop-in replacement for System.getProperty() calls. The
 * application will need to suitably initialize the underlying properties for
 * this class.
 * 
 * @author Chan Chen
 * 
 */
public class PuppetConfig {
	private static final Logger				logger		= Logger.getLogger(PuppetConfig.class.getName());

	// singleton instance
	private static final PuppetConfig	instance	= new PuppetConfig();

	public static PuppetConfig getInstance() {
		return instance;
	}

	private String			appName;
	private String			appVersion;
	private String			contextPath;
	private String			derivedPath	= "";
	// underlying config - default to System props
	private Properties	properties	= System.getProperties();

	/**
	 * Returns the name of the application
	 * 
	 * @@return the name of the application
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the name of the application
	 * 
	 * @@param appName - the name of the application
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Returns the version of the application
	 * 
	 * @@return the version of the application
	 */
	public String getAppVersion() {
		return appVersion;
	}

	/**
	 * Sets the version of the application
	 * 
	 * @@param appVersion - the version of the application
	 */
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}

	/**
	 * Returns the context-path for the application
	 * 
	 * @@return the context-path for the application
	 */
	public String getContextPath() {
		return contextPath;
	}

	/**
	 * Sets the context-path for the application
	 * 
	 * @@param contextPath - the context-path for the application
	 */
	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
		if (contextPath == null) {
			derivedPath = "";
		} else {
			String path = contextPath;
			while (path.startsWith("/"))
				path = path.substring(1);
			path = path.replaceAll("/", "#");
			derivedPath = path;
		}
	}

	/**
	 * Returned the path as derived from the context-path - basically replaces the
	 * "/" characters with "#". This can be used as the name of the folder used to
	 * store the application specific overrides.
	 * 
	 * @@return the path as derived from the context-path
	 */
	public String getDerivedPath() {
		return derivedPath;
	}

	// Methods to allow AppConfig to act as a drop-in replacement for System

	/**
	 * Returns the underlying config
	 * 
	 * @@return - the underlying config
	 */
	public Properties getProperties() {
		return properties;
	}

	/**
	 * Sets the underlying config
	 * 
	 * @@param properties - the underlying config
	 */
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
}