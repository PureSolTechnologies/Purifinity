package com.puresol.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class DefaultPreConditioner extends AbstractPreConditioner {

	public DefaultPreConditioner(File file) throws IOException {
		super(file);
	}

	public DefaultPreConditioner(File file, InputStream stream)
			throws IOException {
		super(stream, file);
	}

	@Override
	protected void generateTokenStream() throws IOException {
		addToken(Token.createPrimitiveFromString(0, 0, 0, readStream()));
	}

	private String readStream() throws IOException {
		StringBuffer text = new StringBuffer();
		InputStream stream = getInputStream();
		byte[] buffer = new byte[1024];
		int size;
		while ((size = stream.read(buffer)) >= 0) {
			text.append(new String(buffer, 0, size));
		}
		return text.toString();
	}
}
