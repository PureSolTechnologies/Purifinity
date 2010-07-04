package com.puresol.utils;

import java.io.IOException;
import java.io.InputStream;

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

}
