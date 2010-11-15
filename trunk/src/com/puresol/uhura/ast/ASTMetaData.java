package com.puresol.uhura.ast;

import java.io.File;

/**
 * This class is used to store meta information for each node in an AST.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ASTMetaData {

	private final File file;
	private final int line;
	private final int lineNum;
	private final int hashcode;

	public ASTMetaData(File file, int line, int lineNum) {
		super();
		this.file = file;
		this.line = line;
		this.lineNum = lineNum;

		final int prime = 31;
		int result = 1;
		result = prime * result + ((file == null) ? 0 : file.hashCode());
		result = prime * result + line;
		result = prime * result + lineNum;
		hashcode = result;
	}

	public File getFile() {
		return file;
	}

	public int getLine() {
		return line;
	}

	public int getLineNum() {
		return lineNum;
	}

	@Override
	public int hashCode() {
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ASTMetaData other = (ASTMetaData) obj;
		if (file == null) {
			if (other.file != null)
				return false;
		} else if (!file.equals(other.file))
			return false;
		if (line != other.line)
			return false;
		if (lineNum != other.lineNum)
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = file.toString() + ": " + line;
		if (lineNum > 1) {
			string += " - " + (line + lineNum - 1);
		}
		return string;
	}
}
