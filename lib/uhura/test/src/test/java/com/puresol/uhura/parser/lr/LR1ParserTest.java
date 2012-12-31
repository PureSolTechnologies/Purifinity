package com.puresol.uhura.parser.lr;

import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.UnspecifiedSourceCodeLocation;

public class LR1ParserTest {

    @Test
    public void testSimple() throws Throwable {
	// Logger.getRootLogger().setLevel(Level.TRACE);
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	assertNotNull(grammar);
	Parser parser = new LR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("1*2+3"), new UnspecifiedSourceCodeLocation())));
	assertNotNull(syntaxTree);
    }

    @Test
    public void testComplex() throws Throwable {
	// Logger.getRootLogger().setLevel(Level.TRACE);
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	Lexer lexer = new RegExpLexer(grammar);
	Parser parser = new LR1Parser(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("((1*(2+3)+4*5)+6)*7"),
		new UnspecifiedSourceCodeLocation())));
	assertNotNull(syntaxTree);
	// new TreePrinter(System.out).println(syntaxTree);
    }
}
