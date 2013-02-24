package com.puresol.coding.lang.java.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.source.FixedCodeLocation;
import com.puresol.uhura.source.CodeLocation;

public class JavaGrammarPartTester {

    public static final File PARSER_DIRECTORY = new File(
	    "test/com/puresol/coding/lang/java/parsers");

    private static Grammar grammar = null;
    private static Lexer lexer = null;
    private static Map<String, Parser> parsers = new HashMap<String, Parser>();

    public static boolean test(String production, String text)
	    throws GrammarException, LexerException, IOException,
	    ParserException {
	return test(production, new FixedCodeLocation(text));
    }

    public static boolean test(String production, CodeLocation source)
	    throws GrammarException, LexerException, IOException,
	    ParserException {
	if (parsers.get(production) == null) {
	    initializeParser(production);
	}
	TokenStream tokenStream = getLexer().lex(source.loadSourceCode());
	parsers.get(production).parse(tokenStream);
	return true;
    }

    private static Grammar getGrammar() {
	if (grammar == null) {
	    initializeGrammar();
	}
	return grammar;
    }

    private static synchronized void initializeGrammar() {
	if (grammar == null) {
	    grammar = JavaGrammar.getGrammar();
	}
    }

    private static Lexer getLexer() throws GrammarException {
	if (lexer == null) {
	    initializeLexer();
	}
	return lexer;
    }

    private static synchronized void initializeLexer() throws GrammarException {
	if (lexer == null) {
	    lexer = getGrammar().createLexer();
	}
    }

    private static synchronized void initializeParser(String production)
	    throws GrammarException {
	if (parsers.get(production) == null) {
	    Parser parser = getGrammar().createWithNewStartProduction(
		    production).createParser();
	    parsers.put(production, parser);
	}
    }
}
