package com.puresol.coding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.parser.AbstractParser;
import com.puresol.parser.EndOfTokenStreamException;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;

public abstract class AbstractSourceCodeParser extends AbstractParser
	implements SourceCodeParser {

    private static final Logger logger =
	    Logger.getLogger(AbstractSourceCodeParser.class);

    private ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

    protected void addCodeRange(CodeRange codeRange) {
	if (!codeRanges.contains(codeRange)) {
	    codeRanges.add(codeRange);
	}
    }

    protected void addCodeRanges(ArrayList<CodeRange> codeRanges) {
	this.codeRanges.addAll(codeRanges);
    }

    public ArrayList<CodeRange> getCodeRanges() {
	return codeRanges;
    }

    @Override
    protected void processPart(Class<? extends Parser> part,
	    boolean moveForward) throws PartDoesNotMatchException {
	try {
	    Constructor<?> constructor = part.getConstructor();
	    Parser parser = (Parser) constructor.newInstance();
	    ((AbstractSourceCodeParser) parser)
		    .setTokenStream(getTokenStream());
	    ((AbstractSourceCodeParser) parser)
		    .setStartPosition(getCurrentPosition());
	    parser.scan();
	    if (parser instanceof SourceCodeParser) {
		ArrayList<CodeRange> ranges =
			((SourceCodeParser) parser).getCodeRanges();
		addCodeRanges(ranges);
	    }
	    if (moveForward) {
		moveForward(parser.getNumberOfTokens());
	    }
	} catch (SecurityException e) {
	    logger.error(e.getMessage(), e);
	} catch (NoSuchMethodException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalArgumentException e) {
	    logger.error(e.getMessage(), e);
	} catch (InstantiationException e) {
	    logger.error(e.getMessage(), e);
	} catch (IllegalAccessException e) {
	    logger.error(e.getMessage(), e);
	} catch (InvocationTargetException e) {
	    logger.error(e.getMessage(), e);
	} catch (EndOfTokenStreamException e) {
	    // this may happen at the end of a file...
	}
    }
}
