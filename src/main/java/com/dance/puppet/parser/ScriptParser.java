package com.dance.puppet.parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Parse Bash Script Text
 * 
 * @author Chan Chen
 * 
 */
public class ScriptParser implements IParser {

	static final Logger				logger	= LoggerFactory.getLogger(ScriptParser.class);

	private ArrayList<String>	parserResult;

	// private String commandList;

	public void parse(String path) {
		if (path == null) {
			logger.error("Can Not Parse Script : Script Text Path is null");
		}

		ArrayList<String> result = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(new File("script/" + path)));
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

		// BufferedReader br;
		// StringBuffer sb = new StringBuffer();
		// try {
		// br = new BufferedReader(new FileReader("script/" + path));
		// while (br.ready()) {
		// sb.append(br.readLine()).append(';');
		// }
		// } catch (FileNotFoundException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// this.commandList = sb.toString();
	}

	public void storeResult(ParserReader parserReader) {
		parserReader.setCommandList(this.parserResult);
	}
}
