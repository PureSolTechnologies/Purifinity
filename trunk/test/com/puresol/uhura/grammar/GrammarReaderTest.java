package com.puresol.uhura.grammar;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.ast.AST;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;
import com.puresol.uhura.parser.lr.SLR1Parser;
import com.puresol.uhura.parser.parsetable.LR0StateTransitionGraph;

import junit.framework.TestCase;

public class GrammarReaderTest extends TestCase {

	@Test
	public void testRead() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g"));
			assertTrue(reader.call());
			AST ast = reader.getSyntaxTree();
			TreePrinter printer = new TreePrinter(System.out);
			printer.println(ast);
			Grammar grammar = reader.getGrammar();
			System.out.println(grammar);
			Lexer lexer = new RegExpLexer(new Properties());
			lexer.scan(new StringReader("1 * 2\n + 3"),
					grammar.getTokenDefinitions());
			Parser parser = new SLR1Parser(grammar);
			System.out.println(parser.getParserTable());
			LR0StateTransitionGraph tg = new LR0StateTransitionGraph(grammar);
			System.out.println(tg);
			parser.setTokenStream(lexer.getTokenStream());
			AST syntaxTree = parser.call();
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
}
