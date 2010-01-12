package com.puresol.coding;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Parser;
import com.puresol.parser.Part;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class SourceCodeParser extends Parser {

	private static final Logger logger = Logger
			.getLogger(SourceCodeParser.class);

	private ArrayList<CodeRange> codeRanges = new ArrayList<CodeRange>();

	public SourceCodeParser(TokenStream tokenStream) {
		super(tokenStream);
	}

	public void parse(Class<? extends Part> rootPart)
			throws PartDoesNotMatchException {
		try {
			Constructor<? extends Part> constructor = rootPart.getConstructor(
					getClass(), TokenStream.class, int.class);
			Part rootPartInstance = (Part) constructor.newInstance(this,
					getTokenStream(), getTokenStream().getFirstVisbleTokenID());
			rootPartInstance.scan();
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
		} catch (NoMatchingTokenException e) {
			logger.warn(e.getMessage(), e);
		}
	}

	public void addCodeRange(CodeRange codeRange) {
		codeRanges.add(codeRange);
	}

	public ArrayList<CodeRange> getCodeRanges() {
		return codeRanges;
	}

}
