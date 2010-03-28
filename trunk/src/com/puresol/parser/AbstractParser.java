package com.puresol.parser;

import org.apache.log4j.Logger;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

public abstract class AbstractParser implements Parser {

	private static final Logger logger = Logger.getLogger(AbstractParser.class);

	@TokenStreamInjection
	private TokenStream tokenStream = null;
	@StartPositionInjection
	private int startPos = 0;
	@StartPositionInjection
	private int currentPos = 0;

	protected final void setStartPosition(int startPos) {
		this.startPos = startPos;
		this.currentPos = startPos;
	}

	public final int getStartPosition() {
		return startPos;
	}

	@Override
	public final int getCurrentPosition() {
		return currentPos;
	}

	@Override
	public final int getNumberOfTokens() {
		return currentPos - startPos;
	}

	public final Token getToken(int pos) {
		return tokenStream.get(pos);
	}

	public final Token getCurrentToken() {
		return tokenStream.get(currentPos);
	}

	protected final void setTokenStream(TokenStream tokenStream) {
		this.tokenStream = tokenStream;
	}

	@Override
	public final TokenStream getTokenStream() {
		return tokenStream;
	}

	protected final int getStartPositionWithLeadingHidden() {
		int pos = getStartPosition();
		while ((getToken(pos - 1).getPublicity() == TokenPublicity.HIDDEN)
				&& (pos > 0)) {
			pos--;
		}
		return getPositionOfNextLineBreak(pos) + 1;
	}

	protected final int getPositionOfNextLineBreak(int pos) {
		while (!getToken(pos).getText().contains("\n")) {
			pos++;
		}
		return pos;
	}

	protected final int getPositionOfNextLineBreak() {
		return getPositionOfNextLineBreak(getCurrentPosition());
	}

	protected final int getPositionOfLastVisible(int pos) {
		pos--;
		while (getToken(pos).getPublicity() != TokenPublicity.VISIBLE) {
			pos--;
		}
		return pos;
	}

	protected final int getPositionOfLastVisible() {
		return getPositionOfLastVisible(getCurrentPosition());
	}

	protected final void moveForward(int steps)
			throws EndOfTokenStreamException {
		if (currentPos >= tokenStream.getSize() - 1) {
			throw new EndOfTokenStreamException(this);
		}
		currentPos += steps;
		while (getCurrentToken().getPublicity() != TokenPublicity.VISIBLE) {
			if (currentPos >= tokenStream.getSize() - 1) {
				throw new EndOfTokenStreamException(this);
			}
			currentPos++;
		}
	}

	private <C> C createInstance(Class<C> clazz) throws ParserException {
		try {
			return Instances.createInstance(clazz);
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		}
	}

	private void processToken(Class<? extends TokenDefinition> definition,
			boolean moveForward) throws PartDoesNotMatchException,
			ParserException {
		try {
			if (!getCurrentToken().getDefinition().equals(definition)) {
				TokenDefinition definitionInstance = createInstance(definition);
				if (!definitionInstance.matches(getCurrentToken().getText())) {
					throw new PartDoesNotMatchException(this);
				}
			}
			if (moveForward) {
				moveForward(1);
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		}
	}

	protected final void processToken(
			Class<? extends TokenDefinition> definition)
			throws PartDoesNotMatchException, ParserException {
		Token token = null;
		if (logger.isDebugEnabled()) {
			token = getCurrentToken();
		}
		processToken(definition, true);
		if (logger.isDebugEnabled()) {
			logger.debug("Processed token: " + token.toString());
		}
	}

	protected final void processToken(
			Class<? extends TokenDefinition>... definitions)
			throws PartDoesNotMatchException, ParserException {
		for (Class<? extends TokenDefinition> definition : definitions) {
			if (isToken(definition)) {
				processToken(definition);
				return;
			}
		}
		throw new PartDoesNotMatchException(this);
	}

	protected final void processToken(String text)
			throws PartDoesNotMatchException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			if (getCurrentToken().getText().equals(text)) {
				moveForward(1);
			} else {
				throw new PartDoesNotMatchException(this);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + token.toString());
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		}
	}

	protected final boolean isToken(Class<? extends TokenDefinition> definition)
			throws ParserException {
		try {
			processToken(definition, false);
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected final boolean isToken(String text)
			throws PartDoesNotMatchException {
		if (getCurrentToken().getText().equals(text)) {
			return true;
		}
		return false;
	}

	protected final boolean processTokenIfPossible(
			Class<? extends TokenDefinition> definition) throws ParserException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			processToken(definition, true);
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + token.toString());
			}
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected final boolean processTokenIfPossible(String text)
			throws ParserException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			processToken(text);
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + token.toString());
			}
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected final void skipTokensUntil(
			Class<? extends TokenDefinition>... definitions)
			throws PartDoesNotMatchException, ParserException {
		try {
			for (;;) {
				for (Class<? extends TokenDefinition> definition : definitions) {
					if (isToken(definition)) {
						return;
					}
				}
				moveForward(1);
			}
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	protected void processPart(Class<? extends Parser> part, boolean moveForward)
			throws PartDoesNotMatchException, ParserException {
		try {
			AbstractParser partInstance = (AbstractParser) createInstance(part);
			partInstance.setTokenStream(tokenStream);
			partInstance.setStartPosition(currentPos);
			partInstance.scan();
			if (moveForward) {
				moveForward(partInstance.getNumberOfTokens());
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		}
	}

	protected void processPart(Class<? extends Parser> part)
			throws PartDoesNotMatchException, ParserException {
		logger.debug("Process part: " + part.getSimpleName());
		processPart(part, true);
		logger.debug("done for " + part.getSimpleName());
	}

	protected boolean isPart(Class<? extends Parser> part)
			throws ParserException {
		try {
			logger.debug("Is part(?): " + part.getSimpleName());
			processPart(part, false);
			logger.debug("true for " + part.getSimpleName());
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected boolean processPartIfPossible(Class<? extends Parser> part)
			throws ParserException {
		try {
			logger.debug("Process part if possible: " + part.getSimpleName());
			processPart(part, true);
			logger.debug("processed for " + part.getSimpleName());
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected void processOneOf(
			Class<? extends TokenDefinitionGroup> definitions)
			throws PartDoesNotMatchException, ParserException {
		TokenDefinitionGroup tokenDefinitions = createInstance(definitions);
		for (TokenDefinition definition : tokenDefinitions
				.getTokenDefinitions()) {
			if (isToken(definition.getClass())) {
				processToken(definition.getClass());
				return;
			}
		}
		throw new PartDoesNotMatchException(this);
	}

	protected boolean isCurrentOneOf(
			Class<? extends TokenDefinitionGroup> definitions)
			throws ParserException {
		TokenDefinitionGroup tokenDefinitions = createInstance(definitions);
		for (TokenDefinition definition : tokenDefinitions
				.getTokenDefinitions()) {
			if (isToken(definition.getClass())) {
				return true;
			}
		}
		return false;
	}

	protected void abort() throws PartDoesNotMatchException {
		throw new PartDoesNotMatchException(this);
	}

	protected void skipNested(Class<? extends TokenDefinition> left,
			Class<? extends TokenDefinition> right)
			throws PartDoesNotMatchException, ParserException {
		try {
			processToken(left);
			int count = 1;
			do {
				if (isToken(right)) {
					processToken(right);
					count--;
				} else if (isToken(left)) {
					processToken(left);
					count++;
				} else {
					moveForward(1);
				}
			} while (count > 0);
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}
}
