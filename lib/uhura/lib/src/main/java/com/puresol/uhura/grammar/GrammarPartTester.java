package com.puresol.uhura.grammar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.LexerFactory;
import com.puresol.uhura.lexer.LexerFactoryException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserFactoryException;
import com.puresol.uhura.parser.ParserManager;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.SourceCode;
import com.puresol.utils.StopWatch;

/**
 * This class is a simple tester for checking grammars and parts of grammars.
 * Parts are single production which can be check by redirecting the start
 * element with id 0 to the production which is to be checked. With this trick
 * parts of grammars and single specifications can be tested.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class GrammarPartTester {

    private static final Logger logger = LoggerFactory
	    .getLogger(GrammarPartTester.class);

    public static boolean test(Grammar grammar, String production,
	    String... lines) {
	try {
	    if (logger.isDebugEnabled()) {
		logger.debug("Testing production '" + production
			+ "' with text '" + lines + "' ...");
	    }
	    grammar = grammar.createWithNewStartProduction(production);
	    Lexer lexer = LexerFactory.create(grammar);
	    TokenStream tokenStream = lexer.lex(SourceCode
		    .fromStringArray(lines));

	    Parser parser = ParserFactory.create(grammar);
	    ParserTree ast = parser.parse(tokenStream);
	    if (logger.isTraceEnabled()) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new TreePrinter(new PrintStream(out)).println(ast);
		logger.trace(new String(out.toByteArray()));
	    }
	    logger.debug("passed.");
	    return true;
	} catch (GrammarException e) {
	    e.printStackTrace();
	    return false;
	} catch (LexerException e) {
	    e.printStackTrace();
	    return false;
	} catch (ParserFactoryException e) {
	    e.printStackTrace();
	    return false;
	} catch (ParserException e) {
	    e.printStackTrace();
	    return false;
	} catch (LexerFactoryException e) {
	    e.printStackTrace();
	    return false;
	}
    }

    public static boolean test(File parserDirectory, String parserName,
	    Grammar grammar, String production, String... lines) {
	try {
	    if (logger.isDebugEnabled()) {
		logger.debug("Testing text '" + production + "' with text '"
			+ lines + "' ...");
	    }
	    grammar = grammar.createWithNewStartProduction(production);
	    StopWatch watch = new StopWatch();
	    Lexer lexer = new RegExpLexer(grammar);
	    watch.start();
	    TokenStream tokenStream = lexer.lex(SourceCode
		    .fromStringArray(lines));
	    watch.stop();
	    logger.debug("Lexer time: " + watch);

	    Parser parser = ParserManager.getManagerParser(parserDirectory,
		    parserName, grammar);
	    watch.start();
	    ParserTree ast = parser.parse(tokenStream);
	    watch.stop();
	    logger.debug("Parser time: " + watch);
	    if (logger.isTraceEnabled()) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		new TreePrinter(new PrintStream(out)).println(ast);
		logger.trace(new String(out.toByteArray()));
	    }
	    logger.debug("passed.");
	    return true;
	} catch (GrammarException e) {
	    e.printStackTrace();
	    return false;
	} catch (ParserException e) {
	    e.printStackTrace();
	    return false;
	} catch (LexerException e) {
	    e.printStackTrace();
	    return false;
	}
    }
}
