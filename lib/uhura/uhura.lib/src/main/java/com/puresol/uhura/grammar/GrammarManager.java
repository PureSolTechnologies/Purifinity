package com.puresol.uhura.grammar;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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

	public static String getPersistedGrammarPath(String grammarPath) {
		return grammarPath + ".persist";
	}

	public static String getPersistedLexerPath(String grammarPath) {
		return grammarPath + ".lexer.persist";
	}

	public static String getPersistedParserPath(String grammarPath) {
		return grammarPath + ".parser.persist";
	}

	private final URL grammarURL;
	private final File grammarPersistencePath;
	private final File lexerPersistencePath;
	private final File parserPersistencePath;

	private Grammar grammar = null;
	private Lexer lexer = null;
	private Parser parser = null;

	public GrammarManager(URL grammarURL) {
		super();
		this.grammarURL = grammarURL;
		this.grammarPersistencePath = new File(
				getPersistedGrammarPath(grammarURL.getPath()));
		this.lexerPersistencePath = new File(
				getPersistedLexerPath(grammarURL.getPath()));
		this.parserPersistencePath = new File(
				getPersistedParserPath(grammarURL.getPath()));
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
		return grammarPersistencePath;
	}

	/**
	 * @return the lexerPersistenceFile
	 */
	public File getLexerPersistenceFile() {
		return lexerPersistencePath;
	}

	/**
	 * @return the parserPersistenceFile
	 */
	public File getParserPersistenceFile() {
		return parserPersistencePath;
	}

	public Grammar getGrammar() throws IOException, GrammarException {
		if ((!grammarPersistencePath.exists())
				|| (grammarModified() > grammarPersistencePath.lastModified())) {
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
			if ((!grammarPersistencePath.exists())
					|| (grammarModified() > grammarPersistencePath
							.lastModified())) {
				inputStream = grammarURL.openStream();
				GrammarReader reader = new GrammarReader(inputStream);
				grammar = reader.getGrammar();
				Persistence.persist(grammar, grammarPersistencePath);
			}
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
	}

	private void readGrammarPersistenceFile() throws IOException,
			GrammarException {
		try {
			grammar = (Grammar) Persistence.restore(grammarPersistencePath);
		} catch (PersistenceException e) {
			updateGrammarPersistenceFile();
		}
	}

	public Lexer getLexer() throws IOException, GrammarException,
			LexerFactoryException {
		if ((!lexerPersistencePath.exists())
				|| (grammarModified() > lexerPersistencePath.lastModified())) {
			updateGrammarPersistenceFile();
			updateLexerPersistenceFile();
		} else if (lexer == null) {
			readLexerPersistenceFile();
		}
		return lexer;
	}

	private void updateLexerPersistenceFile() throws IOException,
			GrammarException, LexerFactoryException {
		if (grammar == null) {
			readGrammarPersistenceFile();
		}
		lexer = LexerFactory.create(grammar);
		Persistence.persist(lexer, lexerPersistencePath);
	}

	private void readLexerPersistenceFile() throws IOException,
			GrammarException, LexerFactoryException {
		try {
			lexer = (Lexer) Persistence.restore(lexerPersistencePath);
		} catch (PersistenceException e) {
			updateLexerPersistenceFile();
		}
	}

	public Parser getParser() throws IOException, GrammarException,
			ParserFactoryException {
		if ((!parserPersistencePath.exists())
				|| (grammarModified() > parserPersistencePath.lastModified())) {
			updateGrammarPersistenceFile();
			updateParserPersistenceFile();
		} else if (parser == null) {
			readParserPersistenceFile();
		}
		return parser;
	}

	private void updateParserPersistenceFile() throws IOException,
			GrammarException, ParserFactoryException {
		if (grammar == null) {
			readGrammarPersistenceFile();
		}
		parser = (Parser) ParserFactory.create(grammar);
		Persistence.persist(parser, parserPersistencePath);
	}

	private void readParserPersistenceFile() throws IOException,
			GrammarException, ParserFactoryException {
		try {
			parser = (Parser) Persistence.restore(parserPersistencePath);
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
