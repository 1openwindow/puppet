package com.dance.puppet.parser;

import java.util.ArrayList;
import org.apache.log4j.Logger;
import com.dance.puppet.conf.Config;
import com.dance.puppet.request.ServerList;
import com.dance.puppet.util.FileHelper;

public class ServerParser {

	static Logger											logger	= Logger.getLogger(ServerParser.class);

	private static String							serverPath;

	public static ArrayList<String> parseServer() {
		return parseServer(Config.getInstance().getServerPath());
	}

	public static ArrayList<String> parseServer(String serverPath) {
		if (serverPath == null) {
			logger.error("Can Not Parse Server : serverPath is null");
		}
		return FileHelper.fileToList("server/" + serverPath);
	}
}
