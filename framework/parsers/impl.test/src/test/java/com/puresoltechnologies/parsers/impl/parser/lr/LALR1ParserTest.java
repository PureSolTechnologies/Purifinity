package com.puresoltechnologies.parsers.impl.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.api.TreePrinter;
import com.puresoltechnologies.parser.impl.grammar.TestGrammars;
import com.puresoltechnologies.parsers.impl.grammar.Grammar;
import com.puresoltechnologies.parsers.impl.lexer.Lexer;
import com.puresoltechnologies.parsers.impl.lexer.RegExpLexer;
import com.puresoltechnologies.parsers.impl.parser.Parser;
import com.puresoltechnologies.parsers.impl.parser.ParserTree;
import com.puresoltechnologies.parsers.impl.source.SourceCodeImpl;
import com.puresoltechnologies.parsers.impl.source.UnspecifiedSourceCodeLocation;

public class LALR1ParserTest {

	@Test
	public void testSimple() throws Throwable {
		Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
		Parser parser = new LALR1Parser(grammar);
		Lexer lexer = new RegExpLexer(grammar);
		ParserTree syntaxTree = parser.parse(lexer.lex(SourceCodeImpl
				.read(new StringReader("id=*id"),
						new UnspecifiedSourceCodeLocation())));
		new TreePrinter(System.out).println(syntaxTree);
	}

}
