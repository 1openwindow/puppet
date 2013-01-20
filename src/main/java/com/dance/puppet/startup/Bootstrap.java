package com.dance.puppet.startup;

import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.dance.puppet.conf.Config;
import com.dance.puppet.parser.ScriptParser;
import com.dance.puppet.parser.ServerParser;
import com.dance.puppet.processor.Engine;

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
		Config.getInstance().setServerList(ServerParser.parseServer());
		logger.info("");
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
		Config.getInstance().setCommand(ScriptParser.parseScript());
		logger.info("");
		logger.info("========================================");
		logger.info("Parse Script File Successfully");
		logger.info("========================================");
		logger.info("");
		logger.info("");
		return this;
	}

	public Bootstrap fireEngine() {
		logger.info("========================================");
		logger.info("Execute Command on Remote Servers");
		logger.info("========================================");
		logger.info("execute the following commond => " + this.onelineCommand);

		Long beforeTimer = System.currentTimeMillis();
		Engine.fire();
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
