package com.dance.puppet.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class ServerParser implements IParser {

	static Logger											logger	= Logger.getLogger(ServerParser.class);

	private ArrayList<String>	parserResult;

	public void parse(String serverPath) {
		if (serverPath == null) {
			logger.error("Can Not Parse Server : serverPath is null");
		}
		Scanner sc = null;
		try {
			sc = new Scanner(new File("server/" + serverPath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> result = new ArrayList<String>();
		while (sc.hasNext())
			result.add(sc.next());
		sc.close();
		this.parserResult = result;
	}

	public void storeResult(ParserReader parserReader) {
		parserReader.setServerList(this.parserResult);
	}
}
