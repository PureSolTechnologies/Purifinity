package com.puresol.purifinity.uhura.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresol.commons.trees.TreePrinter;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.TestGrammars;
import com.puresol.purifinity.uhura.lexer.Lexer;
import com.puresol.purifinity.uhura.lexer.RegExpLexer;
import com.puresol.purifinity.uhura.parser.Parser;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.SourceCode;
import com.puresol.purifinity.uhura.source.UnspecifiedSourceCodeLocation;

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
