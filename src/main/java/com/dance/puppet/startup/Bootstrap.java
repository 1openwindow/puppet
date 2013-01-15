package com.dance.puppet.startup;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dance.puppet.processor.MyRunnable;
import com.dance.puppet.util.FileHelper;

/**
 * 
 * @author Chan Chen
 * 
 */
public final class Bootstrap {

	static Logger							logger			= Logger.getLogger(Bootstrap.class);

	private String						username;
	private String						password;
	// private String serverPath;
	// private String scriptPath;
	private String						onelineCommand;
	private ArrayList<String>	serverList	= new ArrayList<String>();

	public Bootstrap() {
		// loadConfig();
		parseScript();
		sendCommond();
		startReceiever();
		displayResult();
	}

	public void init() {

	}

	public Bootstrap loadConfig(String username, String password, String serverPath, String scriptPath) {
		logger.info("========================================");
		logger.info("Start Loading Config");
		logger.info("========================================");

		this.username = username;
		this.password = password;
		// this.serverPath = serverPath;
		// this.scriptPath = scriptPath;
		this.serverList = FileHelper.fileToList("server/"+serverPath);
		this.onelineCommand = FileHelper.bashToOneLine("script/"+scriptPath);

		logger.info("username => " + username);
		logger.info("password => ********");
		logger.info("server.list path => $PUPPET_HOME/server/" + serverPath);
		logger.info("script file path => $PUPPET_HOME/script/" + scriptPath);

		logger.info("========================================");
		logger.info("Loading Config Successfully");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		return this;
	}

	public Bootstrap parseScript() {
		return this;
	}

	public Bootstrap sendCommond() {
		logger.info("========================================");
		logger.info("Execute Command on Remote Servers");
		logger.info("========================================");
		logger.info("execute the following commond => " + this.onelineCommand);

		String cmd = "";
		Long beforeTimer = System.currentTimeMillis();

		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		MyRunnable cmdRunnable = null;
		for (String host : this.serverList) {
			cmd = this.onelineCommand;
			cmdRunnable = new MyRunnable(this.username, this.password, host, cmd);
			threadPool.submit(cmdRunnable);
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(600, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long afterTimer = System.currentTimeMillis();
		
		logger.info("Total Time eclapse is " + (afterTimer - beforeTimer) / 1000 + " seconds");
		logger.info("========================================");
		logger.info("Execute Command on Remote Servers Successfully");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		return this;
	}

	public Bootstrap startReceiever() {
		return this;
	}

	public Bootstrap displayResult() {
		return this;
	}

}
