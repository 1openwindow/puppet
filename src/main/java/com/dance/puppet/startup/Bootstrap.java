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

	static Logger	logger	= Logger.getLogger(Bootstrap.class);

	public Bootstrap() {
		loadConfig();
		parseScript();
		sendCommond();
		startReceiever();
		displayResult();
	}

	public void init(){
		
	}
	
	public Bootstrap loadConfig() {
		return this;
	}

	public Bootstrap parseScript() {
		return this;
	}

	public Bootstrap sendCommond() {
		return this;
	}

	public Bootstrap startReceiever() {
		return this;
	}

	public Bootstrap displayResult() {
		return this;
	}

	public static void main(String[] args) {
		// Load Config Properties

		// Parse Script

		// Run Engine

		// Load log4j Properties
		PropertyConfigurator.configure("properties/log4j.properties");

		ArrayList<String> list = FileHelper.fileToList("server/server.list");
		String onelineCommond = FileHelper.bashToOneLine("script/test.sh");
		logger.info("execute the following commond");
		logger.info(onelineCommond);
		String user = null;
		String password = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("properties/config.properties"));
			user = prop.getProperty("USERNAME");
			password = prop.getProperty("PASSWORD");
		} catch (Exception e) {
			System.out.println();
		}

		String cmd = "";
		Long beforeTimer = System.currentTimeMillis();
		// logger.info("Before Timer is: " + beforeTimer);

		ExecutorService threadPool = Executors.newFixedThreadPool(50);
		MyRunnable cmdRunnable = null;
		for (String host : list) {
			cmd = onelineCommond;
			cmdRunnable = new MyRunnable(user, password, host, cmd);
			threadPool.submit(cmdRunnable);
		}
		threadPool.shutdown();
		try {
			threadPool.awaitTermination(600, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long afterTimer = System.currentTimeMillis();
		// logger.info("After Timer is: " + afterTimer);
		logger.info("Total Time eclapse is " + (afterTimer - beforeTimer) / 1000 + " seconds");
	}
}
