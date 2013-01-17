package com.dance.puppet.processor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * 
 * @author Chan Chen
 * 
 */
public class Processor {

	static Logger	logger	= Logger.getLogger(Processor.class.getName());

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
}