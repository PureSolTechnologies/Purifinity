package com.puresoltechnologies.purifinity.framework.lang.java7.grammar;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.puresoltechnologies.parsers.grammar.Grammar;
import com.puresoltechnologies.parsers.grammar.GrammarException;
import com.puresoltechnologies.parsers.lexer.Lexer;
import com.puresoltechnologies.parsers.lexer.LexerException;
import com.puresoltechnologies.parsers.lexer.TokenStream;
import com.puresoltechnologies.parsers.parser.Parser;
import com.puresoltechnologies.parsers.parser.ParserException;
import com.puresoltechnologies.parsers.source.FixedCodeLocation;
import com.puresoltechnologies.parsers.source.SourceCodeLocation;
import com.puresoltechnologies.purifinity.server.plugin.java7.grammar.JavaGrammar;

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

	public static boolean test(String production, SourceCodeLocation source)
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
