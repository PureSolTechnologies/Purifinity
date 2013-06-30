package com.puresol.purifinity.uhura.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresol.purifinity.trees.TreePrinter;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.TestGrammars;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.RegExpLexer;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.parser.lr.SLR1Parser;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.source.UnspecifiedSourceCodeLocation;

public class SLR1ParserTest {

    @Test
    public void testSimple() throws Throwable {
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	Parser parser = new SLR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("1*2+3"), new UnspecifiedSourceCodeLocation())));
	new TreePrinter(System.out).println(syntaxTree);
    }

    @Test
    public void testComplex() throws Throwable {
	Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
	Parser parser = new SLR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("((1*(2+3)+4*5)+6)*7"),
		new UnspecifiedSourceCodeLocation())));
	new TreePrinter(System.out).println(syntaxTree);
    }

}
