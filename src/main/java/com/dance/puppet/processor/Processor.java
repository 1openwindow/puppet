package com.dance.puppet.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.dance.puppet.io.PuppetBufferdReader;
import com.dance.puppet.io.PuppetInputStream;
import com.dance.puppet.io.PuppetOutputStream;
import com.dance.puppet.util.TextFormatUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Processor Class is the connect pool in puppet, seting up InputStram,
 * OouputStream, Connection and Disconnection
 * 
 * @author Chan Chen
 * 
 */
public class Processor {

	static Logger								logger			= Logger.getLogger(Processor.class.getName());

	PuppetInputStream						fromServer;
	PuppetOutputStream					toServer;
	String											lastCommand	= "";
	Channel											channel;
	private static final String	TERMINATOR	= "puppet";

	public static void execute(String user, String password, String server, String cmd) {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession(user, server, 22);
			session.setConfig("StrictHostKeyChecking", "no");
			session.setPassword(password);
			// set connection time out to 10000ms
			// session.connect();
			session.connect(10000);
			Channel channel = session.openChannel("exec");
			((ChannelExec) channel).setCommand(cmd);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			synchronized (ExecutorService.class) {
				byte[] tmp = new byte[1024];
				while (true) {
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						if (i < 0)
							break;
						logger.info(new String(tmp, 0, i));
					}
					if (channel.isClosed()) {
						logger.info("exit-status: " + channel.getExitStatus());
						break;
					}
				}
			}
			channel.disconnect();
			session.disconnect();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void connect(String username, String password, String host, int port) throws JSchException,
			IOException {
		JSch shell = new JSch();
		Session session = shell.getSession(username, host, 22);
		session.setConfig("StrictHostKeyChecking", "no");
		session.setPassword(password);
		session.connect();
		channel = session.openChannel("shell");
		fromServer = (PuppetInputStream) channel.getInputStream();
		toServer = (PuppetOutputStream) channel.getOutputStream();

		channel.connect();
		if (isConnected()) {
			send("echo \"\"");
		}
	}

	public boolean isConnected() {
		return (channel != null && channel.isConnected());
	}

	public void disconnect() {
		if (isConnected()) {
			channel.disconnect();
		}
	}

	public void send(String command) throws IOException {
		command += "; echo \"" + TERMINATOR + "\"\n";
		toServer.write(command.getBytes());
		toServer.flush();
		lastCommand = new String(command);
	}

	public String getServerResponse() throws IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		String line = "";
		PuppetBufferdReader reader = new PuppetBufferdReader(new InputStreamReader(fromServer));
		while ((line = reader.readLine()) != null) {
			builder.append(line).append("\n");
			if (line.contains(TERMINATOR) && (++count > 1)) {
				break;
			}
		}
		String result = builder.toString();
		int beginIndex = result.indexOf(TERMINATOR + "\"") + ((TERMINATOR + "\"").length());
		result = result.substring(beginIndex);
		return result.replaceAll(TextFormatUtil.escape(TERMINATOR), "").trim();
	}
}