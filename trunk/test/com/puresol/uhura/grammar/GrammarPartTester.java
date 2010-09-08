package com.puresol.uhura.grammar;

import java.io.StringReader;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserFactory;

public class GrammarPartTester {

	public static boolean test(Grammar grammar, String production, String text) {
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

}
