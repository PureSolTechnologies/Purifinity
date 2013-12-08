package com.puresoltechnologies.parsers.impl.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.impl.TreePrinter;
import com.puresoltechnologies.parser.impl.grammar.TestGrammars;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.RegExpLexer;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.parser.lr.SLR1Parser;
import com.puresoltechnologies.parsers.impl.source.SourceCode;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;

public class SLR1ParserTest {

	@Test
	public void testSimple() throws Throwable {
		Grammar grammar = TestGrammars.getSLR1TestGrammarFromDragonBook();
		Parser parser = new SLR1Parser(grammar);
		Lexer lexer = new RegExpLexer(grammar);
		ParserTree syntaxTree = parser
				.parse(lexer.lex(SourceCode.read(new StringReader("1*2+3"),
						new UnspecifiedSourceCodeLocation())));
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
