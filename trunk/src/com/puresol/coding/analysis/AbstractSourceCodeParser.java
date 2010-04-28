package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.parser.AbstractParser;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.Token;
import com.puresol.parser.TokenStream;
import com.puresol.reporting.ReportingFormat;
import com.puresol.reporting.html.HTMLStandards;
import com.puresol.utils.di.DependencyInjection;
import com.puresol.utils.di.Inject;
import com.puresol.utils.di.Injection;

/**
 * This abstract class extents the normal parser. Added is the functionality to
 * track a symbol table and to handle parts of the source code as code ranges.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractSourceCodeParser extends AbstractParser implements
		CodeRange {

	private static final long serialVersionUID = -5646508377634922308L;

	@Inject("SymbolTable")
	private SymbolTable symbols = null;

	protected AbstractSourceCodeParser() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Parser createParserInstance(Class<? extends Parser> clazz)
			throws ParserException {
		Parser parser = super.createParserInstance(clazz);
		DependencyInjection.inject(parser, Injection.named("SymbolTable",
				symbols));
		return parser;
	}

	@Override
	public final void addCodeRange(CodeRange codeRange) {
		if (!hasChildParser(codeRange)) {
			addChildParser(codeRange);
		}
	}

	@Override
	public final List<CodeRange> getChildCodeRanges() {
		return getChildParsers(CodeRange.class);
	}

	@Override
	public final <T> List<T> getChildCodeRanges(Class<T> codeRangeClass) {
		List<T> ranges = new ArrayList<T>();
		for (Parser parser : getChildParsers(CodeRange.class)) {
			if (codeRangeClass.isAssignableFrom(parser.getClass())) {
				@SuppressWarnings("unchecked")
				T t = (T) parser;
				ranges.add(t);
			}
		}
		return ranges;
	}

	@Override
	public final CodeRange getParentCodeRange() {
		return (CodeRange) getParentParser();
	}

	public final SymbolTable getSymbolTable() {
		return symbols;
	}

	@Override
	public int compareTo(CodeRange other) {
		return this.getCodeRangeType().compareTo(other.getCodeRangeType());
	}

	@Override
	public final File getFile() {
		return getTokenStream().getFile();
	}

	@Override
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
		return getCodeRangeType() + ": " + getName() + "\n" + getFile() + ": "
				+ getStartId() + "-" + getStopId();
	}

	private String getHTMLTitleString() {
		String output = "<b>" + getCodeRangeType() + ": " + getName()
				+ "</b><br/>\n";
		output += "<i>" + getFile() + ": " + getStartLine() + "-"
				+ getStopLine() + "</i>\n";
		return output;
	}

	@Override
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

	@Override
	public final int getStartLine() {
		return getTokenStream().get(getStartId()).getStartLine();
	}

	@Override
	public final int getStopLine() {
		return getTokenStream().get(getStopId()).getStopLine();
	}

	@Override
	public final CodeRange createPartialCodeRange(int newStartPosition,
			int newEndPosition) {
		AbstractSourceCodeParser parser = (AbstractSourceCodeParser) clone();
		DependencyInjection
				.inject(parser, Injection.named("StartPosition",
						newStartPosition), Injection.named("CurrentPosition",
						newEndPosition), Injection.named("EndPosition",
						newEndPosition));
		return parser;
	}

	@Override
	public final int getStartId() {
		return getStartPosition();
	}

	@Override
	public final int getStopId() {
		return getEndPosition();
	}

	@Override
	public final String getText() {
		String text = "";
		TokenStream tokenStream = getTokenStream();
		for (int index = getStartId(); index <= getStopId(); index++) {
			text += tokenStream.get(index).getText();
		}
		return text;
	}

	@Override
	public final ArrayList<Token> getTokens() {
		TokenStream tokenStream = getTokenStream();
		ArrayList<Token> tokens = new ArrayList<Token>();
		for (int index = getStartId(); index <= getStopId(); index++) {
			tokens.add(tokenStream.get(index));
		}
		return tokens;
	}

	@Override
	public Object clone() {
		try {
			AbstractSourceCodeParser cloned = (AbstractSourceCodeParser) super
					.clone();
			cloned.symbols = (SymbolTable) this.symbols.clone();
			return cloned;
		} catch (CloneNotSupportedException e) {
			throw new StrangeSituationException(e);
		}
	}

	/**
	 * This method is used to finish the scan and to trigger adding to the
	 * parents sub code range list and expanding the start and stop ids
	 * accordingly.
	 * 
	 * @param name
	 *            is the name to be given to this code range.
	 */
	protected final void finish() {
		int startPosition = getStartPositionWithLeadingHidden();
		int stopPosition = getPositionOfLastVisible();

		setStartPosition(startPosition);
		setCurrentPosition(stopPosition);
		setEndPosition(stopPosition);

		super.finish();
	}

	/**
	 * The child is born when a name was given. Therefore, this method is used
	 * to finish the scan and to trigger adding to the parents sub code range
	 * list and expanding the start and stop ids accordingly.
	 * 
	 * @param name
	 *            is the name to be given to this code range.
	 */
	protected final void finish(String name) {
		setName(name);
		finish();
	}

	@Override
	public String toString() {
		return getCodeRangeType().getIdentifier() + " '" + getName() + "'";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((symbols == null) ? 0 : symbols.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractSourceCodeParser other = (AbstractSourceCodeParser) obj;
		if (symbols == null) {
			if (other.symbols != null)
				return false;
		} else if (!symbols.equals(other.symbols))
			return false;
		return true;
	}

}
