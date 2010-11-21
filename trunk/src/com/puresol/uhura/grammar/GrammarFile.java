package com.puresol.uhura.grammar;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.log4j.Logger;

import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.uhura.UhuraGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.lr.SLR1Parser;

/**
 * This class is for reading Nyota Uhura grammar files. The grammar file is read
 * into an AST. The interpretation is done with GrammarReader.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarFile {

	private static final Logger logger = Logger.getLogger(GrammarFile.class);

	private final Grammar uhuraGrammar = UhuraGrammar.getGrammar();;
	private final Reader reader;

	private AST ast = null;

	/**
	 * Constructor for InputStream reading.
	 * 
	 * @param inputStream
	 */
	public GrammarFile(InputStream inputStream) {
		this(new InputStreamReader(inputStream));
	}

	/**
	 * Constructor taking a reader for reading the grammar.
	 * 
	 * @param reader
	 */
	public GrammarFile(Reader reader) {
		this.reader = reader;
	}

	/**
	 * This method returns the syntax tree from the read grammar file to
	 * retrieve additional information if needed.
	 * 
	 * @return
	 * @throws GrammarException
	 * @throws IOException
	 */
	public AST getAST() throws IOException, GrammarException {
		if (ast == null) {
			read();
		}
		return ast;
	}

	/**
	 * This is the central reading routine which starts all sub routines like
	 * lexer, parser and converter.
	 * 
	 * @throws IOException
	 * @throws GrammarException
	 */
	private void read() throws IOException, GrammarException {
		try {
			logger.debug("Read grammar file:");
			logger.debug("Starting lexer...");
			Lexer lexer = new RegExpLexer(uhuraGrammar);
			TokenStream tokenStream = lexer.lex(reader, "UhuraGrammar");
			logger.debug("Starting parser...");
			parse(tokenStream);
			logger.debug("done.");
		} catch (LexerException e) {
			logger.error(e.getMessage(), e);
			throw new IOException(e.getMessage());
		} catch (ParserException e) {
			logger.error(e.getMessage(), e);
			String errorMsg = "Error while reading grammar file at: "
					+ e.getToken().getMetaData() + ": " + e.getToken();
			logger.error(errorMsg);
			throw new IOException(errorMsg);
		}
	}

	/**
	 * This method does the parsing and reacts appropriately to any exceptions.
	 * 
	 * @param tokenStream
	 * @throws ParserException
	 */
	private void parse(TokenStream tokenStream) throws ParserException {
		try {
			Parser parser = new SLR1Parser(uhuraGrammar);
			ast = parser.parse(tokenStream);
		} catch (GrammarException e) {
			logger.fatal(e.getMessage(), e);
			throw new RuntimeException("UhuraGrammar is broken!!!");
		}
	}
}
