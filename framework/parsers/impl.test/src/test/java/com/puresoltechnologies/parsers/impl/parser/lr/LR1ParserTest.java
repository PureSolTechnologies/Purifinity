package com.puresoltechnologies.parsers.impl.parser.lr;

import static org.junit.Assert.assertNotNull;

import java.io.StringReader;

import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.TestGrammars;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.RegExpLexer;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.parser.lr.LR1Parser;
import com.puresoltechnologies.parsers.impl.source.SourceCode;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;

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
