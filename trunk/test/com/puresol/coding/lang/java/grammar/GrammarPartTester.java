package com.puresol.coding.lang.java.grammar;

import java.io.File;
import java.io.StringReader;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserManager;

public class GrammarPartTester {

	public static final File PARSER_DIRECTORY = new File(
			"test/com/puresol/coding/lang/java/parsers");

	public static boolean test(String production, String text) {
		return test(production, text, true);
	}

	public static boolean test(String production, String text, boolean output) {
		try {
			final String PARSER_NAME = production + "-parser";
			if (output) {
				System.out.println("Testing production '" + production
						+ "' with text '" + text + "' ...");
			}
			Grammar grammar = JavaGrammar.getInstance().getGrammar();
			grammar = grammar.createWithNewStartProduction(production);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new StringReader(text));

			Parser parser = ParserManager.getManagerParser(PARSER_DIRECTORY,
					PARSER_NAME, grammar);
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
