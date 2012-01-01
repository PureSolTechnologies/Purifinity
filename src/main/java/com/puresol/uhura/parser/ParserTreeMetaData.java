package com.puresol.uhura.parser;

import java.io.Serializable;

import com.puresol.utils.ObjectUtilities;

/**
 * This class is used to store meta information for each node in an AST.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class ParserTreeMetaData implements Serializable, Cloneable {

    private static final long serialVersionUID = 7846977139079079374L;

    private final String sourceName;
    private final int line;
    private final int lineNum;
    private final int hashcode;

    public ParserTreeMetaData(String sourceName, int line, int lineNum) {
	super();
	this.sourceName = sourceName;
	this.line = line;
	this.lineNum = lineNum;

	hashcode = ObjectUtilities.calculateConstantHashCode(sourceName, line,
		lineNum);
    }

    public String getSourceName() {
	return sourceName;
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
	ParserTreeMetaData other = (ParserTreeMetaData) obj;
	if (sourceName == null) {
	    if (other.sourceName != null)
		return false;
	} else if (!sourceName.equals(other.sourceName))
	    return false;
	if (line != other.line)
	    return false;
	if (lineNum != other.lineNum)
	    return false;
	return true;
    }

    @Override
    public String toString() {
	String string = sourceName.toString() + ": " + line;
	if (lineNum > 1) {
	    string += " - " + (line + lineNum - 1);
	}
	return string;
    }

    @Override
    public ParserTreeMetaData clone() {
	return new ParserTreeMetaData(sourceName, line, lineNum);
    }
}
