package com.puresol.uhura.grammar;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.uhura.grammar.uhura.UhuraGrammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerResult;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.parser.lr.SLR1Parser;

/**
 * This class is for reading Nyota Uhura grammar files. The grammar file is read
 * into an AST. The interpretation is done with GrammarReader.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarFile implements Closeable {

    private static final Logger logger = LoggerFactory
	    .getLogger(GrammarFile.class);

    private final Grammar uhuraGrammar = UhuraGrammar.getGrammar();;
    private final Reader reader;

    private ParserTree ast = null;

    /**
     * Constructor for InputStream reading.
     * 
     * @param inputStream
     */
    public GrammarFile(InputStream inputStream) {
	if (inputStream == null) {
	    throw new IllegalArgumentException("Input stream is null!");
	}
	reader = new InputStreamReader(inputStream);
    }

    /**
     * Constructor taking a reader for reading the grammar.
     * 
     * @param reader
     */
    public GrammarFile(Reader reader) {
	if (reader == null) {
	    throw new IllegalArgumentException("Reader stream is null!");
	}
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
    public ParserTree getAST() throws IOException, GrammarException {
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
	    LexerResult lexerResult = lexer.lex(
		    SourceCode.read(reader, new File("UhuraGrammar")),
		    "UhuraGrammar");
	    logger.debug("Starting parser...");
	    parse(lexerResult);
	    logger.debug("done.");
	} catch (LexerException e) {
	    logger.error(e.getMessage(), e);
	    throw new IOException(e.getMessage());
	} catch (ParserException e) {
	    logger.error(e.getMessage(), e);
	    throw new IOException(e.getMessage());
	}
    }

    /**
     * This method does the parsing and reacts appropriately to any exceptions.
     * 
     * @param tokenStream
     * @throws ParserException
     */
    private void parse(LexerResult lexerResult) throws ParserException {
	try {
	    Parser parser = new SLR1Parser(uhuraGrammar);
	    ast = parser.parse(lexerResult);
	} catch (GrammarException e) {
	    logger.error(e.getMessage(), e);
	    throw new RuntimeException("UhuraGrammar is broken!!!");
	}
    }

    @Override
    public void close() throws IOException {
	reader.close();
    }
}
