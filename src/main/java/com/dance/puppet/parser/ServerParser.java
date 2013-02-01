package com.dance.puppet.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerParser implements IParser {

	static final Logger				logger	= LoggerFactory.getLogger(ServerParser.class);

	private ArrayList<String>	parserResult;

	public void parse(String serverPath) {
		if (serverPath == null) {
			logger.error("Can Not Parse Server : serverPath is null");
		}
		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("server/" + serverPath)));
			String line = null;

			while ((line = reader.readLine()) != null) {
				if (line.matches("^\\s*#.*")) {
					continue;
				} else {
					result.add(line);
				}
			}

			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.parserResult = result;
	}

	public void storeResult(ParserReader parserReader) {
		parserReader.setServerList(this.parserResult);
	}
}
