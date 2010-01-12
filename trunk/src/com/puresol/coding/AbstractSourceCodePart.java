package com.puresol.coding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import com.puresol.parser.AbstractPart;
import com.puresol.parser.Part;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public abstract class AbstractSourceCodePart extends AbstractPart {

    private static final Logger logger =
	    Logger.getLogger(AbstractSourceCodePart.class);

    public AbstractSourceCodePart(SourceCodeParser parser,
	    TokenStream tokenStream, int startPos) {
	super(parser, tokenStream, startPos);
    }

    protected void addCodeRange(CodeRange codeRange) {
	((SourceCodeParser) getParser()).addCodeRange(codeRange);
    }

    @Override
    protected void processPart(Class<? extends Part> part,
	    boolean moveForward) throws PartDoesNotMatchException {
	try {
	    Constructor<?> constructor =
		    part.getConstructor(SourceCodeParser.class,
			    TokenStream.class, int.class);
	    Part partInstance =
		    (Part) constructor.newInstance(
			    (SourceCodeParser) getParser(),
			    getTokenStream(), getCurrentPosition());
	    partInstance.scan();
	    if (moveForward) {
		moveForward(partInstance.getNumberOfTokens());
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
	}
    }
}
