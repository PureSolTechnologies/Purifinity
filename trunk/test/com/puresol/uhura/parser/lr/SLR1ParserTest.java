package com.puresol.uhura.parser.lr;

import java.io.StringReader;
import java.util.Properties;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.ast.ASTPrinter;
import com.puresol.uhura.ast.SyntaxTree;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.LexerException;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserException;

public class SLR1ParserTest extends TestCase {

	@Test
	public void test() {
		try {
			Logger.getRootLogger().setLevel(Level.TRACE);
			Grammar grammar = TestGrammars.getTestGrammarFromDragonBook();
			Parser parser = new SLR1Parser(new Properties(), grammar);
			Lexer lexer = new RegExpLexer(new Properties());
			lexer.scan(new StringReader("1*2+3"), grammar.getTokenDefinitions());
			parser.setTokenStream(lexer.getTokenStream());
			SyntaxTree syntaxTree = parser.call();
			new ASTPrinter(System.out).println(syntaxTree);
		} catch (ParserException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (LexerException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
