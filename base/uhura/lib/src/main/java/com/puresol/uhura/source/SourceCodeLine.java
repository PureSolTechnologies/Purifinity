package com.puresol.uhura.source;

import java.io.Serializable;

import com.puresol.utils.ObjectUtilities;

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

    private final CodeLocation source;
    private final int lineNumber;
    private final String line;
    private final int hashCode;

    public SourceCodeLine(CodeLocation source, int lineNumber, String line) {
	super();
	this.source = source;
	this.lineNumber = lineNumber;
	this.line = line;
	hashCode = ObjectUtilities.calculateConstantHashCode(source,
		lineNumber, line);
    }

    public CodeLocation getSource() {
	return source;
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
	if (source == null) {
	    if (other.source != null)
		return false;
	} else if (!source.equals(other.source))
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
	return line + " (" + source + ":" + lineNumber + ")";
    }

    @Override
    public SourceCodeLine clone() {
	return new SourceCodeLine(source, lineNumber, line);
    }
}
