package com.puresoltechnologies.purifinity.coding.lang.java7.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.api.source.CodeLocation;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.grammar.GrammarException;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.LexerException;
import com.puresoltechnologies.parsers.impl.lexer.TokenStream;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserException;
import com.puresoltechnologies.parsers.impl.source.FixedCodeLocation;
import com.puresoltechnologies.purifinity.coding.lang.java7.grammar.JavaGrammar;

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
