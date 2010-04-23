package com.puresol.coding.analysis;

import java.io.File;
import java.util.ArrayList;

import com.puresol.coding.evaluator.UnsupportedReportingFormatException;
import com.puresol.exceptions.StrangeSituationException;
import com.puresol.parser.AbstractParser;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
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

    private final ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

    @Inject("SymbolTable")
    private SymbolTable symbols = null;

    private CodeRangeType type;
    private String name;
    
    protected AbstractSourceCodeParser() {
	super();
    }

    @Override
    protected Parser createParserInstance(Class<? extends Parser> clazz)
	    throws ParserException {
	Parser parser = super.createParserInstance(clazz);
	DependencyInjection.inject(parser, Injection.named("SymbolTable",
		symbols));
	return parser;
    }

    protected final void addCodeRange(CodeRange codeRange) {
	if (!codeRanges.contains(codeRange)) {
	    codeRanges.add(codeRange);
	}
    }

    protected final void addCodeRanges(ArrayList<CodeRange> codeRanges) {
	this.codeRanges.addAll(codeRanges);
    }

    @Override
    public final ArrayList<CodeRange> getCodeRanges() {
	return codeRanges;
    }

    public final void setSymbolTable(SymbolTable symbols) {
	this.symbols = symbols;
    }

    public final SymbolTable getSymbolTable() {
	return symbols;
    }

    @Override
    protected final void expectPart(Class<? extends Parser> part,
	    boolean moveForward) throws PartDoesNotMatchException,
	    ParserException {
	try {
	    if (AbstractSourceCodeParser.class.isAssignableFrom(part)) {
		AbstractSourceCodeParser parser = (AbstractSourceCodeParser) createParserInstance(part);
		parser.setSymbolTable(symbols);
		parser.scan();
		if (parser instanceof AbstractSourceCodeParser) {
		    ArrayList<CodeRange> ranges = ((AbstractSourceCodeParser) parser)
			    .getCodeRanges();
		    addCodeRanges(ranges);
		}
		if (moveForward) {
		    moveToNextVisible(parser.getNumberOfTokens());
		}
	    } else {
		super.expectPart(part, moveForward);
	    }
	} catch (EndOfTokenStreamException e) {
	    // this may happen at the end of a file...
	}
    }

    @Override
    public int compareTo(CodeRange other) {
	return this.getType().compareTo(other.getType());
    }

    @Override
    public File getFile() {
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
	return getType() + ": " + getName() + "\n" + getFile() + ": "
		+ getStartId() + "-" + getStopId();
    }

    private String getHTMLTitleString() {
	String output = "<b>" + getType() + ": " + getName() + "</b><br/>\n";
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
    public int getStartLine() {
	return getTokenStream().get(getStartId()).getStartLine();
    }

    @Override
    public int getStopLine() {
	return getTokenStream().get(getStopId()).getStopLine();
    }

    // ////////////////////////////////////////////////////////////////////////////////////

    @Override
    public CodeRange createPartialCodeRange(int newStartPosition,
	    int newEndPosition) {
	AbstractSourceCodeParser parser = (AbstractSourceCodeParser) clone();
	DependencyInjection.inject(parser, Injection.named("StartPosition",
		newStartPosition), Injection.named("EndPosition",
		newEndPosition));
	return parser;
    }

    @Override
    public int getStartId() {
	return this.getStartPosition();
    }

    @Override
    public int getStopId() {
	return getEndPosition();
    }

    @Override
    public String getText() {
	String text = "";
	TokenStream tokenStream = getTokenStream();
	for (int index = getStartId(); index <= getStopId(); index++) {
	    text += tokenStream.get(index).getText();
	}
	return text;
    }

    @Override
    public ArrayList<Token> getTokens() {
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


    @Override
    public String getName() {
	return name;
    }

    @Override
    public CodeRangeType getType() {
	return type;
    }

    @Override
    public String getTypeName() {
	return type.getIdentifier();
    }
}
