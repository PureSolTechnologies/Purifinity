package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.GrammarException;
import com.puresoltechnologies.parser.impl.lexer.Lexer;
import com.puresoltechnologies.parser.impl.lexer.LexerException;
import com.puresoltechnologies.parser.impl.lexer.TokenStream;
import com.puresoltechnologies.parser.impl.parser.Parser;
import com.puresoltechnologies.parser.impl.parser.ParserException;
import com.puresoltechnologies.parser.impl.source.CodeLocation;
import com.puresoltechnologies.parser.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.coding.lang.fortran2008.FortranPreConditioner;
import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;

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
