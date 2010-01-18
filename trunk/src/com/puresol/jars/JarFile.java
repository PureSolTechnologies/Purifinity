package com.puresol.jars;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.log4j.Logger;

public class JarFile {

	private static final Logger logger = Logger.getLogger(JarFile.class);

	public static boolean extractResource(URL resource, File destination) {
		try {
			InputStream inStream = resource.openStream();
			FileOutputStream outStream = new FileOutputStream(destination);
			byte[] buffer = new byte[1024];
			while (inStream.read(buffer) >= 0) {
				outStream.write(buffer);
			}
			outStream.close();
			inStream.close();
			return true;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return false;
	}

	public static void main(String[] args) {
		JarFile.extractResource(JarFile.class
				.getResource("/META-INF/logo.jpeg"), new File(
				"/home/ludwig/logo.jpeg"));
	}

}
