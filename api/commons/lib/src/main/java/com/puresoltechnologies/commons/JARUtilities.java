package com.puresoltechnologies.commons;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class provides some utilities for handling jar files and their content.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class JARUtilities {

	public static void copyResource(URL resource, File destination)
			throws IOException {
		InputStream inStream = resource.openStream();
		if (inStream == null) {
			throw new IOException("No resource with the name '"
					+ resource.toString() + "' found!");
		}
		try {
			File parent = destination.getParentFile();
			if (parent != null) {
				if (!parent.exists()) {
					parent.mkdirs();
				}
			}
			FileOutputStream outStream = new FileOutputStream(destination);
			try {
				byte[] buffer = new byte[1024];
				int amount;
				while ((amount = inStream.read(buffer)) >= 0) {
					outStream.write(buffer, 0, amount);
				}
			} finally {
				outStream.close();
			}
		} finally {
			inStream.close();
		}
	}

	/**
	 * This class reads a properties file and replaces constructs surrounded by
	 * ${ and } by a system properties specified within the curly brackets. For
	 * example, the construct ${user.home} is replaced by the used home
	 * directory.
	 * 
	 * @param url
	 *            is the url to be read.
	 * @return Returns a Properties object containing the properties.
	 * @throws IOException
	 *             is thrown in cases of IO failures.
	 */
	public static Properties readPropertyFile(URL url) throws IOException {
		if (url == null) {
			return new Properties();
		}
		InputStream inputStream = url.openStream();
		if (inputStream == null) {
			throw new IOException("No resource with the name '"
					+ url.toString() + "' found!");
		}
		try {
			Properties properties = new Properties();
			properties.load(inputStream);
			Pattern pattern = Pattern.compile("\\$\\{([\\w.]+)\\}");
			for (Object key : properties.keySet()) {
				String value = (String) properties.get(key);
				while (true) {
					Matcher matcher = pattern.matcher(value);
					if (!matcher.find()) {
						break;
					}
					String string = matcher.group(1);
					String propertyValue = System.getProperty(string);
					value = value.replaceAll("\\$\\{" + string + "\\}",
							propertyValue);
				}
				properties.put(key, value);
			}
			return properties;
		} finally {
			inputStream.close();
		}
	}

	public static String readResourceFileToString(URL url) throws IOException {
		InputStream inStream = url.openStream();
		if (inStream == null) {
			throw new IOException("No resource with the name '"
					+ url.toString() + "' found!");
		}
		try {
			byte buffer[] = new byte[1024];
			StringBuffer stringBuffer = new StringBuffer();
			int length;
			length = inStream.read(buffer);
			while (length > 0) {
				stringBuffer.append(buffer);
				length = inStream.read(buffer);
			}
			return stringBuffer.toString();
		} finally {
			inStream.close();
		}
	}
}
