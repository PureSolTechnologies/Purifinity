package com.puresol.uhura.lexer;

import java.io.Serializable;

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

	private final int id;
	private final int pos;
	private final int line;
	private final int lineNum;
	private final int hashcode;

	public TokenMetaData(int id, int pos, int line, int lineNum) {
		super();
		this.id = id;
		this.pos = pos;
		this.line = line;
		this.lineNum = lineNum;
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + line;
		result = prime * result + lineNum;
		result = prime * result + pos;
		hashcode = result;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the pos
	 */
	public int getPos() {
		return pos;
	}

	/**
	 * @return the line
	 */
	public int getLine() {
		return line;
	}

	/**
	 * @return the lineNum
	 */
	public int getLineNum() {
		return lineNum;
	}

	@Override
	public String toString() {
		String result = "id: " + id + ", ";
		result += "pos: " + pos + ", ";
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
		if (id != other.id)
			return false;
		if (line != other.line)
			return false;
		if (lineNum != other.lineNum)
			return false;
		if (pos != other.pos)
			return false;
		return true;
	}

	@Override
	public TokenMetaData clone() {
		return new TokenMetaData(id, pos, line, lineNum);
	}
}
