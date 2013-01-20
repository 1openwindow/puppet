package com.dance.puppet.processor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.dance.puppet.conf.Config;
import com.dance.puppet.parser.ParserReader;
import com.dance.puppet.parser.ScriptParser;

public class Engine {

	public static void fire(){
		ExecutorService processorPool = Executors.newFixedThreadPool(10);
		Processor processor = new ProcessorFactory().createProcessor();
		
		Config config = Config.getInstance();
		String command = new ScriptParser().getOnelineBash();
		for(String host : ParserReader.readServerList()){
			processor.setUsername(config.getUserName());
			processor.setPassword(config.getPassword());
			processor.setHost(host);
			processor.setCommand(command);
			processor.setPort(config.getPort());
			processorPool.submit(processor);
		}
	}
}
