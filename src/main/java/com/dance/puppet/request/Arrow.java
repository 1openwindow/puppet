package com.dance.puppet.request;

/**
 * Chain of Responsibility Pattern
 * ScriptRequest Object
 * 
 * @author Chan Chen
 *
 */
public interface Arrow {

	int getLineNumber();

	void setLineNumber(int lineNumber);

	String getScriptType();

	void setScriptType(String scriptType);

}
