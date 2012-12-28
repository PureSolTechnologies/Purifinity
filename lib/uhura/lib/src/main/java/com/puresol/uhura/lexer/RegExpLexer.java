package com.puresol.uhura.lexer;

import java.util.Iterator;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.token.TokenDefinition;
import com.puresol.uhura.grammar.token.Visibility;
import com.puresol.uhura.source.Source;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.SourceCodeLine;

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
    public LexerResult lex(SourceCode sourceCode) throws LexerException {
	this.sourceCode = sourceCode;
	return new LexerResult(scan());
    }

    private TokenStream scan() throws LexerException {
	tokenStream = new TokenStream();
	int pos = 0;
	int id = 0;
	int line = 1;
	Iterator<SourceCodeLine> sourceIterator = sourceCode.getSource()
		.iterator();
	while (sourceIterator.hasNext()) {
	    SourceCodeLine sourceCodeLine = sourceIterator.next();
	    StringBuffer text = new StringBuffer(sourceCodeLine.getLine());
	    while (text.length() > 0) {
		Token token = findNextToken(text, sourceCodeLine.getSource(),
			id, pos, line);
		if ((token == null) || (token.getText().length() == 0)) {
		    String exceptionText;
		    if (text.length() <= 12) {
			exceptionText = text.toString();
		    } else {
			exceptionText = text.substring(0, 12) + "...";

		    }
		    if (sourceIterator.hasNext()) {
			sourceCodeLine = sourceIterator.next();
			text.append(sourceCodeLine.getLine());
			continue;
		    } else {
			throw new LexerException("No token found for '"
				+ exceptionText + "' in line " + line
				+ " at position " + pos + ".");
		    }
		}
		if (logger.isTraceEnabled()) {
		    logger.trace("Found token: " + token + " / "
			    + token.getMetaData());
		}
		if (token.getVisibility() != Visibility.HIDDEN) {
		    tokenStream.add(token);
		}
		id++;
		pos += token.getText().length();
		line += token.getMetaData().getLineNum() - 1;
		text = text.delete(0, token.getText().length());
	    }
	}
	return tokenStream;
    }

    private Token findNextToken(StringBuffer text, Source source, int id,
	    int pos, int line) {
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
		TokenMetaData metaData = new TokenMetaData(source, id, pos,
			line, lineCounter);
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
