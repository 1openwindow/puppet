package com.dance.puppet.startup;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.dance.puppet.util.FileHelper;

public class BoosStrap {
	static Logger	logger	= Logger.getLogger(Bootstrap.class);

	public static void main(String[] args) {
		//Load log4j Properties
		PropertyConfigurator.configure("properties/log4j.properties");
		
		ArrayList<String> list = FileHelper.fileToList("server/localtest.list");
		String onelineCommond = FileHelper.bashToOneLine("script/echoVar.sh");
		logger.info("execute the following commond");
		logger.info(onelineCommond);
		String user = null;
		String password = null;
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("properties/test.properties"));
			user = prop.getProperty("USERNAME");
			password = prop.getProperty("PASSWORD");
		} catch (Exception e) {
			System.out.println();
		}

		String cmd = "";
		Long beforeTimer = System.currentTimeMillis();
		logger.info("Before Timer is: " + beforeTimer);

		ExecutorService threadPool = Executors.newFixedThreadPool(50);
/*		for (String host : list) {
			cmd = onelineCommond;
			threadPool.submit(cmdRunnable);
		}
		threadPool.shutdown();*/
		try {
			threadPool.awaitTermination(600, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Long afterTimer = System.currentTimeMillis();
		logger.info("After Timer is: " + afterTimer);
		logger.info("Total Time eclapse is " + (afterTimer - beforeTimer) / 1000 + " seconds");
	}
}
