package com.puresol.uhura.grammar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

	private final URL grammarURL;
	private final File grammarPersistenceFile;
	private final File lexerPersistenceFile;
	private final File parserPersistenceFile;

	private Grammar grammar = null;
	private Lexer lexer = null;
	private Parser parser = null;

	public GrammarManager(URL grammarURL) {
		super();
		this.grammarURL = grammarURL;
		this.grammarPersistenceFile = new File(".", grammarURL.getFile()
				+ ".persist");
		this.lexerPersistenceFile = new File(".", grammarURL.getFile()
				+ ".lexer.persist");
		this.parserPersistenceFile = new File(".", grammarURL.getFile()
				+ ".parser.persist");
	}

	/**
	 * @return the grammarFile
	 */
	public URL getGrammarURL() {
		return grammarURL;
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
				|| (grammarModified() > grammarPersistenceFile.lastModified())) {
			updateGrammarPersistenceFile();
		} else if (grammar == null) {
			readGrammarPersistenceFile();
		}
		return grammar;
	}

	private void updateGrammarPersistenceFile() throws IOException,
			GrammarException {
		InputStream inputStream = null;
		try {
			if ((!grammarPersistenceFile.exists())
					|| (grammarModified() > grammarPersistenceFile
							.lastModified())) {
				inputStream = grammarURL.openStream();
				GrammarReader reader = new GrammarReader(inputStream);
				reader.call();
				grammar = reader.getGrammar();
				Persistence.persist(grammar, grammarPersistenceFile);
			}
		} catch (PersistenceException e) {
			logger.error(e.getMessage());
			throw new IOException(e.getMessage());
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
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
				|| (grammarModified() > lexerPersistenceFile.lastModified())) {
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
				|| (grammarModified() > parserPersistenceFile.lastModified())) {
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

	private long grammarModified() {
		if ("file".equals(grammarURL.getProtocol())) {
			return new File(grammarURL.getFile()).lastModified();
		}
		return System.currentTimeMillis();
	}
}
