package com.dance.puppet.processor;

import java.io.IOException;
import java.util.ArrayList;

import com.jcraft.jsch.JSchException;

public abstract class IProcessor implements Runnable {

	abstract void connect(String username, String password, String host, int port) throws JSchException,
			IOException;

	abstract boolean isConnected();

	abstract void disconnect();

	abstract void sendCommand(String command) throws IOException;

	abstract String fetchResponse() throws IOException, InterruptedException;

	abstract String getUsername();

	abstract void setUsername(String username);

	abstract String getPassword();

	abstract void setPassword(String password);

	abstract String getCommand();

	abstract void setCommand(String command);

	abstract ArrayList<String> getCommandList();

	abstract void setCommandList(ArrayList<String> command);

	abstract String getHost();

	abstract void setHost(String host);

	abstract String getPort();

	abstract void setPort(String port);
}
