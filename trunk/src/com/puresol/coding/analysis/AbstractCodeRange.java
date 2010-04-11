package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;

/**
 * This is a base implementation of CodeRange with all information always
 * available for all code ranges. Specific code ranges should extend this class.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractCodeRange implements CodeRange {

	private static final long serialVersionUID = 2623881586176818439L;

	private final String name;
	private final TokenStream tokenStream;
	private String text;
	private int start; // not final for sub range creation!
	private int stop; // not final for sub range creation!

	public AbstractCodeRange(String name, TokenStream tokenStream, int start,
			int stop) {
		this.name = name;
		this.tokenStream = tokenStream;
		setPosition(start, stop);
	}

	private void setPosition(int start, int stop) {
		this.start = start;
		this.stop = stop;
		createText();
	}

	private void createText() {
		text = "";
		for (int index = start; index <= stop; index++) {
			text += tokenStream.get(index).getText();
		}
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
		for (int index = getStartId(); index <= getStopId(); index++) {
			tokens.add(tokenStream.get(index));
		}
		return tokens;
	}

	public int getStartId() {
		return start;
	}

	public int getStopId() {
		return stop;
	}

	public int getStartLine() {
		return tokenStream.get(getStartId()).getStartLine();
	}

	public int getStopLine() {
		return tokenStream.get(getStopId()).getStopLine();
	}

	public String getTitleString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return getTextTitleString();
		} else if (format == ReportingFormat.HTML) {
			return getHTMLTitleString();
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String getTextTitleString() {
		return getType() + ": " + getName() + "\n" + getFile() + ": "
				+ getStartId() + "-" + getStopId();
	}

	private String getHTMLTitleString() {
		String output = "<b>" + getType() + ": " + getName() + "</b><br/>\n";
		output += "<i>" + getFile() + ": " + getStartLine() + "-"
				+ getStopLine() + "</i>\n";
		return output;
	}

	public String toString(ReportingFormat format)
			throws UnsupportedReportingFormatException {
		if (format == ReportingFormat.TEXT) {
			return toTextString();
		} else if (format == ReportingFormat.HTML) {
			return toHTMLString();
		}
		throw new UnsupportedReportingFormatException(format);
	}

	private String toTextString() {
		return getTextTitleString() + "\n" + getText();
	}

	private String toHTMLString() {
		String output = "<p>\n";
		output += getHTMLTitleString() + "<br/>\n";
		output += "<br/>\n";
		output += HTMLStandards.convertSourceCodeToHTML(getText());
		return output;
	}

	public CodeRange createPartialCodeRange(int newStart, int newStop) {
		AbstractCodeRange newRange = (AbstractCodeRange) this.clone();
		newRange.setPosition(newStart, newStop);
		return newRange;
	}

	@Override
	public int compareTo(CodeRange other) {
		return this.getType().compareTo(other.getType());
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
