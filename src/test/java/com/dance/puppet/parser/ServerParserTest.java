package com.dance.puppet.parser;

import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ServerParserTest {

	@BeforeClass
	public static void testSetup() {
		// Preparation of the unit tests
	}

	@AfterClass
	public static void testCleanup() {
		// Teardown for data used by the unit tests
	}

	@Test
	public void testCommentFunctionOnParse() {
		ServerParser serverParser = new ServerParser();
		ParserReader.getInstance().parse(serverParser, "serverTest.list");
		ArrayList<String> serverList = ParserReader.getInstance().getServerList();
		for (String host : serverList) {
			System.out.println(host);
		}
	}

	@Test
	public void testStoreResult() {
	}

}
