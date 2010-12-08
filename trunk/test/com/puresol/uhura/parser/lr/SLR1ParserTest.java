package com.puresol.uhura.parser.lr;

import static org.junit.Assert.*;

import java.io.StringReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.ParserTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;

public class SLR1ParserTest {

	@Test
	public void testSimple() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
			Parser parser = new SLR1Parser(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			ParserTree syntaxTree = parser.parse(lexer.lex(new StringReader("1*2+3"),
					"SampleString"));
			new TreePrinter(System.out).println(syntaxTree);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testComplex() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
			Parser parser = new SLR1Parser(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			ParserTree syntaxTree = parser.parse(lexer.lex(new StringReader(
					"((1*(2+3)+4*5)+6)*7"), "SampleString"));
			new TreePrinter(System.out).println(syntaxTree);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
