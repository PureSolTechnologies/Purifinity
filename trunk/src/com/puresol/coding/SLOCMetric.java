package com.puresol.coding;

import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;

/**
 * This class calculates a small statistics for a source code for source lines
 * of code. It's a simple counting statistics for productive, comment and blank
 * lines.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class SLOCMetric {

	private TokenStream stream = null;
	private int start;
	private int stop;
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
	public SLOCMetric(TokenStream stream) {
		this.stream = stream;
		start = 0;
		stop = stream.size() - 1;
		calculate();
	}

	public SLOCMetric(CodeRange codeRange) {
		this.stream = codeRange.getTokenStream();
		start = codeRange.getStart();
		stop = codeRange.getStop();
		calculate();
	}

	private void calculate() {
		phyLOC = 0;
		proLOC = 0;
		comLOC = 0;
		blLOC = 0;
		for (int index = start; index <= stop; index++) {
			Token token = stream.get(index);
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
