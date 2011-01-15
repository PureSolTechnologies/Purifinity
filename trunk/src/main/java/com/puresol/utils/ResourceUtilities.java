package com.puresol.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResourceUtilities {

	public static String readResourceFileToString(String resourceName)
			throws IOException {
		InputStream inStream = ResourceUtilities.class
				.getResourceAsStream(resourceName);
		if (inStream == null) {
			throw new IOException("No resource with the name '" + resourceName
					+ "' found!");
		}
		byte buffer[] = new byte[1024];
		StringBuffer stringBuffer = new StringBuffer();
		int length;
		length = inStream.read(buffer);
		while (length > 0) {
			stringBuffer.append(buffer);
			length = inStream.read(buffer);
		}
		return stringBuffer.toString();
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
	public static Properties readResourcePropertyFile(URL url)
			throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = url.openStream();
		try {
			properties.load(inputStream);
		} finally {
			inputStream.close();
		}
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
			properties.put((String) key, value);
		}
		return properties;
	}

}
