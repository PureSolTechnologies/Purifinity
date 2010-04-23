package com.puresol.parser;

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
    private final Integer startPosition = 0;

    @Inject("StartPosition")
    private Integer currentPosition = 0;

    @Inject("EndPosition")
    private Integer endPosition = 0;

    @Inject("ParentParser")
    private final Parser parent = null;

    /**
     * Default Constructor...
     */
    public AbstractParser() {
    }

    public final int getStartPosition() {
	return startPosition;
    }

    @Override
    public final int getCurrentPosition() {
	return currentPosition;
    }

    public final int getEndPosition() {
	return endPosition;
    }

    @Override
    public final int getNumberOfTokens() {
	return endPosition - startPosition;
    }

    public final Token getToken(int pos) {
	return tokenStream.get(pos);
    }

    public final Token getCurrentToken() {
	return tokenStream.get(currentPosition);
    }

    @Override
    public final TokenStream getTokenStream() {
	return tokenStream;
    }

    protected final Parser getParent() {
	return parent;
    }

    protected Parser createParserInstance(Class<? extends Parser> clazz)
	    throws ParserException {
	try {
	    return DIClassBuilder.forInjections(
		    Injection.named("StartPosition", Integer
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

    protected final void move(int steps) {
	currentPosition += steps;
	endPosition = currentPosition;
    }

    protected final void moveToNextVisible(int steps)
	    throws EndOfTokenStreamException {
	if (currentPosition >= tokenStream.getSize() - 1) {
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

    private void expectToken(Class<? extends TokenDefinition> definition,
	    boolean moveForward) throws PartDoesNotMatchException,
	    ParserException {
	try {
	    if (!getCurrentToken().getDefinition().equals(definition)) {
		TokenDefinition definitionInstance = Instances
			.createInstance(definition);
		if (!definitionInstance.matches(getCurrentToken().getText())) {
		    throw new PartDoesNotMatchException(this);
		}
	    }
	    if (moveForward) {
		moveToNextVisible(1);
	    }
	} catch (EndOfTokenStreamException e) {
	    // this may happen at the end of a file...
	} catch (ClassInstantiationException e) {
	    logger.error(e.getMessage());
	    throw new ParserException(e.getMessage());
	}
    }

    protected final void expectToken(Class<? extends TokenDefinition> definition)
	    throws PartDoesNotMatchException, ParserException {
	Token token = null;
	if (logger.isDebugEnabled()) {
	    token = getCurrentToken();
	}
	expectToken(definition, true);
	if (logger.isDebugEnabled()) {
	    logger.debug("Processed token: " + token.toString());
	}
    }

    protected final void expectToken(
	    Class<? extends TokenDefinition>... definitions)
	    throws PartDoesNotMatchException, ParserException {
	for (Class<? extends TokenDefinition> definition : definitions) {
	    if (isToken(definition)) {
		expectToken(definition);
		return;
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
	    expectToken(definition, false);
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

    protected final boolean acceptToken(
	    Class<? extends TokenDefinition> definition) throws ParserException {
	try {
	    Token token = null;
	    if (logger.isDebugEnabled()) {
		token = getCurrentToken();
	    }
	    expectToken(definition, true);
	    if (logger.isDebugEnabled()) {
		logger.debug("Processed token: " + token.toString());
	    }
	    return true;
	} catch (PartDoesNotMatchException e) {
	    return false;
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

    protected void expectPart(Class<? extends Parser> part, boolean moveForward)
	    throws PartDoesNotMatchException, ParserException {
	try {
	    AbstractParser partInstance = (AbstractParser) createParserInstance(part);
	    partInstance.scan();
	    if (moveForward) {
		moveToNextVisible(partInstance.getNumberOfTokens());
	    }
	} catch (EndOfTokenStreamException e) {
	    // this may happen at the end of a file...
	}
    }

    protected void expectPart(Class<? extends Parser> part)
	    throws PartDoesNotMatchException, ParserException {
	logger.debug("Process part: " + part.getSimpleName());
	expectPart(part, true);
	logger.debug("done for " + part.getSimpleName());
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

    protected boolean acceptPart(Class<? extends Parser> part)
	    throws ParserException {
	try {
	    logger.debug("Process part if possible: " + part.getSimpleName());
	    expectPart(part, true);
	    logger.debug("processed for " + part.getSimpleName());
	    return true;
	} catch (PartDoesNotMatchException e) {
	    return false;
	}
    }

    protected void expectOneOf(
	    List<Class<? extends TokenDefinition>> definitions)
	    throws PartDoesNotMatchException, ParserException {
	try {
	    for (Class<? extends TokenDefinition> definitionClass : definitions) {
		TokenDefinition definition = Instances
			.createInstance(definitionClass);
		if (isToken(definition.getClass())) {
		    expectToken(definition.getClass());
		    return;
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
		if (isToken(right)) {
		    expectToken(right);
		    count--;
		} else if (isToken(left)) {
		    expectToken(left);
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
