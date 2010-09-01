package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;
import java.io.StringReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.lr.LR1Parser;
import com.puresol.uhura.parser.lr.SLR1Parser;

public class ExprTest extends TestCase {

	@Test
	public void test() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar;
			grammar = FortranGrammar.get();
			grammar.getProductions().setNewStart("expr");
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new StringReader(
					"1 + 2 * 3 + ( 4 - 5)"));
			Parser parser = new LR1Parser(grammar);
			AST ast = parser.parse(tokenStream);
			new TreePrinter(System.out).println(ast);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LexerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (GrammarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
