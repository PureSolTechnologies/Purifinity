package com.puresol.uhura.lexer;

import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;
import com.puresol.uhura.source.StringWithLocation;

/**
 * This is a basic Lexer based on Java's regular expression engine.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class RegExpLexer implements Lexer {

    private static final long serialVersionUID = -6858518460147748314L;

    private static final Logger logger = LoggerFactory
	    .getLogger(RegExpLexer.class);

    private final Grammar grammar;

    private TokenStream tokenStream = null;
    private SourceCode sourceCode = null;

    public RegExpLexer(Grammar grammar) {
	this.grammar = grammar;
    }

    /**
     * @return the grammar
     */
    public Grammar getGrammar() {
	return grammar;
    }

    /**
     * {@inheritDoc}
     * 
     * @throws LexerException
     */

    @Override
    public TokenStream lex(SourceCode sourceCode) throws LexerException {
	this.sourceCode = sourceCode;
	return scan();
    }

    private TokenStream scan() throws LexerException {
	tokenStream = new TokenStream();
	StringWithLocation fullText = new StringWithLocation(sourceCode);
	StringBuffer text = new StringBuffer(fullText.getText());
	int position = 0;
	while (text.length() > 0) {
	    Token token = findNextToken(text, fullText.getSource(position));
	    if ((token == null) || (token.getText().length() == 0)) {
		String exceptionText;
		if (text.length() <= 12) {
		    exceptionText = text.toString();
		} else {
		    exceptionText = text.substring(0, 12) + "...";

		}
		SourceCodeLine sourceCodeLine = fullText.getSource(position);
		throw new LexerException("No token found for '"
			+ exceptionText
			+ "' in line "
			+ sourceCodeLine.getSource()
				.getHumanReadableLocationString() + ":"
			+ sourceCodeLine.getLineNumber() + ".");
	    }
	    if (logger.isTraceEnabled()) {
		logger.trace("Found token: " + token + " / "
			+ token.getMetaData());
	    }
	    if (token.getVisibility() != Visibility.HIDDEN) {
		tokenStream.add(token);
	    }
	    int length = token.getText().length();
	    text = text.delete(0, length);
	    position += length;
	}
	return tokenStream;
    }

    private Token findNextToken(StringBuffer text, SourceCodeLine sourceCodeLine) {
	Token nextToken = null;
	for (TokenDefinition definition : grammar.getTokenDefinitions()
		.getDefinitions()) {
	    Matcher matcher = definition.getPattern().matcher(text);
	    if (!matcher.find()) {
		continue;
	    }
	    String tokenText = matcher.group(0);
	    if ((nextToken == null)
		    || (tokenText.length() > nextToken.getText().length())) {
		int lineCounter = 1;
		for (char c : tokenText.toCharArray()) {
		    if (c == '\n') {
			lineCounter++;
		    }
		}
		TokenMetaData metaData = new TokenMetaData(
			sourceCodeLine.getSource(),
			sourceCodeLine.getLineNumber(), lineCounter);
		nextToken = new Token(definition.getName(), tokenText,
			definition.getVisibility(), metaData);
	    }
	}
	return nextToken;
    }

    @Override
    public Lexer clone() {
	RegExpLexer cloned = new RegExpLexer(grammar);
	if (this.tokenStream != null) {
	    cloned.tokenStream = (TokenStream) this.tokenStream.clone();
	} else {
	    cloned.tokenStream = null;
	}
	if (this.sourceCode != null) {
	    cloned.sourceCode = this.sourceCode.clone();
	} else {
	    cloned.sourceCode = null;
	}
	return cloned;
    }
}
