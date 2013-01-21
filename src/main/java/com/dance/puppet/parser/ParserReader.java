package com.dance.puppet.parser;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Use to Read Server List and Script, after parse Server List and Script
 * 
 * Singleton + Strategy
 * 
 * @author Chan Chen
 * 
 */
public class ParserReader {

	static Logger													logger	= Logger.getLogger(ServerParser.class);

	private ArrayList<String>							serverList;
	private String												commandList;

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

	public String getCommandList() {
		if (this.commandList == null) {
			logger.info("CommandList is null, CommandList has not been parsed");
		}
		return this.commandList;
	}

	public void setCommandList(String commandList) {
		this.commandList = commandList;
	}
}
