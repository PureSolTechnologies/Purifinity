package com.puresol.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.log4j.Logger;

public abstract class AbstractPart implements Part {

	private static final Logger logger = Logger.getLogger(AbstractPart.class);

	private Parser parser = null;
	private TokenStream tokenStream = null;
	private int startPos;
	private int currentPos;

	public AbstractPart(Parser parser, TokenStream tokenStream, int startPos) {
		this.parser = parser;
		this.tokenStream = tokenStream;
		this.startPos = startPos;
		this.currentPos = startPos;
	}

	public int getStartPosition() {
		return startPos;
	}

	public int getCurrentPosition() {
		return currentPos;
	}

	public int getNumberOfTokens() {
		return currentPos - startPos;
	}

	public Token getCurrentToken() {
		Token token = tokenStream.get(currentPos);
		if (logger.isTraceEnabled()) {
			logger.trace("Current token:" + token.toString());
		}
		return token;
	}

	public TokenStream getTokenStream() {
		return tokenStream;
	}

	public Parser getParser() {
		return parser;
	}

	protected void moveForward(int steps) throws PartDoesNotMatchException {
		currentPos += steps;
		if (currentPos >= tokenStream.getSize()) {
			throw new PartDoesNotMatchException(this);
		}
		while (getCurrentToken().getPublicity() == TokenPublicity.HIDDEN) {
			currentPos++;
			if (currentPos >= tokenStream.getSize()) {
				throw new PartDoesNotMatchException(this);
			}
		}
	}

	private void processToken(Class<? extends TokenDefinition> definition,
			boolean moveForward) throws PartDoesNotMatchException {
		try {
			if (!getCurrentToken().getDefinition().equals(definition)) {
				Constructor<?> constructor;
				constructor = definition.getConstructor();
				TokenDefinition definitionInstance = (TokenDefinition) constructor
						.newInstance();
				if (!definitionInstance.matches(getCurrentToken().getText())) {
					throw new PartDoesNotMatchException(this);
				}
			}
			if (moveForward) {
				moveForward(1);
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

	protected void processToken(Class<? extends TokenDefinition> definition)
			throws PartDoesNotMatchException {
		processToken(definition, true);
	}

	protected boolean isToken(Class<? extends TokenDefinition> definition) {
		try {
			processToken(definition, false);
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected void processPart(Class<? extends Part> part, boolean moveForward)
			throws PartDoesNotMatchException {
		try {
			Constructor<?> constructor = part.getConstructor(Parser.class,
					TokenStream.class, int.class);
			Part partInstance = (Part) constructor.newInstance(tokenStream,
					getCurrentPosition());
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

	protected void processPart(Class<? extends Part> part)
			throws PartDoesNotMatchException {
		processPart(part, true);
	}

	protected boolean isPart(Class<? extends Part> part) {
		try {
			processPart(part, false);
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected void processOneTokenOf(
			Class<? extends TokenDefinitionGroup> definitions)
			throws PartDoesNotMatchException {
		try {
			Constructor<?> constructor = definitions.getConstructor();
			TokenDefinitionGroup tokenDefinitions = (TokenDefinitionGroup) constructor
					.newInstance();
			for (TokenDefinition definition : tokenDefinitions.getKeywords()) {
				if (isToken(definition.getClass())) {
					processToken(definition.getClass());
					return;
				}
			}
			throw new PartDoesNotMatchException(this);
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

	protected boolean isOneTokenOf(
			Class<? extends TokenDefinitionGroup> definitions) {
		try {
			Constructor<?> constructor = definitions.getConstructor();
			TokenDefinitionGroup tokenDefinitions = (TokenDefinitionGroup) constructor
					.newInstance();
			for (TokenDefinition definition : tokenDefinitions.getKeywords()) {
				if (isToken(definition.getClass())) {
					return true;
				}
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
		return false;
	}

	protected void abort() throws PartDoesNotMatchException {
		throw new PartDoesNotMatchException(this);
	}

	protected void skipNested(Class<? extends TokenDefinition> left,
			Class<? extends TokenDefinition> right)
			throws PartDoesNotMatchException {
		processToken(left);
		int count = 1;
		while ((count > 0) || (!isToken(right))) {
			moveForward(1);
			if (isToken(right)) {
				count--;
			} else if (isToken(left)) {
				count++;
			}
		}
	}
}
