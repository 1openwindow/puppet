package com.dance.puppet.parser;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.dance.puppet.conf.Config;
import com.dance.puppet.request.ServerList;
import com.dance.puppet.util.FileHelper;

/**
 * Parse Bash Script Text
 * 
 * @author Chan Chen
 * 
 */
public class ScriptParser {

	static Logger		logger	= Logger.getLogger(ScriptParser.class);

	private String	onelineScript;
	private String	scriptPath;

	public String getOnelineBash() {
		return onelineScript;
	}

	public void setOnelineBash(String onelineBash) {
		this.onelineScript = onelineBash;
	}

	public String getBashPath() {
		return scriptPath;
	}

	public void setBashPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	public static String parseScript() {
		return parseServer(Config.getInstance().getScriptPath());
	}

	public static String parseServer(String scriptPath) {
		if (scriptPath == null) {
			logger.error("Can Not Parse Script : Script Text Path is null");
		}

		return FileHelper.bashToOneLine("script/" + scriptPath);
	}
}
