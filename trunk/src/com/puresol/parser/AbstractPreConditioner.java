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

    private InputStream inputStream = null;
    private TokenStream tokenStream = null;

    public AbstractPreConditioner(File directory, File file)
	    throws FileNotFoundException {
	this.inputStream = new FileInputStream(Files.addPaths(directory, file));
    }

    public AbstractPreConditioner(InputStream stream) {
	this.inputStream = stream;
    }

    public InputStream getInputStream() {
	return inputStream;
    }

    public TokenStream getTokenStream() throws IOException {
	if (tokenStream == null) {
	    generateTokenStream();
	}
	return tokenStream;
    }

    protected void setTokenStream(TokenStream stream) {
	this.tokenStream = stream;
    }

    protected abstract void generateTokenStream() throws IOException;
}