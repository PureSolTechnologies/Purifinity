package com.puresol.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

import com.puresol.utils.Files;

public class DefaultPreConditioner extends AbstractPreConditioner {

	private final File directory;
	private final File file;

	public DefaultPreConditioner(File directory, File file)
			throws FileNotFoundException {
		super(Files.addPaths(directory, file));
		this.directory = directory;
		this.file = file;
	}

	public DefaultPreConditioner(File directory, File file, InputStream stream) {
		super(stream);
		this.directory = directory;
		this.file = file;
	}

	public File getFile() {
		return file;
	}

	public File getDirectory() {
		return directory;
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
		InputStream stream = getInputStream();
		byte[] buffer = new byte[1024];
		int size;
		while ((size = stream.read(buffer)) >= 0) {
			text.append(new String(buffer, 0, size));
		}
		return text.toString();
	}
}
