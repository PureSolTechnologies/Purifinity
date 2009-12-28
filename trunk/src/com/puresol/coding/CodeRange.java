package com.puresol.coding;

import java.io.File;
import java.util.Hashtable;

import org.antlr.runtime.TokenStream;

public class CodeRange implements Comparable<CodeRange> {

    private File file;
    private CodeRangeType type;
    private String name;
    private String text;
    private TokenStream tokenStream;
    private Hashtable<Integer, TokenContent> tokenContents;
    private int start;
    private int stop;

    public CodeRange(File file, CodeRangeType type, String name,
	    TokenStream tokenStream,
	    Hashtable<Integer, TokenContent> tokenContents, int start,
	    int stop) {
	this.file = file;
	this.type = type;
	this.name = name;
	this.tokenStream = tokenStream;
	this.tokenContents = tokenContents;
	this.start = start;
	this.stop = stop;
	createText();
    }

    private void createText() {
	text = "";
	for (int index = start; index < stop; index++) {
	    text += tokenStream.get(index).getText();
	}
    }

    public File getFile() {
	return file;
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

    @Override
    public int compareTo(CodeRange other) {
	return toString().compareTo(other.toString());
    }
}
