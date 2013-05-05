package com.puresol.uhura.lexer;

import java.io.Serializable;

import com.puresol.uhura.source.CodeLocation;
import com.puresol.utils.ObjectUtilities;

/**
 * This class is used to store additional data for the tokens found like the
 * original position within the source input.
 * 
 * This information is kept away from the original token stream due to keep the
 * token stream small and more flexible. If tokens are moved around the
 * information about the tokens becomes obsolete which is not so very obvious.
 * If the information is kept separately, it's clear to keep an eye about the
 * validity of data.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TokenMetaData implements Serializable, Cloneable {

    private static final long serialVersionUID = 6478412339837934971L;

    /**
     * This is the location of the token. This needs to point to a file or
     * document, where the token is in. See {@link CodeLocation} for more
     * information.
     */
    private final CodeLocation source;
    /**
     * This is the line number within the containing document.
     * 
     * <b>Attention: This line numbers start with 1!</b>
     */
    private final int line;
    /**
     * This contains the number of lines which the token overspans. This is
     * normally the number of line terminators plus 1.
     */
    private final int lineNum;
    /**
     * This is a constant hash code for {@link #hashCode()}. It is calculated in
     * the constructor for fast access.
     */
    private final int hashcode;

    public TokenMetaData(CodeLocation source, int line, int lineNum) {
	super();
	this.source = source;
	this.line = line;
	this.lineNum = lineNum;
	hashcode = ObjectUtilities.calculateConstantHashCode(source, line,
		lineNum);
    }

    public CodeLocation getSource() {
	return source;
    }

    /**
     * @return the line
     */
    public int getLine() {
	return line;
    }

    /**
     * Returns the number of lines which are covered by a token. Generally,
     * tokens will return 1 here, but tokens like line break will return 2 and
     * multi line comments will return larger numbers.
     * 
     * @return the lineNum
     */
    public int getLineNum() {
	return lineNum;
    }

    @Override
    public String toString() {
	String result = "";
	if (lineNum == 1) {
	    result += "line: " + line;
	} else {
	    result += "lines: " + line + " - " + (line + lineNum - 1);
	}
	return result;
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
	TokenMetaData other = (TokenMetaData) obj;
	if (hashcode != other.hashcode)
	    return false;
	if (line != other.line)
	    return false;
	if (lineNum != other.lineNum)
	    return false;
	if (source == null) {
	    if (other.source != null)
		return false;
	} else if (!source.equals(other.source))
	    return false;
	return true;
    }

    @Override
    public TokenMetaData clone() {
	return new TokenMetaData(source, line, lineNum);
    }
}
