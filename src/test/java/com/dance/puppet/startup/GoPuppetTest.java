package com.dance.puppet.startup;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dance.puppet.util.MainArgsParser;

public class GoPuppetTest {
	static final Logger	logger	= LoggerFactory.getLogger(GoPuppetTest.class);

	public static void main(String[] args) {
		BeanFactory factory = new ClassPathXmlApplicationContext("puppet-aop.xml");
		// logger.info("==================================");
		// logger.info("Start Puppet");
		// logger.info("==================================");
		// logger.info("");
		//
		// logger.info("Parse Main Args...");
		Map<String, String> mainArgs = MainArgsParser.parse(args);
		// Bootstrap bootStrap = new Bootstrap();
		// bootStrap.loadConfig(mainArgs.get("-u"), mainArgs.get("-p"),
		// mainArgs.get("-s"), mainArgs.get("-c"))
		// .parseServer().parseScript().fireEngine();
		Bootstrap bootstrap = (Bootstrap) factory.getBean("bootstrap");

		bootstrap.test();
		// .loadConfig("cchen", "Cc12345", "server.list",
		// "scriptParserTest.sh").parseServer()
		// .parseScript().fireEngine();
	}
}
