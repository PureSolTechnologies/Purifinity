package com.puresol.purifinity.coding.lang.java.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresol.purifinity.coding.lang.java7.grammar.JavaGrammar;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarException;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.LexerException;
import com.puresol.purifinity.uhura.lexer.TokenStream;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserException;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

public class JavaGrammarPartTester {

	public static final File PARSER_DIRECTORY = new File(
			"test/com/puresol/coding/lang/java/parsers");

	private static Grammar grammar = null;
	private static Lexer lexer = null;
	private static Map<String, Parser> parsers = new HashMap<>();

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
			lexer = getGrammar().createLexer(
					JavaGrammarPartTester.class.getClassLoader());
		}
	}

	private static synchronized void initializeParser(String production)
			throws GrammarException {
		if (parsers.get(production) == null) {
			Parser parser = getGrammar().createWithNewStartProduction(
					production).createParser(
					JavaGrammarPartTester.class.getClassLoader());
			parsers.put(production, parser);
		}
	}
}
