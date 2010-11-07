package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.TokenStream;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.functions.Closure0;
import com.puresol.uhura.parser.functions.Goto0;
import com.puresol.uhura.parser.items.LR0ItemSetCollection;
import com.puresol.uhura.parser.lr.SLR1Parser;

public class GrammarReaderTest {

	@Test
	public void testRead() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g"));
			assertTrue(reader.call());
			AST ast = reader.getSyntaxTree();
			TreePrinter printer = new TreePrinter(System.out);
			printer.println(ast);
			Grammar grammar = reader.getGrammar();
			System.out.println(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer
					.lex(new StringReader("1 * 2\n + 3"));
			Parser parser = new SLR1Parser(grammar);
			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection tg = new LR0ItemSetCollection(grammar,
					closure0, goto0);
			System.out.println(tg);
			AST syntaxTree = parser.parse(tokenStream);
			new TreePrinter(System.out).println(syntaxTree);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testReadAutoGeneration() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			GrammarReader reader = new GrammarReader(
					new File(
							"test/com/puresol/uhura/grammar/TestGrammarForAutoGeneration.g"));
			assertTrue(reader.call());
			AST ast = reader.getSyntaxTree();
			TreePrinter printer = new TreePrinter(System.out);
			printer.println(ast);
			Grammar grammar = reader.getGrammar();
			System.out.println(grammar);
			Lexer lexer = new RegExpLexer(grammar);
			TokenStream tokenStream = lexer
					.lex(new StringReader("1 * 2\n + 3"));
			Parser parser = new SLR1Parser(grammar);
			Closure0 closure0 = new Closure0(grammar);
			Goto0 goto0 = new Goto0(closure0);
			LR0ItemSetCollection tg = new LR0ItemSetCollection(grammar,
					closure0, goto0);
			System.out.println(tg);
			AST syntaxTree = parser.parse(tokenStream);
			new TreePrinter(System.out).println(syntaxTree);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionOptionalList() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", ""));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1"));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 2"));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 3"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionList() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertFalse(GrammarPartTester.test(grammar, "List", ""));
			assertTrue(GrammarPartTester.test(grammar, "List", "1"));
			assertTrue(GrammarPartTester.test(grammar, "List", "1 2"));
			assertTrue(GrammarPartTester.test(grammar, "List", "1 3"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionOptionalPart() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertTrue(GrammarPartTester.test(grammar, "OptionalPart", ""));
			assertTrue(GrammarPartTester.test(grammar, "OptionalPart", "1"));
			assertFalse(GrammarPartTester.test(grammar, "OptionalPart", "1 2"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
