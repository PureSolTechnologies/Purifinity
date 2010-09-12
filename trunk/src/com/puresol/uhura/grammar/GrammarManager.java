package com.puresol.uhura.grammar;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerFactory;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

/**
 * This class manages a single grammar with all its lexer and parser facilities.
 * It's needed due to an excessive calculation effort for parser table
 * construction.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarManager {

	private static final Logger logger = Logger.getLogger(GrammarManager.class);

	private final File grammarFile;
	private final File grammarPersistenceFile;
	private final File lexerPersistenceFile;
	private final File parserPersistenceFile;

	private Grammar grammar = null;
	private Lexer lexer = null;
	private Parser parser = null;

	public GrammarManager(File grammarFile) {
		super();
		this.grammarFile = grammarFile;
		this.grammarPersistenceFile = new File(grammarFile.getParentFile(),
				grammarFile.getName() + ".persist");
		this.lexerPersistenceFile = new File(grammarFile.getParentFile(),
				grammarFile.getName() + ".lexer.persist");
		this.parserPersistenceFile = new File(grammarFile.getParentFile(),
				grammarFile.getName() + ".parser.persist");
	}

	/**
	 * @return the grammarFile
	 */
	public File getGrammarFile() {
		return grammarFile;
	}

	/**
	 * @return the grammarPersistenceFile
	 */
	public File getGrammarPersistenceFile() {
		return grammarPersistenceFile;
	}

	/**
	 * @return the lexerPersistenceFile
	 */
	public File getLexerPersistenceFile() {
		return lexerPersistenceFile;
	}

	/**
	 * @return the parserPersistenceFile
	 */
	public File getParserPersistenceFile() {
		return parserPersistenceFile;
	}

	public Grammar getGrammar() throws IOException, GrammarException {
		if ((!grammarPersistenceFile.exists())
				|| (grammarFile.lastModified() > grammarPersistenceFile
						.lastModified())) {
			updateGrammarPersistenceFile();
		} else if (grammar == null) {
			readGrammarPersistenceFile();
		}
		return grammar;
	}

	private void updateGrammarPersistenceFile() throws IOException,
			GrammarException {
		try {
			if ((!grammarPersistenceFile.exists())
					|| (grammarFile.lastModified() > grammarPersistenceFile
							.lastModified())) {
				GrammarReader reader = new GrammarReader(grammarFile);
				reader.call();
				grammar = reader.getGrammar();
				Persistence.persist(grammar, grammarPersistenceFile);
			}
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	private void readGrammarPersistenceFile() throws IOException,
			GrammarException {
		try {
			grammar = (Grammar) Persistence.restore(grammarPersistenceFile);
		} catch (PersistenceException e) {
			updateGrammarPersistenceFile();
		}
	}

	public Lexer getLexer() throws IOException, GrammarException,
			LexerFactoryException {
		if ((!lexerPersistenceFile.exists())
				|| (grammarFile.lastModified() > lexerPersistenceFile
						.lastModified())) {
			updateGrammarPersistenceFile();
			updateLexerPersistenceFile();
		} else if (lexer == null) {
			readLexerPersistenceFile();
		}
		return lexer;
	}

	private void updateLexerPersistenceFile() throws IOException,
			GrammarException, LexerFactoryException {
		try {
			if (grammar == null) {
				readGrammarPersistenceFile();
			}
			lexer = LexerFactory.create(grammar);
			Persistence.persist(lexer, lexerPersistenceFile);
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	private void readLexerPersistenceFile() throws IOException,
			GrammarException, LexerFactoryException {
		try {
			lexer = (Lexer) Persistence.restore(lexerPersistenceFile);
		} catch (PersistenceException e) {
			updateLexerPersistenceFile();
		}
	}

	public Parser getParser() throws IOException, GrammarException,
			ParserFactoryException {
		if ((!parserPersistenceFile.exists())
				|| (grammarFile.lastModified() > parserPersistenceFile
						.lastModified())) {
			updateGrammarPersistenceFile();
			updateParserPersistenceFile();
		} else if (parser == null) {
			readParserPersistenceFile();
		}
		return parser;
	}

	private void updateParserPersistenceFile() throws IOException,
			GrammarException, ParserFactoryException {
		try {
			if (grammar == null) {
				readGrammarPersistenceFile();
			}
			parser = (Parser) ParserFactory.create(grammar);
			Persistence.persist(parser, parserPersistenceFile);
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		}
	}

	private void readParserPersistenceFile() throws IOException,
			GrammarException, ParserFactoryException {
		try {
			parser = (Parser) Persistence.restore(parserPersistenceFile);
		} catch (PersistenceException e) {
			updateParserPersistenceFile();
		}
	}
}
