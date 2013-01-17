package com.dance.puppet.startup;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dance.puppet.conf.Config;
import com.dance.puppet.parser.ParserReader;
import com.dance.puppet.parser.ScriptParser;
import com.dance.puppet.parser.ServerParser;
import com.dance.puppet.processor.MyRunnable;
import com.dance.puppet.util.FileHelper;

/**
 * Method Chaining Pattern Use to Start Puppet
 * 
 * @author Chan Chen
 * 
 */
public final class Bootstrap {

	static Logger							logger			= Logger.getLogger(Bootstrap.class);

	private String						onelineCommand;
	private ArrayList<String>	serverList	= new ArrayList<String>();

	public void start() {
	}

	public Bootstrap loadConfig(String username, String password, String serverPath, String scriptPath) {
		logger.info("========================================");
		logger.info("Start Loading Config");
		logger.info("========================================");

		Config.getInstance().setUserName(username);
		Config.getInstance().setPassword(password);
		Config.getInstance().setServerPath(serverPath);
		Config.getInstance().setScriptPath(scriptPath);

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

	public Bootstrap parseServer() {
		logger.info("========================================");
		logger.info("Start Parse Sever List");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		this.serverList = ServerParser.parseServer();
		logger.info("========================================");
		logger.info("Parse Server List Successfully");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		return this;
	}

	public Bootstrap parseScript() {
		logger.info("========================================");
		logger.info("Start Parse Script File");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		this.onelineCommand = ScriptParser.parseScript();
		logger.info("========================================");
		logger.info("Parse Script File Successfully");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		return this;
	}

	public Bootstrap sendCommond() {
		logger.info("========================================");
		logger.info("Execute Command on Remote Servers");
		logger.info("========================================");
		logger.info("execute the following commond => " + this.onelineCommand);


		Long beforeTimer = System.currentTimeMillis();

		String cmd = ParserReader.readOneLineCommand();
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		MyRunnable cmdRunnable = null;
		for (String host : ParserReader.readServerList()) {
			cmdRunnable = new MyRunnable(Config.getInstance().getUserName(), Config.getInstance().getPassword(),
					host, cmd);
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
