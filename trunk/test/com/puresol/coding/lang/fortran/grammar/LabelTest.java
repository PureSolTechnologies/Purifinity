package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;
import java.io.StringReader;

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

public class LabelTest extends TestCase {

	@Test
	public void test() {
		try {
			Grammar grammar;
			grammar = FortranGrammar.get();
			grammar.getProductions().setNewStart("label");
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer.lex(new StringReader("12345"));
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
