package com.dance.puppet.startup;

import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.dance.puppet.util.MainArgsParser;

public class GoPuppet {

	static Logger							logger			= Logger.getLogger(GoPuppet.class);
	
	public static void main(String[] args) {
		// Load log4j Properties
		PropertyConfigurator.configure("properties/log4j.properties");
		logger.info("==================================");
		logger.info("Start Puppet");
		logger.info("==================================");
		logger.info("");

		logger.info("Parse Main Args");
		Map<String,String> mainArgs = MainArgsParser.parse(args);
		Bootstrap bootStrap = new Bootstrap();
		bootStrap.loadConfig(mainArgs.get("-u"), mainArgs.get("-p"), mainArgs.get("-s"), mainArgs.get("-c")).sendCommond();
		//bootStrap.loadConfig("puppet","puppet","server.list","test.sh").sendCommond();
	}
}
