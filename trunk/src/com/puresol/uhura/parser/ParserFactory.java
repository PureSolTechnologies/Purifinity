package com.puresol.uhura.parser;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.puresol.uhura.parser.lr.LR1Parser;
import com.puresol.uhura.parser.lr.TEBTLRParser;

public class ParserFactory {

	private static final Logger logger = Logger.getLogger(ParserFactory.class);

	public static Parser create(Properties options)
			throws ParserFactoryException {
		try {
			if (options.containsKey("parser")) {
				return (Parser) Class.forName(options.getProperty("parser"))
						.getConstructor(Properties.class).newInstance(options);
			} else {
				return new TEBTLRParser(options);
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			throw new ParserFactoryException(e.getMessage());
		}
	}

}
