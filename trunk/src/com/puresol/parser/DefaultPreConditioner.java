package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

import com.puresol.exceptions.StrangeSituationException;

public class DefaultPreConditioner extends AbstractPreConditioner {

	private final String name;

	public DefaultPreConditioner(File file) throws FileNotFoundException {
		super(file);
		this.name = file.getPath();
	}

	public DefaultPreConditioner(String name, InputStream stream) {
		super(stream);
		this.name = name;
	}

	@Override
	protected void generateTokenStream() throws IOException {
		try {
			TokenStream tokenStream = new TokenStream(name, getStream());
			String text = readStream();
			Token token = new Token(getStream(), 0, TokenPublicity.VISIBLE, 0,
					text.length(), text, 0, text
							.split(LineEnd.UNIX.getString()).length - 1, null);
			tokenStream.addToken(token);
			setTokenStream(tokenStream);
		} catch (InvalidInputStreamException e) {
			throw new StrangeSituationException(e);
		}
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
