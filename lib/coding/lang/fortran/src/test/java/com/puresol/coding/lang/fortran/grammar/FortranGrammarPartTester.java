package com.puresol.coding.lang.fortran.grammar;

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
import com.puresol.uhura.source.BuiltinSource;
import com.puresol.uhura.source.Source;

public class FortranGrammarPartTester {

    public static final File PARSER_DIRECTORY = new File(
	    "test/com/puresol/coding/lang/fortran/parsers");

    private static Grammar grammar = null;
    private static Lexer lexer = null;
    private static Map<String, Parser> parsers = new HashMap<String, Parser>();

    public static boolean test(String production, String text)
	    throws GrammarException, LexerException, IOException,
	    ParserException {
	return test(production, new BuiltinSource(text));
    }

    public static boolean test(String production, Source source)
	    throws GrammarException, LexerException, IOException,
	    ParserException {
	if (grammar == null) {
	    initializeGrammar();
	}
	if (lexer == null) {
	    initializeLexer();
	}
	if (parsers.get(production) == null) {
	    initializeParser(production);
	}
	TokenStream tokenStream = lexer.lex(source.load());
	parsers.get(production).parse(tokenStream);
	return true;
    }

    private static synchronized void initializeGrammar() {
	if (grammar == null) {
	    grammar = FortranGrammar.getGrammar();
	}
    }

    private static synchronized void initializeLexer() throws GrammarException {
	if (lexer == null) {
	    lexer = grammar.createLexer();
	}
    }

    private static synchronized void initializeParser(String production)
	    throws GrammarException {
	if (parsers.get(production) == null) {
	    Parser parser = grammar.createWithNewStartProduction(production)
		    .createParser();
	    parsers.put(production, parser);
	}
    }
}
