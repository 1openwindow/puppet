package com.dance.puppet.parser;

/**
 * IParser interface use to convert a given file to inner data structure
 * Strategy Design Patter
 * 
 * @author Chan Chen
 * 
 */
public interface IParser {

	/**
	 * Parse a given path
	 * @param path
	 */
	void parse(String path);

	/**
	 * Store Parse Result on which ParserReader
	 * @param parserReader
	 */
	void storeResult(ParserReader parserReader);
}
