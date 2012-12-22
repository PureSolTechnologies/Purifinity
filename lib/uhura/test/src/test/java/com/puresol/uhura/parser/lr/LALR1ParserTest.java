package com.puresol.uhura.parser.lr;

import java.io.File;
import java.io.StringReader;

import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.TestGrammars;
import com.puresol.uhura.lexer.Lexer;
import com.puresol.uhura.lexer.RegExpLexer;
import com.puresol.uhura.lexer.SourceCode;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.ParserTree;

public class LALR1ParserTest {

    @Test
    public void testSimple() throws Throwable {
	Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
	Parser parser = new LALR1Parser(grammar);
	Lexer lexer = new RegExpLexer(grammar);
	ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode.read(
		new StringReader("id=*id"), new File("SampleString")),
		"SampleString"));
	new TreePrinter(System.out).println(syntaxTree);
    }

}