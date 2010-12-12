package com.puresol.io;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {

	private final StringBuffer string = new StringBuffer();

	@Override
	public void write(int b) throws IOException {
		string.append(Character.valueOf((char) b));
	}

	@Override
	public String toString() {
		return string.toString();
	}

}
