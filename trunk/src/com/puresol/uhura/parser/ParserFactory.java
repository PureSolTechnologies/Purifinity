package com.puresol.uhura.parser;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.parser.lr.SLR1Parser;

public class ParserFactory {

	private static final Logger logger = Logger.getLogger(ParserFactory.class);

	public static Parser create(Grammar grammar) throws ParserFactoryException {
		try {
			Properties options = grammar.getOptions();
			if (options.containsKey("parser")) {
				return (Parser) Class.forName(options.getProperty("parser"))
						.getConstructor(Grammar.class)
						.newInstance(options, grammar);
			} else {
				return new SLR1Parser(grammar);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw new ParserFactoryException(e.getMessage());
		}
	}

}
