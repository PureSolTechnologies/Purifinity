package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;

/**
 * This is a base implementation of CodeRange with all information always
 * available for all code ranges. Specific code ranges should extend this class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractCodeRange implements CodeRange {

	private final String name;
	private final String text;
	private final TokenStream tokenStream;
	private int start; // not final for sub range creation!
	private int stop; // not final for sub range creation!

	public AbstractCodeRange(String name, TokenStream tokenStream, int start,
			int stop) {
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
		return tokenStream.getFile();
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

	public CodeRange createPartialCodeRange(int newStart, int newStop) {
		AbstractCodeRange newRange = (AbstractCodeRange) this.clone();
		newRange.start = newStart;
		newRange.stop = newStop;
		return newRange;
	}

	@Override
	public int compareTo(CodeRange other) {
		return toString().compareTo(other.toString());
	}

	@Override
	public Object clone() {
		try {
			AbstractCodeRange cloned = (AbstractCodeRange) super.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new StrangeSituationException(e);
		}
	}
}
