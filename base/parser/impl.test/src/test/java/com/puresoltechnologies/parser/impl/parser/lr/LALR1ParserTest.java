package com.puresoltechnologies.parser.impl.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.TreePrinter;
import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.parser.impl.grammar.TestGrammars;
import com.puresoltechnologies.parser.impl.lexer.Lexer;
import com.puresoltechnologies.parser.impl.lexer.RegExpLexer;
import com.puresoltechnologies.parser.impl.parser.Parser;
import com.puresoltechnologies.parser.impl.parser.ParserTree;
import com.puresoltechnologies.parser.impl.parser.lr.LALR1Parser;
import com.puresoltechnologies.parser.impl.source.SourceCode;
import com.puresoltechnologies.parser.impl.source.UnspecifiedSourceCodeLocation;

public class LALR1ParserTest {

	@Test
	public void testSimple() throws Throwable {
		Grammar grammar = TestGrammars.getLALR1TestGrammarFromDragonBook();
		Parser parser = new LALR1Parser(grammar);
		Lexer lexer = new RegExpLexer(grammar);
		ParserTree syntaxTree = parser.parse(lexer.lex(SourceCode
				.read(new StringReader("id=*id"),
						new UnspecifiedSourceCodeLocation())));
		new TreePrinter(System.out).println(syntaxTree);
	}

}
