package com.puresol.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.puresol.exceptions.StrangeSituationException;
import com.puresol.parser.tokens.EndOfTokenStreamException;
import com.puresol.parser.tokens.Token;
import com.puresol.parser.tokens.TokenDefinition;
import com.puresol.parser.tokens.TokenCreationException;
import com.puresol.parser.tokens.TokenPublicity;
import com.puresol.parser.tokens.TokenStream;
import com.puresol.parser.tokens.TokenStreamIterator;
import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;

/**
 * This class is the abstract base implementation for a parser based on
 * TokenStream, Token and TokenDefinition.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public abstract class AbstractParser implements Parser {

	private static final long serialVersionUID = -5314830349131569085L;

	private static final Logger logger = Logger.getLogger(AbstractParser.class);

	private TokenStreamIterator tokenStreamIterator = null;

	private Integer startPosition = 0;

	private Integer endPosition = 0;

	private Parser parentParser = null;

	/**
	 * This variable is the name of the parser. Per default it's just the class
	 * name, but it should be replaced during the parsing process with something
	 * more meaningful.
	 */
	private String name = getClass().getName();
	private final List<Parser> childParsers = new ArrayList<Parser>();

	/**
	 * This flag is used to signalize a finished parsing process. It's also used
	 * as a lock for all values to avoid a change after the process.
	 */
	private boolean finished = false;

	/**
	 * This is the default Constructor. It is specified to make clear, that this
	 * class and all other inherited classes should always have a default
	 * constructor. This make inheritance easier due to not creating any other
	 * constructors and to use Dependency Injection instead. Therefore, creating
	 * smaller sub parsers is much easier.
	 */
	public AbstractParser() {
	}

	protected final void setStartPosition(int startPosition) {
		if (isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		if ((startPosition < 0)
				|| (startPosition >= getTokenStream().getSize())) {
			throw new IllegalArgumentException("StartPosition is out of range!");
		}
		this.startPosition = startPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getStartPosition() {
		return startPosition;
	}

	protected final void setCurrentPosition(int currentPosition) {
		try {
			if (isFinished()) {
				throw new IllegalStateException("Parser was finished!");
			}
			if ((currentPosition < 0)
					|| (currentPosition >= getTokenStream().getSize())) {
				throw new IllegalArgumentException(
						"CurrentPosition is out of range!");
			}
			tokenStreamIterator.moveTo(currentPosition);
		} catch (EndOfTokenStreamException e) {
			throw new StrangeSituationException(e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCurrentPosition() {
		return tokenStreamIterator.getPosition();
	}

	protected final void setEndPosition(int endPosition) {
		if (isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		if ((endPosition < 0) || (endPosition >= getTokenStream().getSize())) {
			throw new IllegalArgumentException("EndPosition is out of range!");
		}
		this.endPosition = endPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getEndPosition() {
		if (!isFinished()) {
			throw new IllegalStateException("Parser was not finished!");
		}
		return endPosition;
	}

	protected final void setName(String name) {
		this.name = name;
	}

	@Override
	public String getText() {
		String text = "";
		TokenStream tokenStream = getTokenStream();
		for (int index = startPosition; index <= tokenStreamIterator
				.getPosition(); index++) {
			text += tokenStream.get(index).getText();
		}
		return text;
	}

	@Override
	public String getVisibleText() {
		String text = "";
		TokenStream tokenStream = getTokenStream();
		for (int index = startPosition; index <= tokenStreamIterator
				.getPosition(); index++) {
			Token token = tokenStream.get(index);
			if (token.getPublicity() == TokenPublicity.VISIBLE) {
				text += token.getText();
			}
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final String getName() {
		return name;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getTokenCount() {
		return tokenStreamIterator.getPosition() - startPosition + 1;
	}

	public final Token getToken(int pos) {
		return getTokenStream().get(pos);
	}

	public final Token getCurrentToken() throws EndOfTokenStreamException {
		return tokenStreamIterator.getToken();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final TokenStream getTokenStream() {
		return tokenStreamIterator.getTokenStream();
	}

	public final void setTokenStreamIterator(
			TokenStreamIterator tokenStreamIterator) {
		if (this.tokenStreamIterator != null) {
			throw new IllegalStateException(
					"TokenStreamIterator was already assigned!");
		}
		this.tokenStreamIterator = tokenStreamIterator;
	}

	protected final TokenStreamIterator getTokenStreamIterator() {
		return tokenStreamIterator;
	}

	protected final void setParentParser(Parser parentParser) {
		this.parentParser = parentParser;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final Parser getParentParser() {
		return parentParser;
	}

	protected final List<Parser> getAllParentParsers() {
		List<Parser> ancestors = new ArrayList<Parser>();
		Parser ancestor = this.getParentParser();
		while (ancestor != null) {
			ancestors.add(ancestor);
			ancestor = ((AbstractParser) ancestor).getParentParser();
		}
		return ancestors;
	}

	public final boolean hasChildParser(Parser childParser) {
		return childParsers.contains(childParser);
	}

	public final void addChildParser(Parser childParser) {
		if ((childParser != this) && (!hasChildParser(childParser))) {
			assert childParser.getParentParser() == this;
			childParsers.add(childParser);
		}
	}

	@Override
	public final List<Parser> getChildParsers() {
		return childParsers;
	}

	@Override
	public final <T> List<T> getChildParsers(Class<T> parserClass) {
		List<T> parsers = new ArrayList<T>();
		getChildParsers(parserClass, parsers, this);
		return parsers;
	}

	private <T> void getChildParsers(Class<T> parserClass,
			List<T> childParsers, Parser parser) {
		if (parser == null) {
			return;
		}
		if (parserClass.isAssignableFrom(parser.getClass())) {
			@SuppressWarnings("unchecked")
			T t = (T) parser;
			childParsers.add(t);
		}
		for (Parser childParser : parser.getChildParsers()) {
			getChildParsers(parserClass, childParsers, childParser);
		}
	}

	private void addThisToParentsChildParsers() {
		Parser parent = getParentParser();
		if (parent != null) {
			((AbstractParser) parentParser).addChildParser(this);
		}
	}

	protected Parser createParserInstance(Class<? extends Parser> clazz)
			throws ParserException {
		try {
			AbstractParser parser = (AbstractParser) Instances
					.createInstance(clazz);
			parser.setParentParser(this);
			parser.setTokenStreamIterator(new TokenStreamIterator(
					getTokenStream(), tokenStreamIterator.getPosition()));
			parser.setStartPosition(tokenStreamIterator.getPosition());
			parser.setEndPosition(tokenStreamIterator.getPosition());
			return parser;
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage(), e);
			throw new ParserException(e.getMessage());
		}
	}

	protected final int getStartPositionWithLeadingHidden() {
		int pos = getStartPosition();
		if (pos == 0) {
			return 0;
		}
		while ((getToken(pos - 1).getPublicity() != TokenPublicity.VISIBLE)
				&& (pos > 0)) {
			pos--;
			if (pos == 0) {
				return 0;
			}
		}
		return pos;
	}

	protected final int getPositionOfNextLineBreak() {
		int pos = getCurrentPosition();
		while (!getToken(pos).getText().contains("\n")) {
			pos++;
		}
		return getPositionOfNextLineBreak();
	}

	protected final int getPositionOfLastVisible() {
		int pos = getCurrentPosition();
		if (pos <= 0) {
			return 0;
		}
		if (getToken(pos) == null) {
			return pos;
		}
		pos--;
		while (getToken(pos).getPublicity() != TokenPublicity.VISIBLE) {
			if (pos <= 0) {
				return 0;
			}
			pos--;
		}
		return pos;
	}

	protected final void move(int steps) throws EndOfTokenStreamException {
		if (this.isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		tokenStreamIterator.move(steps);
		endPosition = tokenStreamIterator.getPosition();
	}

	protected final void moveTo(int position) throws EndOfTokenStreamException {
		if (this.isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		tokenStreamIterator.moveTo(position);
		endPosition = tokenStreamIterator.getPosition();
	}

	protected final void moveToNextVisibleToken(int steps)
			throws EndOfTokenStreamException {
		if (this.isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		tokenStreamIterator.moveToNextVisibleToken(steps);
		endPosition = tokenStreamIterator.getPosition();
	}

	protected final Token lookAhead(int steps) throws EndOfTokenStreamException {
		if (this.isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		int pos = tokenStreamIterator.getPosition() + 1;
		int countVisible = 0;
		while (pos < getTokenStream().getSize()) {
			Token token = getTokenStream().get(pos);
			if (token.getPublicity() == TokenPublicity.VISIBLE) {
				countVisible++;
			}
			if (countVisible == steps) {
				return token;
			}
			pos++;
		}
		throw new EndOfTokenStreamException(this);
	}

	private TokenDefinition processToken(
			Class<? extends TokenDefinition> definition, boolean moveForward)
			throws PartDoesNotMatchException, ParserException,
			EndOfTokenStreamException {
		try {
			if (this.isFinished()) {
				throw new IllegalStateException("Parser was finished!");
			}
			Token currentToken = getCurrentToken();
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + currentToken.toString());
			}
			if (!currentToken.getDefinition().equals(definition)) {
				throw new PartDoesNotMatchException(this);
			}
			if (moveForward) {
				moveToNextVisibleToken(1);
			}
			return currentToken.getDefinitionInstance();
		} catch (TokenCreationException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		}
	}

	protected final TokenDefinition expectToken(
			Class<? extends TokenDefinition> definition)
			throws PartDoesNotMatchException, ParserException {
		try {
			return processToken(definition, true);
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	protected final boolean isToken(Class<? extends TokenDefinition> definition)
			throws ParserException {
		try {
			return (processToken(definition, false) != null);
		} catch (PartDoesNotMatchException e) {
		} catch (EndOfTokenStreamException e) {
		}
		return false;
	}

	protected final boolean isToken(String text)
			throws PartDoesNotMatchException {
		try {
			return getCurrentToken().getText().equals(text);
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	protected final TokenDefinition acceptToken(
			Class<? extends TokenDefinition> definition) throws ParserException {
		try {
			return processToken(definition, true);
		} catch (PartDoesNotMatchException e) {
		} catch (EndOfTokenStreamException e) {
		}
		return null;
	}

	private Parser processPart(Class<? extends Parser> part)
			throws PartDoesNotMatchException, ParserException {
		try {
			if (this.isFinished()) {
				throw new IllegalStateException("Parser was finished!");
			}
			logger.trace("Process part if possible: " + part.getSimpleName());
			AbstractParser partInstance = null;
			partInstance = (AbstractParser) createParserInstance(part);
			partInstance.scan();
			if (!partInstance.isFinished()) {
				throw new ParserException("Part " + partInstance.getClass()
						+ " was not correctly finished!");
			}
			moveToNextVisibleToken(partInstance.getEndPosition()
					- getCurrentPosition() + 1);
			logger.debug("Processed part " + part.getSimpleName());
			return partInstance;
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	protected Parser expectPart(Class<? extends Parser> part)
			throws PartDoesNotMatchException, ParserException {
		return processPart(part);
	}

	protected Parser acceptPart(Class<? extends Parser> part)
			throws ParserException {
		try {
			return processPart(part);
		} catch (PartDoesNotMatchException e) {
		}
		return null;
	}

	protected TokenDefinition expectOneTokenOf(
			List<Class<? extends TokenDefinition>> definitions)
			throws PartDoesNotMatchException, ParserException {
		for (Class<? extends TokenDefinition> definitionClass : definitions) {
			TokenDefinition definitionInstance = acceptToken(definitionClass);
			if (definitionInstance != null) {
				return definitionInstance;
			}
		}
		throw new PartDoesNotMatchException(this);
	}

	/**
	 * This method checks if the current parser is marked as finished.
	 * 
	 * @return
	 */
	protected boolean isFinished() {
		return finished;
	}

	/**
	 * This method marks the current parser as "finished parsing" and adds it to
	 * the parents child parser list.
	 * 
	 * This method is to be called after a successful scan of a parser.
	 */
	protected void finish() {
		if (isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		addThisToParentsChildParsers();
		finished = true;
	}

	/**
	 * This method aborts the current parsing run and throws a
	 * PartDoesNotMatchException.
	 * 
	 * This method is to be called if a parser can not go further on due to a
	 * leak of alternatives. There is an error within the text parsed.
	 * 
	 * @throws PartDoesNotMatchException
	 */
	protected void abort() throws PartDoesNotMatchException {
		if (isFinished()) {
			throw new IllegalStateException("Parser was finished!");
		}
		throw new PartDoesNotMatchException(this);
	}

	protected void skipNested(Class<? extends TokenDefinition> left,
			Class<? extends TokenDefinition> right)
			throws PartDoesNotMatchException, ParserException {
		try {
			if (this.isFinished()) {
				throw new IllegalStateException("Parser was finished!");
			}
			expectToken(left);
			int count = 1;
			do {
				if (acceptToken(right) != null) {
					count--;
				} else if (acceptToken(left) != null) {
					count++;
				} else {
					moveToNextVisibleToken(1);
				}
			} while (count > 0);
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childParsers == null) ? 0 : childParsers.hashCode());
		result = prime * result
				+ ((endPosition == null) ? 0 : endPosition.hashCode());
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentParser == null) ? 0 : parentParser.hashCode());
		result = prime * result
				+ ((startPosition == null) ? 0 : startPosition.hashCode());
		result = prime
				* result
				+ ((tokenStreamIterator == null) ? 0 : tokenStreamIterator
						.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractParser other = (AbstractParser) obj;
		if (childParsers == null) {
			if (other.childParsers != null)
				return false;
		} else if (!childParsers.equals(other.childParsers))
			return false;
		if (endPosition == null) {
			if (other.endPosition != null)
				return false;
		} else if (!endPosition.equals(other.endPosition))
			return false;
		if (finished != other.finished)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentParser == null) {
			if (other.parentParser != null)
				return false;
		} else if (!parentParser.equals(other.parentParser))
			return false;
		if (startPosition == null) {
			if (other.startPosition != null)
				return false;
		} else if (!startPosition.equals(other.startPosition))
			return false;
		if (tokenStreamIterator == null) {
			if (other.tokenStreamIterator != null)
				return false;
		} else if (!tokenStreamIterator.equals(other.tokenStreamIterator))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String string = getTokenStream().getFile() + ":\n";
		for (int index = getStartPosition(); index <= getEndPosition(); index++) {
			string += getTokenStream().get(index).getText();
		}
		return string;
	}
}
