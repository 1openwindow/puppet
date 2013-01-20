package com.dance.puppet.processor;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dance.puppet.conf.Config;
import com.dance.puppet.parser.ParserReader;
import com.dance.puppet.parser.ScriptParser;
import com.dance.puppet.parser.ServerParser;
import com.jcraft.jsch.Logger;

public class Engine {

	public static void fire() {
		ExecutorService processorPool = Executors.newFixedThreadPool(10);
		Processor processor = new ProcessorFactory().createProcessor();

		Config config = Config.getInstance();
		String command = Config.getInstance().getCommand();
		ArrayList<String> serverList = Config.getInstance().getServerList();
		for (String host : serverList) {
			processor.setUsername(config.getUserName());
			processor.setPassword(config.getPassword());
			processor.setHost(host);
			processor.setCommand(command);
			processor.setPort(config.getPort());
			processorPool.submit(processor);
		}
	}
}
