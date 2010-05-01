package com.puresol.parser;

import java.util.ArrayList;
import java.util.List;

import javax.i18n4j.Translator;

import org.apache.log4j.Logger;

import com.puresol.utils.ClassInstantiationException;
import com.puresol.utils.Instances;
import com.puresol.utils.di.DIClassBuilder;
import com.puresol.utils.di.Inject;
import com.puresol.utils.di.Injection;

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
	private static final Translator translator = Translator
			.getTranslator(AbstractParser.class);

	@Inject("TokenStream")
	private final TokenStream tokenStream = null;

	@Inject("StartPosition")
	private Integer startPosition = 0;

	@Inject("CurrentPosition")
	private Integer currentPosition = 0;

	@Inject("EndPosition")
	private Integer endPosition = 0;

	@Inject("ParentParser")
	private final Parser parentParser = null;

	private String name = getClass().getName();
	private final List<Parser> childParsers = new ArrayList<Parser>();
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
		this.currentPosition = currentPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getCurrentPosition() {
		return currentPosition;
	}

	protected final void setEndPosition(int endPosition) {
		this.endPosition = endPosition;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final int getEndPosition() {
		return endPosition;
	}

	protected final void setName(String name) {
		this.name = name;
	}

	@Override
	public String getText() {
		String text = "";
		for (int index = startPosition; index <= currentPosition; index++) {
			text += tokenStream.get(index).getText();
		}
		return text;
	}

	@Override
	public String getContinuousText() {
		String text = "";
		for (int index = startPosition; index <= currentPosition; index++) {
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
	public final int getNumberOfTokens() {
		return currentPosition - startPosition;
	}

	public final Token getToken(int pos) {
		return tokenStream.get(pos);
	}

	public final Token getCurrentToken() {
		return tokenStream.get(currentPosition);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final TokenStream getTokenStream() {
		return tokenStream;
	}

	protected final Parser getParentParser() {
		return parentParser;
	}

	public boolean hasChildParser(Parser childParser) {
		return childParsers.contains(childParser);
	}

	public void addChildParser(Parser childParser) {
		childParsers.add(childParser);
	}

	@Override
	public List<Parser> getChildParsers() {
		return childParsers;
	}

	@Override
	public <T> List<T> getChildParsers(Class<T> parserClass) {
		List<T> parsers = new ArrayList<T>();
		getChildParsers(parserClass, parsers, this);
		return parsers;
	}

	public <T> void getChildParsers(Class<T> parserClass, List<T> childParsers,
			Parser parser) {
		for (Parser childParser : parser.getChildParsers()) {
			getChildParsers(parserClass, childParsers, childParser);
			if (parserClass.isAssignableFrom(childParser.getClass())) {
				@SuppressWarnings("unchecked")
				T t = (T) childParser;
				childParsers.add(t);
			}
		}
	}

	protected void addSelfToParent() {
		Parser parent = getParentParser();
		if (parent != null) {
			((AbstractParser) parentParser).addChildParser(this);
		}
	}

	protected Parser createParserInstance(Class<? extends Parser> clazz)
			throws ParserException {
		try {
			return DIClassBuilder.forInjections(
					Injection.named("StartPosition", Integer
							.valueOf(currentPosition)),
					Injection.named("CurrentPosition", Integer
							.valueOf(currentPosition)),
					Injection.named("TokenStream", getTokenStream()),
					Injection.named("ParentParser", this))
					.createInstance(clazz);
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
		return pos + 1;
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
		if (pos <= 0) {
			return 0;
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

	protected final int getPositionOfLastVisible() {
		return getPositionOfLastVisible(getCurrentPosition());
	}

	protected final void move(int steps) {
		currentPosition += steps;
		endPosition = currentPosition;
	}

	protected final void moveTo(int position) {
		currentPosition = position;
		endPosition = currentPosition;
	}

	protected final void moveToNextVisible(int steps)
			throws EndOfTokenStreamException {
		if (currentPosition >= tokenStream.getSize() - steps) {
			moveTo(tokenStream.getSize() - 1);
			throw new EndOfTokenStreamException(this);
		}
		move(steps);
		while (getCurrentToken().getPublicity() != TokenPublicity.VISIBLE) {
			if (currentPosition >= tokenStream.getSize() - 1) {
				throw new EndOfTokenStreamException(this);
			}
			move(1);
		}
	}

	protected final Token lookAhead(int steps) throws EndOfTokenStreamException {
		int pos = currentPosition + 1;
		int countVisible = 0;
		while (pos < tokenStream.getSize()) {
			Token token = tokenStream.get(pos);
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

	private TokenDefinition expectToken(
			Class<? extends TokenDefinition> definition, boolean moveForward)
			throws PartDoesNotMatchException, ParserException {
		TokenDefinition definitionInstance = null;
		try {
			definitionInstance = getCurrentToken().getDefinitionInstance();
			if (!getCurrentToken().getDefinition().equals(definition)) {
				definitionInstance = Instances.createInstance(definition);
				if (!definitionInstance.matches(getCurrentToken().getText())) {
					throw new PartDoesNotMatchException(this);
				}
			} else {
				definitionInstance = getCurrentToken().getDefinitionInstance();
			}
			if (moveForward) {
				moveToNextVisible(1);
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		} catch (TokenException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		}
		return definitionInstance;
	}

	protected final TokenDefinition expectToken(
			Class<? extends TokenDefinition> definition)
			throws PartDoesNotMatchException, ParserException {
		Token token = null;
		if (logger.isDebugEnabled()) {
			token = getCurrentToken();
		}
		TokenDefinition retValue = expectToken(definition, true);
		if (logger.isDebugEnabled()) {
			logger.debug("Processed token: " + token.toString());
		}
		return retValue;
	}

	protected final TokenDefinition expectToken(
			Class<? extends TokenDefinition>... definitions)
			throws PartDoesNotMatchException, ParserException {
		for (Class<? extends TokenDefinition> definition : definitions) {
			TokenDefinition definitionInstance = acceptToken(definition);
			if (definitionInstance != null) {
				return definitionInstance;
			}
		}
		throw new PartDoesNotMatchException(this);
	}

	protected final void expectToken(String text)
			throws PartDoesNotMatchException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			if (getCurrentToken().getText().equals(text)) {
				moveToNextVisible(1);
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
			if (expectToken(definition, false) != null) {
				return true;
			}
		} catch (PartDoesNotMatchException e) {
		}
		return false;
	}

	protected final boolean isToken(String text)
			throws PartDoesNotMatchException {
		if (getCurrentToken().getText().equals(text)) {
			return true;
		}
		return false;
	}

	protected final TokenDefinition acceptToken(
			Class<? extends TokenDefinition> definition) throws ParserException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			TokenDefinition retValue = expectToken(definition, true);
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + token.toString());
			}
			return retValue;
		} catch (PartDoesNotMatchException e) {
			return null;
		}
	}

	protected final boolean acceptToken(String text) throws ParserException {
		try {
			Token token = null;
			if (logger.isDebugEnabled()) {
				token = getCurrentToken();
			}
			expectToken(text);
			if (logger.isDebugEnabled()) {
				logger.debug("Processed token: " + token.toString());
			}
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected final void skipTo(Class<? extends TokenDefinition>... definitions)
			throws PartDoesNotMatchException, ParserException {
		try {
			for (;;) {
				for (Class<? extends TokenDefinition> definition : definitions) {
					if (isToken(definition)) {
						return;
					}
				}
				moveToNextVisible(1);
			}
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	private Parser expectPart(Class<? extends Parser> part, boolean moveForward)
			throws PartDoesNotMatchException, ParserException {
		AbstractParser partInstance = null;
		try {
			partInstance = (AbstractParser) createParserInstance(part);
			partInstance.scan();
			if (!partInstance.isFinish()) {
				throw new ParserException("Part " + partInstance.getClass()
						+ " was not correctly finished!");
			}
			if (moveForward) {
				// move to the end of part and one further for next token
				moveToNextVisible(partInstance.getEndPosition()
						- getCurrentPosition() + 1);
			}
		} catch (EndOfTokenStreamException e) {
			// this may happen at the end of a file...
		}
		return partInstance;
	}

	protected Parser expectPart(Class<? extends Parser> part)
			throws PartDoesNotMatchException, ParserException {
		logger.debug("Process part: " + part.getSimpleName());
		Parser retValue = expectPart(part, true);
		logger.debug("done for " + part.getSimpleName());
		return retValue;
	}

	protected Parser expectOnePartOf(List<Class<? extends Parser>> parts)
			throws PartDoesNotMatchException, ParserException {
		Parser parser = acceptOnePartOf(parts);
		if (parser != null) {
			return parser;
		}
		throw new PartDoesNotMatchException(this);
	}

	protected boolean isPart(Class<? extends Parser> part)
			throws ParserException {
		try {
			logger.debug("Is part(?): " + part.getSimpleName());
			expectPart(part, false);
			logger.debug("true for " + part.getSimpleName());
			return true;
		} catch (PartDoesNotMatchException e) {
			return false;
		}
	}

	protected Parser acceptPart(Class<? extends Parser> part)
			throws ParserException {
		try {
			logger.debug("Process part if possible: " + part.getSimpleName());
			Parser retValue = expectPart(part, true);
			logger.debug("processed for " + part.getSimpleName());
			return retValue;
		} catch (PartDoesNotMatchException e) {
			return null;
		}
	}

	protected Parser acceptOnePartOf(List<Class<? extends Parser>> parts)
			throws ParserException {
		try {
			for (Class<? extends Parser> part : parts) {
				if (isPart(part)) {
					return expectPart(part);
				}
			}
			return null;
		} catch (PartDoesNotMatchException e) {
			return null;
		}
	}

	protected TokenDefinition expectOneTokenOf(
			List<Class<? extends TokenDefinition>> definitions)
			throws PartDoesNotMatchException, ParserException {
		try {
			for (Class<? extends TokenDefinition> definitionClass : definitions) {
				TokenDefinition definition = Instances
						.createInstance(definitionClass);
				TokenDefinition definitionInstance = acceptToken(definition
						.getClass());
				if (definitionInstance != null) {
					return definitionInstance;
				}
			}
			throw new PartDoesNotMatchException(this);
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		}
	}

	protected boolean isCurrentTokenOneOf(
			List<Class<? extends TokenDefinition>> definitions)
			throws ParserException {
		try {
			for (Class<? extends TokenDefinition> definitionClass : definitions) {
				TokenDefinition definition = Instances
						.createInstance(definitionClass);
				if (isToken(definition.getClass())) {
					return true;
				}
			}
			return false;
		} catch (ClassInstantiationException e) {
			logger.error(e.getMessage());
			throw new ParserException(e.getMessage());
		}
	}

	/**
	 * This method checks if the current parser is marked as finished.
	 * 
	 * @return
	 */
	protected boolean isFinish() {
		return finished;
	}

	/**
	 * This method marks the current parser as "finished parsing" and adds it to
	 * the parents child parser list.
	 * 
	 * This method is to be called after a successful scan of a parser.
	 */
	protected void finish() {
		addSelfToParent();
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
		throw new PartDoesNotMatchException(this);
	}

	protected void skipNested(Class<? extends TokenDefinition> left,
			Class<? extends TokenDefinition> right)
			throws PartDoesNotMatchException, ParserException {
		try {
			expectToken(left);
			int count = 1;
			do {
				if (acceptToken(right) != null) {
					count--;
				} else if (acceptToken(left) != null) {
					count++;
				} else {
					moveToNextVisible(1);
				}
			} while (count > 0);
		} catch (EndOfTokenStreamException e) {
			throw new PartDoesNotMatchException(this);
		}
	}

	/**
	 * Hash code as always...
	 * 
	 * Parent had to be removed to do loop and Stack Overflow.
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		/*
		 * Parent
		 */
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((childParsers == null) ? 0 : childParsers.hashCode());
		result = prime * result
				+ ((currentPosition == null) ? 0 : currentPosition.hashCode());
		result = prime * result
				+ ((endPosition == null) ? 0 : endPosition.hashCode());
		result = prime * result + (finished ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((startPosition == null) ? 0 : startPosition.hashCode());
		result = prime * result
				+ ((tokenStream == null) ? 0 : tokenStream.hashCode());
		return result;
	}

	/**
	 * equals as always...
	 * 
	 * Parent had to be removed to do loop and Stack Overflow.
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
		if (currentPosition == null) {
			if (other.currentPosition != null)
				return false;
		} else if (!currentPosition.equals(other.currentPosition))
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
		}
		if (startPosition == null) {
			if (other.startPosition != null)
				return false;
		} else if (!startPosition.equals(other.startPosition))
			return false;
		if (tokenStream == null) {
			if (other.tokenStream != null)
				return false;
		} else if (!tokenStream.equals(other.tokenStream))
			return false;
		return true;
	}

	@Override
	public String toString() {
		Token startToken = tokenStream.get(getStartPosition());
		Token stopToken = tokenStream.get(getEndPosition());
		String string = tokenStream.getFile() + ": ";
		if (startToken.getStartLine() == stopToken.getStopLine()) {
			string += translator.i18n("line") + " " + startToken.getStartLine();
		} else {
			string += translator.i18n("lines") + " "
					+ startToken.getStartLine() + " - "
					+ stopToken.getStopLine();
		}
		return string;
	}
}
