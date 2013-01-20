package com.dance.puppet.processor;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dance.puppet.conf.Config;

public class Engine {

	public static void fire() {
		ExecutorService processorPool = Executors.newFixedThreadPool(10);
		
		ProcessorFactory processorFactory = new ProcessorFactory();
		Config config = Config.getInstance();
		ArrayList<String> serverList = config.getServerList();
		for (String host : serverList) {
			//IProcessor processor = ProcessorFactory.createProcessor(config.getCommand().type());
			IProcessor processor = processorFactory.createProcessor("NORMAL");
			processor.setUsername(config.getUserName());
			processor.setPassword(config.getPassword());
			processor.setHost(host);
			processor.setCommand(config.getCommand());
			processor.setPort(config.getPort());
			processorPool.submit(processor);
		}
	}
}
