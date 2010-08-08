package com.puresol.parser.preconditioner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * PreConditioner is for reading an InputStream and creating a basic token
 * stream. This is needed for all parsing which is related on additional
 * information like parsing line depended input like fortran source code.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PreConditioner {

	private InputStream stream;

	public PreConditioner(File file) throws FileNotFoundException {
		this.stream = new FileInputStream(file);
	}

	public PreConditioner(InputStream stream) {
		this.stream = stream;
	}

	public InputStream getStream() {
		return stream;
	}
}
