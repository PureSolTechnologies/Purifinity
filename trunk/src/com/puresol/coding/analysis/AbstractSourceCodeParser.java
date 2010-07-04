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
		DependencyInjection.inject(parser,
				Injection.named("SymbolTable", symbols));
		return parser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void addChildCodeRange(CodeRange codeRange) {
		if ((codeRange != this) && (!hasChildParser(codeRange))) {
			addChildParser(codeRange);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<CodeRange> getChildCodeRanges() {
		return getChildParsers(CodeRange.class);
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CodeRange getParentCodeRange() {
		return (CodeRange) getParentParser();
	}

	public final SymbolTable getSymbolTable() {
		return symbols;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int compareTo(CodeRange other) {
		return this.getCodeRangeType().compareTo(other.getCodeRangeType());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final File getFile() {
		return getTokenStream().getFile();
	}

	/**
	 * {@inheritDoc}
	 */
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getReportingString(ReportingFormat format)
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStartLine() {
		return getTokenStream().get(getStartId()).getStartLine();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStopLine() {
		return getTokenStream().get(getStopId()).getStopLine();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final CodeRange createPartialCodeRange(int newStartPosition,
			int newEndPosition) {
		AbstractSourceCodeParser parser = (AbstractSourceCodeParser) clone();
		DependencyInjection.inject(parser,
				Injection.named("StartPosition", newStartPosition),
				Injection.named("CurrentPosition", newEndPosition),
				Injection.named("EndPosition", newEndPosition));
		return parser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStartId() {
		return getStartPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStopId() {
		return getEndPosition();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getText() {
		String text = "";
		TokenStream tokenStream = getTokenStream();
		for (int index = getStartId(); index <= getStopId(); index++) {
			if (tokenStream.getSize() <= index) {
				break;
			}
			text += tokenStream.get(index).getText();
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final List<Token> getTokens() {
		TokenStream tokenStream = getTokenStream();
		List<Token> tokens = new ArrayList<Token>();
		for (int index = getStartId(); index <= getStopId(); index++) {
			tokens.add(tokenStream.get(index));
		}
		return tokens;
	}

	/**
	 * Standard clone method for AbstractSourceCodeParser.
	 */
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
		String string = super.toString();
		string += "\n" + getCodeRangeType().getIdentifier() + " '" + getName()
				+ "'";
		return string;
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
