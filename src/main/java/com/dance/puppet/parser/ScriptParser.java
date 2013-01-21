package com.dance.puppet.parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.apache.log4j.Logger;

/**
 * Parse Bash Script Text
 * 
 * @author Chan Chen
 * 
 */
public class ScriptParser implements IParser {

	static Logger		logger	= Logger.getLogger(ScriptParser.class);

	private String	commandList;

	public void parse(String path) {
		if (path == null) {
			logger.error("Can Not Parse Script : Script Text Path is null");
		}

		BufferedReader br;
		StringBuffer sb = new StringBuffer();
		try {
			br = new BufferedReader(new FileReader("script/" + path));
			while (br.ready()) {
				sb.append(br.readLine()).append(';');
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.commandList = sb.toString();
	}

	public void storeResult(ParserReader parserReader) {
		parserReader.setCommandList(this.commandList);
	}
}
