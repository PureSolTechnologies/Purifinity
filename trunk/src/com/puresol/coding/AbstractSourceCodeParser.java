package com.puresol.coding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.parser.AbstractParser;
import com.puresol.parser.Parser;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public abstract class AbstractSourceCodeParser extends AbstractParser {

	private static final Logger logger = Logger
			.getLogger(AbstractSourceCodeParser.class);

	private ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public AbstractSourceCodeParser(TokenStream tokenStream, int startPos) {
		super(tokenStream, startPos);
	}

	protected void addCodeRange(CodeRange codeRange) {
		codeRanges.add(codeRange);
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}

	@Override
	protected void processPart(Class<? extends Parser> part, boolean moveForward)
			throws PartDoesNotMatchException {
		try {
			Constructor<?> constructor = part.getConstructor(TokenStream.class,
					int.class);
			Parser partInstance = (Parser) constructor.newInstance(
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
