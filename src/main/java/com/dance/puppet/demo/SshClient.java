package com.dance.puppet.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.regex.Pattern;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SshClient {
	BufferedReader							fromServer;
	Pattern											alphaNumeric	= Pattern.compile("([^a-zA-z0-9])");
	OutputStream								toServer;
	String											lastCommand		= "";
	Channel											channel;

	private static final String	TERMINATOR		= "zDonez";

	public static void main(String[] args) throws JSchException, IOException, InterruptedException {
		/*
		 * SshClient sshClient = new SshClient(); sshClient.connect("cchen",
		 * "Cc12345", "cchen-linux.corp.walmart.com", 22);
		 * System.out.println(sshClient.getServerResponse());
		 * System.out.println("main end");
		 */
		SshClient con = new SshClient();
		con.connect("cchen", "Cc12345", "cchen-linux.corp.walmart.com", 22);
		System.out.println(con.getServerResponse());
		if (con.isConnected()) {
			con.send("ls -l");
		}
		System.out.println(con.getServerResponse());
		con.disconnect();
	}

	public void connect(String username, String password, String host, int port) throws JSchException,
			IOException {
		JSch shell = new JSch();
		Session session = shell.getSession(username, host, port);
		MyUserInfo ui = new MyUserInfo();
		ui.setPassword(password);
		session.setUserInfo(ui);
		session.connect();

		channel = session.openChannel("shell");
		fromServer = new BufferedReader(new InputStreamReader(channel.getInputStream()));
		toServer = channel.getOutputStream();
		channel.connect();
		if (isConnected()) {
			send("echo \"\"");
		}
	}

	public boolean isConnected() {
		// TODO Auto-generated method stub
		return (channel != null && channel.isConnected());
	}

	public void disconnect() {
		if (isConnected()) {
			channel.disconnect();
		}

	}

	public void send(String command) throws IOException {
		command += "; ls -l; echo \"" + TERMINATOR + "\"\n";
		toServer.write(command.getBytes());
		toServer.flush();
		lastCommand = new String(command);
	}

	public String getServerResponse() throws IOException, InterruptedException {
		StringBuilder builder = new StringBuilder();
		int count = 0;
		String line = "";
		while ((line = fromServer.readLine()) != null) {
			System.out.println(line);
			builder.append(line).append("\n");
			if (line.contains(TERMINATOR) && (++count > 1)) {
				break;
			}
		}
		/*
		 * for (int i = 0; true; i++) { line = fromServer.readLine();
		 * builder.append(line).append("\n"); if (line.contains(TERMINATOR) &&
		 * (++count > 1)) { break; } }
		 */
		String result = builder.toString();

		int beginIndex = result.indexOf(TERMINATOR + "\"") + ((TERMINATOR + "\"").length());
		result = result.substring(beginIndex);
		return result.replaceAll(escape(TERMINATOR), "").trim();
	}

	private String escape(String subjectString) {
		return alphaNumeric.matcher(subjectString).replaceAll("\\\\$1");
	}

	private static class MyUserInfo implements UserInfo {
		private String	password;

		public void setPassword(String password) {
			this.password = password;

		}

		public String getPassphrase() {
			// TODO Auto-generated method stub
			return null;
		}

		public String getPassword() {
			// TODO Auto-generated method stub
			return password;
		}

		public boolean promptPassword(String arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean promptPassphrase(String arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		public boolean promptYesNo(String arg0) {
			// TODO Auto-generated method stub
			return true;
		}

		public void showMessage(String arg0) {
			// TODO Auto-generated method stub
			System.out.println(arg0);
		}

	}

}
