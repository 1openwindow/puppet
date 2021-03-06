package com.dance.puppet.parser;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Use to Read Server List and Script, after parse Server List and Script
 * 
 * Singleton + Strategy
 * 
 * @author Chan Chen
 * 
 */
public class ParserReader {

	static final Logger													logger	= LoggerFactory.getLogger(ServerParser.class);

	private ArrayList<String>							serverList;
	// private String commandList;
	private ArrayList<String>							commandList;

	private volatile static ParserReader	instance;

	private ParserReader() {
	}

	public static ParserReader getInstance() {
		if (instance == null) {
			synchronized (ParserReader.class) {
				if (instance == null) {
					instance = new ParserReader();
				}
			}
		}
		return instance;
	}

	public void parse(IParser parser, String path) {
		parser.parse(path);
		parser.storeResult(getInstance());
	}

	public ArrayList<String> getServerList() {
		if (this.serverList == null) {
			logger.info("ServerList is null, ServerPath has not been parsed");
		}
		return this.serverList;
	}

	public void setServerList(ArrayList<String> serverList) {
		this.serverList = serverList;
	}

	public ArrayList<String> getCommandList() {
		if (this.commandList == null) {
			logger.info("CommandList is null, CommandList has not been parsed");
		}
		return commandList;
	}

	public void setCommandList(ArrayList<String> commandList) {
		this.commandList = commandList;
	}

	// public String getCommandList() {
	// if (this.commandList == null) {
	// logger.info("CommandList is null, CommandList has not been parsed");
	// }
	// return this.commandList;
	// }
	//
	// public void setCommandList(String commandList) {
	// this.commandList = commandList;
	// }

}
