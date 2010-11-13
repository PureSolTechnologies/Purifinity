package com.puresol.uhura.grammar;

import java.io.File;
import java.io.StringReader;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactory;
import com.puresol.uhura.parser.ParserManager;

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

	public static boolean test(Grammar grammar, String production, String text) {
		return test(grammar, production, text, false);
	}

	public static boolean test(Grammar grammar, String production, String text,
			boolean output) {
		try {
			System.out.println("Testing production '" + production
					+ "' with text '" + text + "' ...");
			grammar = grammar.createWithNewStartProduction(production);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new StringReader(text));

			Parser parser = ParserFactory.create(grammar);
			AST ast = parser.parse(tokenStream);
			new TreePrinter(System.out).println(ast);
			System.out.println("passed.");
			return true;
		} catch (Throwable e) {
			System.out.println("failed.");
			return false;
		}
	}

	public static boolean test(File parserDirectory, String parserName,
			Grammar grammar, String production, String text) {
		return test(parserDirectory, parserName, grammar, production, text,
				false);
	}

	public static boolean test(File parserDirectory, String parserName,
			Grammar grammar, String production, String text, boolean output) {
		try {
			if (output) {
				System.out.println("Testing text '" + production
						+ "' with text '" + text + "' ...");
			}
			grammar = grammar.createWithNewStartProduction(production);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new StringReader(text));

			Parser parser = ParserManager.getManagerParser(parserDirectory,
					parserName, grammar);
			AST ast = parser.parse(tokenStream);
			if (output) {
				new TreePrinter(System.out).println(ast);
				System.out.println("passed.");
			}
			return true;
		} catch (Throwable e) {
			e.printStackTrace();
			System.out.println("failed.");
			return false;
		}

	}
}
