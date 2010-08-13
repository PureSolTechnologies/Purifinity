package com.puresol.uhura.lexer;

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
public class TokenMetaData {

	private final Token token;
	private final int id;
	private final int pos;
	private final int line;
	private final int lineNum;

	public TokenMetaData(Token token, int id, int pos, int line) {
		super();
		this.token = token;
		this.id = id;
		this.pos = pos;
		this.line = line;
		int lineCounter = 1;
		if (token.getText().contains("\r") || token.getText().contains("\n")) {
			lineCounter += token.getText().split("(\\r\\n|\\n|\\r)").length - 1;
		}
		lineNum = lineCounter;
	}

	/**
	 * @return the token
	 */
	public Token getToken() {
		return token;
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
		result += "pos: " + pos + " (" + token.getText().length() + "), ";
		if (lineNum == 1) {
			result += "line: " + line + ": ";
		} else {
			result += "lines: " + line + " - " + (line + lineNum - 1) + ": ";
		}
		result += token.toString();
		return result;
	}
}
