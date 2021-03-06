package com.dance.puppet.util;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainArgsParser {

	static final Logger	logger	= LoggerFactory.getLogger(MainArgsParser.class);

	public static Map parse(String[] args) {
		if (args == null) {
			logger.error("Main Args is Null");
		}
		Map<String, String> result = new HashMap<String, String>();
		for (int i = 0; i < args.length; i++) {
			if (args[i].equalsIgnoreCase("-u")) {
				result.put("-u", args[i + 1]);
			}
			if (args[i].equalsIgnoreCase("-p")) {
				result.put("-p", args[i + 1]);
			}
			if (args[i].equalsIgnoreCase("-s")) {
				result.put("-s", args[i + 1]);
			}
			if (args[i].equalsIgnoreCase("-c")) {
				result.put("-c", args[i + 1]);
			}
		}
		return result;
	}
}
