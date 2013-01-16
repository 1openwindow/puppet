package com.dance.puppet.parser;

import java.util.ArrayList;

/**
 * Use to Read Server List and Script, after parse Server List and Script
 * 
 * @author Chan Chen
 * 
 */
public class ParserReader {

	private static ArrayList<String>	serverList;
	private static String							onelineCommand;

	public static ArrayList<String> readServerList() {
		return serverList;
	}

	public static void loadServerList(ArrayList<String> serverList) {
		ParserReader.serverList = serverList;
	}

	public static String readOneLineCommand() {
		return onelineCommand;
	}

	public static void setOnelineCommand(String onelineCommand) {
		ParserReader.onelineCommand = onelineCommand;
	}

}
