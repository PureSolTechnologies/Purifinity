package com.puresoltechnologies.parsers.api.source;

import java.io.Serializable;

/**
 * This class represents a single line of source code taken from a source code
 * file. This is used to read a file into a list of such objects to run the
 * preprocessor over it and to lex it afterwards.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public interface SourceCodeLine extends Serializable, Cloneable {

	public CodeLocation getSource();

	public int getLineNumber();

	public String getLine();

}
