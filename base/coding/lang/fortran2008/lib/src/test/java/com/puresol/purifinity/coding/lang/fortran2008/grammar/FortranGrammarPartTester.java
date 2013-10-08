package com.puresol.purifinity.coding.lang.fortran2008.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresol.purifinity.coding.lang.fortran2008.FortranPreConditioner;
import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarException;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.LexerException;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

public class FortranGrammarPartTester {

    public static final File PARSER_DIRECTORY = new File(
	    "test/com/puresol/coding/lang/fortran/parsers");

    private static Grammar grammar = null;
    private static Lexer lexer = null;
    private static Map<String, Parser> parsers = new HashMap<String, Parser>();

    public static boolean test(String production, String... lines)
	    throws GrammarException, LexerException, IOException,
	    ParserException {
	return test(production, new FixedCodeLocation(lines));
    }

    public static boolean test(String production, CodeLocation source)
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
	FortranPreConditioner fortranPreConditioner = new FortranPreConditioner(
		source.loadSourceCode());
	TokenStream tokenStream = fortranPreConditioner.scan(lexer);
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
	    lexer = grammar.createLexer(FortranGrammarPartTester.class
		    .getClassLoader());
	}
    }

    private static synchronized void initializeParser(String production)
	    throws GrammarException {
	if (parsers.get(production) == null) {
	    Parser parser = grammar.createWithNewStartProduction(production)
		    .createParser(
			    FortranGrammarPartTester.class.getClassLoader());
	    parsers.put(production, parser);
	}
    }
}
