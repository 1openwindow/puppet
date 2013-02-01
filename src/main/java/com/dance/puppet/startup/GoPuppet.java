package com.dance.puppet.startup;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dance.puppet.util.MainArgsParser;

public class GoPuppet {

	static final Logger	logger	= LoggerFactory.getLogger(GoPuppet.class);

	public static void main(String[] args) {
		// Load log4j Properties
		//PropertyConfigurator.configure("properties/log4j.properties");
		logger.info("==================================");
		logger.info("Start Puppet");
		logger.info("==================================");
		logger.info("");

		logger.info("Parse Main Args...");
		Map<String, String> mainArgs = MainArgsParser.parse(args);

		Bootstrap bootstrap = new Bootstrap();
		bootstrap.test().loadConfig(mainArgs.get("-u"), mainArgs.get("-p"), mainArgs.get("-s"), mainArgs.get("-c"))
				.parseServer().parseScript().fireEngine();
	}
}
