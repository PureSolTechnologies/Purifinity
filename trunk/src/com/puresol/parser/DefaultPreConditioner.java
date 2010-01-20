package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

public class DefaultPreConditioner extends AbstractPreConditioner {

	private final File file;

	public DefaultPreConditioner(File file) throws FileNotFoundException {
		super(file);
		this.file = file;
	}

	public DefaultPreConditioner(File file, InputStream stream) {
		super(stream);
		this.file = file;
	}

	public File getName() {
		return file;
	}

	@Override
	protected void generateTokenStream() throws IOException {
		TokenStream tokenStream = new TokenStream(file);
		String text = readStream();
		Token token = new Token(0, TokenPublicity.VISIBLE, 0, text.length(),
				text, 0, text.split(LineEnd.UNIX.getString()).length - 1, null);
		tokenStream.addToken(token);
		setTokenStream(tokenStream);
	}

	private String readStream() throws IOException {
		StringBuffer text = new StringBuffer();
		InputStream stream = getStream();
		byte[] buffer = new byte[1024];
		int size;
		while ((size = stream.read(buffer)) >= 0) {
			text.append(new String(buffer, 0, size));
		}
		return text.toString();
	}
}
