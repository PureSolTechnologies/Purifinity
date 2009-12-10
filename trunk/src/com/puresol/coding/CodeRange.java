package com.puresol.coding;

import java.util.Hashtable;

import org.antlr.runtime.TokenStream;

public class CodeRange {

	private CodeRangeType type;
	private String name;
	private String text;
	private TokenStream tokenStream;
	private Hashtable<Integer, TokenContent> tokenContents;
	private int start;
	private int stop;

	public CodeRange(CodeRangeType type, String name, String text,
			TokenStream tokenStream,
			Hashtable<Integer, TokenContent> tokenContents, int start, int stop) {
		this.type = type;
		this.name = name;
		this.text = text;
		this.tokenStream = tokenStream;
		this.tokenContents = tokenContents;
		this.start = start;
		this.stop = stop;
	}

	public CodeRangeType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public TokenStream getTokenStream() {
		return tokenStream;
	}

	public Hashtable<Integer, TokenContent> getTokenContents() {
		return tokenContents;
	}

	public int getStart() {
		return start;
	}

	public int getStop() {
		return stop;
	}

	public String toString() {
		return getType() + ": " + getName() + "\n" + getText();
	}
}
