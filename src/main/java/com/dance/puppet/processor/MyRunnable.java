package com.dance.puppet.processor;

/**
 * 
 * @author Chan Chen
 *
 */
public class MyRunnable implements Runnable {
	private String user;
	private String password;
	private String cmd;
	private String server;
	
	public MyRunnable(String user, String password, String server, String cmd) {
		this.user = user;
		this.password = password;
		this.cmd = cmd;
		this.server = server;
	}
	
	public MyRunnable(String cmd) {
		this.cmd = cmd;
	}

	public void run() {
		Processor.execute(user, password, server, cmd);
	}
}
