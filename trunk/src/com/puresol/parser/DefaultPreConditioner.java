package com.puresol.parser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swingx.data.LineEnd;

public class DefaultPreConditioner extends AbstractPreConditioner {

    public DefaultPreConditioner(File directory, File file) throws IOException {
	super(directory, file);
    }

    public DefaultPreConditioner(File directory, File file, InputStream stream)
	    throws IOException {
	super(stream, file);
    }

    @Override
    protected void generateTokenStream() throws IOException {
	String text = readStream();
	Token token = new Token(0, TokenPublicity.VISIBLE, 0, text.length(),
		text, 0, text.split(LineEnd.UNIX.getString()).length - 1, null);
	addToken(token);
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
