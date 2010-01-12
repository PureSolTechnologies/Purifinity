package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

/**
 * Reads a token stream from lexer and looks for a configured structure.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class Parser {

	private static final Logger logger = Logger.getLogger(Parser.class);

	private TokenStream tokenStream = null;

	public Parser(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	public TokenStream getTokenStream() {
		return tokenStream;
	}

	public void parse(Class<? extends Part> rootPart)
			throws PartDoesNotMatchException {
		try {
			Constructor<? extends Part> constructor = rootPart.getConstructor(
					this.getClass(), TokenStream.class, int.class);
			Part rootPartInstance = (Part) constructor.newInstance(this,
					tokenStream, tokenStream.getFirstVisbleTokenID());
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
