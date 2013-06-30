package com.puresol.purifinity.uhura.parser;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.parser.lr.SLR1Parser;

public class ParserFactory {

    private static final Logger logger = LoggerFactory
	    .getLogger(ParserFactory.class);

    public static Parser create(Grammar grammar) throws ParserFactoryException {
	try {
	    Properties options = grammar.getOptions();
	    if (options.containsKey("parser")) {
		return (Parser) Class.forName(options.getProperty("parser"))
			.getConstructor(Grammar.class).newInstance(grammar);
	    } else {
		return new SLR1Parser(grammar);
	    }
	} catch (Throwable e) {
	    logger.error(e.getMessage(), e);
	    throw new ParserFactoryException(e.getMessage());
	}
    }

}
