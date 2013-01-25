package com.dance.puppet.parser;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

public class ScriptParserTest {

	@Test
	public void testParse() {
		ParserReader.getInstance().parse(new ScriptParser(), "scriptParserTest.sh");
		ArrayList<String> serverList = ParserReader.getInstance().getCommandList();
		for (String host : serverList) {
			System.out.println(host);
		}
	}

}
