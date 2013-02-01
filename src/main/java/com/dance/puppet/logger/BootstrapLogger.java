package com.dance.puppet.logger;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BootstrapLogger {

	static final Logger	logger	= LoggerFactory.getLogger(BootstrapLogger.class);

	public void beforeMethod() {
		logger.info("before test");
	}

	public void afterMethod() {
		logger.info("after test");
	}

	public void addLog(JoinPoint j) {
		System.out.println("------------------Add Log----------------");
		Object obj[] = j.getArgs();
		for (Object o : obj) {
			System.out.println(o);
		}
		System.out.println("========checkSecurity==" + j.getSignature().getName());
	}
}
