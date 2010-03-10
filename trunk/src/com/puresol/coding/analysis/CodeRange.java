/***************************************************************************
 *
 *   CodeRange.java
 *   -------------------
 *   copyright            : (c) 2009 by PureSol-Technologies
 *   author               : Rick-Rainer Ludwig
 *   email                : ludwig@puresol-technologies.com
 *
 ***************************************************************************/

package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.lang.Language;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

public final class CodeRange implements Comparable<CodeRange> {

    private final File file;
    private final Language language;
    private final CodeRangeType type;
    private final String name;
    private final String text;
    private final TokenStream tokenStream;
    private final int start;
    private final int stop;

    public CodeRange(File file, Language language, CodeRangeType type,
	    String name, TokenStream tokenStream, int start, int stop) {
	this.file = file;
	this.language = language;
	this.type = type;
	this.name = name;
	this.tokenStream = tokenStream;
	this.start = start;
	this.stop = stop;
	this.text = createText();
    }

    private String createText() {
	String text = "";
	for (int index = start; index <= stop; index++) {
	    text += tokenStream.get(index).getText();
	}
	return text;
    }

    public File getFile() {
	return file;
    }

    public Language getLanguage() {
	return language;
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

    public ArrayList<Token> getTokens() {
	ArrayList<Token> tokens = new ArrayList<Token>();
	for (int index = getStart(); index <= getStop(); index++) {
	    tokens.add(tokenStream.get(index));
	}
	return tokens;
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
