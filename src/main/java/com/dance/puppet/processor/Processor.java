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
public class Processor implements Runnable {

	static Logger logger = Logger.getLogger(Processor.class.getName());

	String username;
	String password;
	String command;
	String host;
	String port;

	PuppetInputStream fromServer;
	PuppetOutputStream toServer;
	String lastCommand = "";
	Channel channel;
	private static final String TERMINATOR = "puppet";

	public void run() {
		// connect to server
		try {
			connect(this.getUsername(), this.getPassword(), this.host, Integer.parseInt(this.port));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// send command to remote server
		if (isConnected()) {
			try {
				send(this.command);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			// send("echo \"\"");
			send("echo \"connect to $HOST_NAME suceessfully \"");
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

}