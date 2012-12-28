package com.puresol.uhura.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserTree;
import com.puresol.uhura.source.SourceCode;
import com.puresol.uhura.source.UnspecifiedSource;

public class SLR1ParserTest {

    @Test
    public void testSimple() throws Throwable {
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	Parser parser = new SLR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("1*2+3"), new UnspecifiedSource())));
	new TreePrinter(System.out).println(syntaxTree);
    }

    @Test
    public void testComplex() throws Throwable {
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	Parser parser = new SLR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("((1*(2+3)+4*5)+6)*7"),
		new UnspecifiedSource())));
	new TreePrinter(System.out).println(syntaxTree);
    }

}
