package com.puresol.coding.analysis;

import java.util.ArrayList;

import com.puresol.coding.lang.Language;
import com.puresol.parser.AbstractParser;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.Parser;
import com.puresol.parser.ParserException;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public abstract class AbstractSourceCodeParser extends AbstractParser
	implements SourceCodeParser {

    private final ArrayList<CodeRange> codeRanges =
	    new ArrayList<CodeRange>();

    protected final void addCodeRange(Language language,
	    CodeRangeType codeRangeType, int start, int stop) {
	addCodeRange(language, codeRangeType, codeRangeType
		.getIdentifier(), start, stop);
    }

    protected final void addCodeRange(Language language,
	    CodeRangeType codeRangeType, String name, int start, int stop) {
	CodeRange codeRange =
		new CodeRange(getTokenStream().getFile(), language,
			codeRangeType, name, getTokenStream(), start, stop);
	if (!codeRanges.contains(codeRange)) {
	    codeRanges.add(codeRange);
	}
    }

    protected final void addCodeRanges(ArrayList<CodeRange> codeRanges) {
	this.codeRanges.addAll(codeRanges);
    }

    public final ArrayList<CodeRange> getCodeRanges() {
	return codeRanges;
    }

    @Override
    protected final void processPart(Class<? extends Parser> part,
	    boolean moveForward) throws PartDoesNotMatchException,
	    ParserException {
	try {
	    if (SourceCodeParser.class.isAssignableFrom(part)) {
		AbstractSourceCodeParser parser =
			(AbstractSourceCodeParser) Instances
				.createInstance(part);
		parser.setTokenStream(getTokenStream());
		parser.setStartPosition(getCurrentPosition());
		parser.scan();
		if (parser instanceof SourceCodeParser) {
		    ArrayList<CodeRange> ranges =
			    ((SourceCodeParser) parser).getCodeRanges();
		    addCodeRanges(ranges);
		}
		if (moveForward) {
		    moveForward(parser.getNumberOfTokens());
		}
	    } else {
		super.processPart(part, moveForward);
	    }
	} catch (EndOfTokenStreamException e) {
	    // this may happen at the end of a file...
	} catch (ClassInstantiationException e) {
	    throw new ParserException("Could not create instance for '"
		    + part.getName() + "'!");
	}
    }
}
