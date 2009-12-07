package com.puresol.coding;

import java.util.List;

import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.Token;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCStatistics {

	private CommonTokenStream stream = null;
	private int phyLOC;
	private int proLOC;
	private int comLOC;
	private int blLOC;

	/**
	 * This constructor takes as argument a CommonTokenStream which comes out of
	 * a ANTLR lexer. The lexer should have marked the line feeds and has to
	 * hide the comments.
	 * 
	 * @param commonTokenStream
	 */
	public SLOCStatistics(CommonTokenStream stream) {
		this.stream = stream;
		calculate();
	}

	@SuppressWarnings("unchecked")
	private List<Token> getTokenList() {
		return ((List<Token>) stream.getTokens());
	}

	private void calculate() {
		phyLOC = 0;
		proLOC = 0;
		comLOC = 0;
		blLOC = 0;
		for (Token token : getTokenList()) {
			if (!token.getText().endsWith("\n")) {
				// it's not a end of line token and therefore to be skipped
				continue;
			}
			int lines = countLinesInToken(token);
			phyLOC += lines;
			if (isComment(token)) {
				comLOC += lines;
			}
			if (isBlank(token)) {
				blLOC += lines;
			}
			if (isProductive(token)) {
				proLOC += lines;
			}
		}
	}

	/**
	 * This method calculates how much lines are encoded in the current token
	 * and the tokens before which do not end with a new line.
	 * 
	 * @param token
	 * @return
	 */
	private int countLinesInToken(Token token) {
		int index = token.getTokenIndex();
		index--;
		int lines = 0;
		Token previous = stream.get(index);
		while (!previous.getText().endsWith("\n")) {
			lines += previous.getText().split("\n").length - 1;
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return lines + 1;
	}

	private boolean isComment(Token token) {
		/*
		 * A comment is a channel 99 with a trimmed non-empty string!
		 */
		int index = token.getTokenIndex();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getChannel() == 99) {
				if (!previous.getText().trim().isEmpty()) {
					return true;
				}
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return false;
	}

	private boolean isBlank(Token token) {
		/*
		 * A blank lines consists only of channel 99 tokens with are trimmed
		 * empty.
		 */
		int index = token.getTokenIndex();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getChannel() != 99) {
				return false;
			}
			if (!previous.getText().trim().isEmpty()) {
				return false;
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return true;
	}

	private boolean isProductive(Token token) {
		/*
		 * A productive line is a line with at least one token not in channel
		 * 99!
		 */
		int index = token.getTokenIndex();
		if (index == 0) {
			return false;
		}
		index--;
		Token previous = stream.get(index);
		while (!previous.getText().equals("\n")) {
			if (previous.getChannel() != 99) {
				return true;
			}
			index--;
			if (index < 0) {
				break;
			}
			previous = stream.get(index);
		}
		return false;
	}

	public int getPhyLOC() {
		return phyLOC;
	}

	public int getProLOC() {
		return proLOC;
	}

	public int getComLOC() {
		return comLOC;
	}

	public int getBlLOC() {
		return blLOC;
	}

	public void print() {
		System.out.println("physical lines: " + phyLOC);
		System.out.println("productive lines: " + proLOC);
		System.out.println("commented lines: " + comLOC);
		System.out.println("blank lines: " + blLOC);
	}
}
