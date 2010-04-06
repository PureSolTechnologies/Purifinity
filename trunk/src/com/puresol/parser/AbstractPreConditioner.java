package com.puresol.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.puresol.utils.Files;

/**
 * PreConditioner is for reading an InputStream and creating a basic token
 * stream. This is needed for all parsing which is related on additional
 * information like parsing line depended input like fortran source code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractPreConditioner {

	private final InputStream inputStream;
	private final TokenStream tokenStream;

	public AbstractPreConditioner(File directory, File file)
			throws FileNotFoundException {
		this.inputStream = new FileInputStream(Files.addPaths(directory, file));
		this.tokenStream = new TokenStream(file);
	}

	public AbstractPreConditioner(InputStream stream, File file) {
		this.inputStream = stream;
		this.tokenStream = new TokenStream(file);
	}

	public final InputStream getInputStream() {
		return inputStream;
	}

	public final TokenStream getTokenStream() throws IOException {
		if (tokenStream == null) {
			generateTokenStream();
		}
		return tokenStream;
	}

	/**
	 * This method is used to add tokens to the token stream to be generated.
	 * 
	 * @param token
	 *            to be added.
	 */
	protected final void addToken(Token token) {
		tokenStream.addToken(token);
	}

	protected abstract void generateTokenStream() throws IOException;
}