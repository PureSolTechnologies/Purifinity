package com.puresol.parser;

import java.util.ArrayList;
import java.util.List;

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

    private static final Logger logger = Logger.getLogger(AbstractParser.class);

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

    private String name = "";
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
	for (Parser childParser : childParsers) {
	    if (parserClass.isAssignableFrom(childParser.getClass())) {
		@SuppressWarnings("unchecked")
		T t = (T) childParser;
		parsers.add(t);
	    }
	}
	return parsers;
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

    protected TokenDefinition expectOneOf(
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

    protected boolean isCurrentOneOf(
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
}
