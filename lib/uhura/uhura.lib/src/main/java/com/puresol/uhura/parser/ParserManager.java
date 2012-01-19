package com.puresol.uhura.parser;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class ParserManager {

    private static final Logger logger = LoggerFactory
	    .getLogger(ParserManager.class);

    public static void storeParser(File directory, String name, Parser parser)
	    throws IOException {
	Persistence.persist(parser, new File(directory, name + ".persist"));
    }

    public static Parser loadParser(File directory, String name)
	    throws PersistenceException, IOException {
	return (Parser) Persistence.restore(new File(directory, name
		+ ".persist"));
    }

    public static Parser getManagerParser(File directory, String name,
	    Grammar grammar) throws GrammarException {
	logger.debug("Look for manager parser '" + name + "' in directory '"
		+ directory + "'...");
	try {
	    Parser parser = loadParser(directory, name);
	    logger.debug("Parser '" + name + "' was successfully loaded!");
	    return parser;
	} catch (PersistenceException e) {
	    logger.debug("Parser '" + name + "' not available, yet.");
	} catch (IOException e) {
	    logger.debug("Parser '" + name + "' not available, yet.");
	}
	Parser parser = null;
	try {
	    parser = ParserFactory.create(grammar);
	} catch (ParserFactoryException e) {
	    throw new GrammarException(
		    "Grammar does not include information about the needed parser in parser-key!");
	}
	try {
	    storeParser(directory, name, parser);
	} catch (IOException e) {
	    logger.warn(
		    "Newly created managed parser '" + name
			    + "' could not be stored in directory '"
			    + directory + "'!", e);
	}
	return parser;
    }
}
