package com.puresol.coding.java;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

import com.puresol.coding.SourceCodeParser;
import com.puresol.parser.NoMatchingTokenException;
import com.puresol.parser.Part;
import com.puresol.parser.PartDoesNotMatchException;
import com.puresol.parser.TokenStream;

public class JavaParser extends SourceCodeParser {

	private static final Logger logger = Logger.getLogger(JavaParser.class);

	public JavaParser(TokenStream tokenStream) {
		super(tokenStream);
	}

	public void parse(Class<? extends Part> rootPart)
			throws PartDoesNotMatchException {
		try {
			Constructor<? extends Part> constructor = rootPart.getConstructor(
					SourceCodeParser.class, TokenStream.class, int.class);
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
}
