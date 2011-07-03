package com.puresol.uhura.lexer;

import java.io.File;
import java.io.Serializable;

/**
 * This class represents a single line of source code taken from a source code
 * file. This is used to read a file into a list of such objects to run the
 * preprocessor over it and to lex it afterwards.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SourceCodeLine implements Serializable, Cloneable {

	private static final long serialVersionUID = -3788483766559761026L;

	private final File file;
	private final int lineNumber;
	private final String line;
	private final int hashCode;

	public SourceCodeLine(File file, int lineNumber, String line) {
		super();
		this.file = file;
		this.lineNumber = lineNumber;
		this.line = line;
		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + ((line == null) ? 0 : line.hashCode());
		result = prime * result + lineNumber;
		hashCode = result;
	}

	public File getFile() {
		return file;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public String getLine() {
		return line;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SourceCodeLine other = (SourceCodeLine) obj;
		if (hashCode != other.hashCode) {
			return false;
		}
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (line == null) {
			if (other.line != null)
				return false;
		} else if (!line.equals(other.line))
			return false;
		if (lineNumber != other.lineNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return line + " (" + file + ":" + lineNumber + ")";
	}

	@Override
	public SourceCodeLine clone() {
		return new SourceCodeLine(file, lineNumber, line);
	}
}
