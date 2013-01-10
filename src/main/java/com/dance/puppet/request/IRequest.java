package com.dance.puppet.request;

/**
 * 
 * @author Chan Chen
 *
 */
public interface IRequest {

	int getLineNumber();

	void setLineNumber(int lineNumber);

	String getScriptType();

	void setScriptType(String scriptType);

}
