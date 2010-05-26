package com.puresol.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

/**
 * This is the core lexer engine for scanning texts to an ArrayList of tokens.
 * It is not implemented as a recursive scan due to the stack limitation for
 * large source codes. It is implemented as a stack based linear search with
 * roll back after a dead end.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class TextLexerEngine {

    private static final Logger logger = Logger
	    .getLogger(TextLexerEngine.class);

    /**
     * This is the only access method to use the engine. Everything else is only
     * internal for proper working conditions.
     * 
     * @param text
     *            is the text to be scanned for tokens.
     * @param tokenDefinitions
     *            is a list of token defintiosn to be scanned for.
     * @return An ArrayList of Token is returned containing all tokens found.
     * @throws NoMatchingTokenDefinitionFound
     *             is thrown if the text is not parsable with the given list of
     *             token definitions.
     */
    public static List<Token> process(String text,
	    List<TokenDefinition> tokenDefinitions)
	    throws NoMatchingTokenDefinitionFound {
	return new TextLexerEngine(text, tokenDefinitions).process();
    }

    /**
     * This class is for storing a lexer position within the lexer stack. The
     * class is set up as value object.
     * 
     * @author Rick-Rainer Ludwig
     * 
     */
    class LexerPosition {

	private final int definitionIndex;
	private final Token token;

	public LexerPosition(int definitionIndex, Token token) {
	    this.definitionIndex = definitionIndex;
	    this.token = token;
	}

	public int getDefinitionIndex() {
	    return definitionIndex;
	}

	public Token getToken() {
	    return token;
	}
    }

    private final String text;
    private final List<TokenDefinition> tokenDefinitions;

    private TextLexerEngine(String text, List<TokenDefinition> tokenDefinitions) {
	this.text = text;
	this.tokenDefinitions = tokenDefinitions;
    }

    private final Stack<LexerPosition> lexerStack = new Stack<LexerPosition>();
    private int textPosition = 0;
    private int tokenIndex = 0;
    private int lineNumber = 0;
    private int startDefinitionIndex = 0;
    private TokenDefinition definition = null;
    private int definitionIndex = 0;

    /**
     * This is the engine. It is not implemented as a recursive scan due to the
     * stack limitation for large source codes. It is implemented as a stack
     * based linear search with roll back after a dead end.
     * 
     * @return
     * @throws NoMatchingTokenDefinitionFound
     */
    private ArrayList<Token> process() throws NoMatchingTokenDefinitionFound {
	// scan text...
	for (textPosition = 0; textPosition < text.length();) {
	    // look for matching token...
	    boolean tokenFound = checkForMatchingDefinition();
	    if (tokenFound) {
		storeTokenAndMoveOn();
	    } else {
		removeTokenAndRollBack();
		if (textPosition == 0) {
		    throw new NoMatchingTokenDefinitionFound(lineNumber,
			    textPosition, text);
		}
	    }
	}
	return createTokenList();

    }

    /**
     * Check for a matching token...
     * 
     * @return
     */
    private boolean checkForMatchingDefinition() {
	definition = null;
	for (definitionIndex = startDefinitionIndex; definitionIndex < tokenDefinitions
		.size(); definitionIndex++) {
	    definition = tokenDefinitions.get(definitionIndex);
	    if (definition.atStart(text.substring(textPosition))) {
		return true;
	    }
	}
	return false;
    }

    /**
     * A Token was found, so save at stack and move on...
     */
    private void storeTokenAndMoveOn() {
	String tokenText = definition.getTokenAtStart(text
		.substring(textPosition));
	Token token = Token.createByDefinition(definition, tokenIndex,
		textPosition, lineNumber, tokenText);
	lexerStack.push(new LexerPosition(definitionIndex, token));

	textPosition += token.getLength();
	tokenIndex++;
	lineNumber += countNewLines(tokenText);
	startDefinitionIndex = 0;
	logger.debug("store token: " + token.toString());
    }

    private int countNewLines(String text) {
	char[] chars = text.toCharArray();
	int counter = 0;
	for (int index = 0; index < text.length(); index++) {
	    if (chars[index] == '\n') {
		counter++;
	    }
	}
	return counter;
    }

    /**
     * No token was found, so roll back one position and try next token
     * definition...
     * 
     * @throws NoMatchingTokenDefinitionFound
     */
    private void removeTokenAndRollBack() throws NoMatchingTokenDefinitionFound {
	if (lexerStack.size() == 0) {
	    throw new NoMatchingTokenDefinitionFound(lineNumber, textPosition,
		    text);
	}
	LexerPosition oldPosition = lexerStack.pop();
	Token token = oldPosition.getToken();

	textPosition -= token.getLength();
	tokenIndex--;
	lineNumber -= token.getStopLine() - token.getStartLine();
	startDefinitionIndex = oldPosition.getDefinitionIndex() + 1;
	logger.debug("rollback: " + token.toString());
    }

    /**
     * This method reads the stack and creates an ArrayList of Token.
     * 
     * @return An ArrayList<Token> is returned with all tokens from the stack
     *         after successful completion of the scan.
     */
    private ArrayList<Token> createTokenList() {
	ArrayList<Token> tokens = new ArrayList<Token>();
	while (lexerStack.size() > 0) {
	    // add it in front due to Stack is a FIFO buffer!
	    tokens.add(0, lexerStack.pop().getToken());
	}
	return tokens;
    }
}
