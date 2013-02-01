package com.dance.puppet.processor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dance.puppet.io.PuppetBufferdReader;
import com.dance.puppet.util.TextFormatUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * Processor Class is the central of request and response in puppet, seting up
 * InputStram, OouputStream, Connection and Disconnection
 * 
 * @author Chan Chen
 * 
 */
public class NormalProcessor extends IProcessor {

	static final Logger					logger			= LoggerFactory.getLogger(NormalProcessor.class.getName());

	private String							username;
	private String							password;
	private String							command;
	private ArrayList<String>		commandList;
	private String							host;
	private String							port;

	private InputStream					fromServer;
	private OutputStream				toServer;
	private String							lastCommand	= "";
	private Channel							channel;

	private static final String	TERMINATOR	= "puppet";

	public void run() {
		// connect to server
		try {
			connect(this.getUsername(), this.getPassword(), this.host, Integer.parseInt(this.port));
			logger.info(fetchResponse());

			if (isConnected()) {
				for (String cmd : getCommandList()) {
					if (cmd.trim().equalsIgnoreCase("")) {
						continue;
					}
					logger.info(cmd);
					sendCommand(cmd);
					// sendCommand(getCommand());
					logger.info(fetchResponse());
				}
			}

			logger.info("try to disconnect...");
			disconnect();
			logger.info("disconnect successfully");
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
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
		fromServer = channel.getInputStream();
		toServer = channel.getOutputStream();

		channel.connect();
		if (isConnected()) {
			sendCommand("echo \"connect to $HOSTNAME suceessfully \"");
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

	public void sendCommand(String command) throws IOException {
		command += "; echo \"" + TERMINATOR + "\"\n";
		logger.info(command);
		toServer.write(command.getBytes());
		toServer.flush();
		lastCommand = new String(command);
	}

	public void sendCommand(ArrayList<String> commandList) {

	}

	public String fetchResponse() throws IOException, InterruptedException {
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
		// return result;
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

	public ArrayList<String> getCommandList() {
		return commandList;
	}

	public void setCommandList(ArrayList<String> commandList) {
		this.commandList = commandList;
	}

}