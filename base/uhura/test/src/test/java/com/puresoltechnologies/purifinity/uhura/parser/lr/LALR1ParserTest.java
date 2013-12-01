package com.puresoltechnologies.purifinity.uhura.parser.lr;

import java.io.StringReader;

import org.junit.Test;

import com.puresoltechnologies.commons.trees.TreePrinter;
import com.puresoltechnologies.purifinity.uhura.grammar.Grammar;
import com.puresoltechnologies.purifinity.uhura.grammar.TestGrammars;
import com.puresoltechnologies.purifinity.uhura.lexer.Lexer;
import com.puresoltechnologies.purifinity.uhura.lexer.RegExpLexer;
import com.puresoltechnologies.purifinity.uhura.parser.Parser;
import com.puresoltechnologies.purifinity.uhura.parser.ParserTree;
import com.puresoltechnologies.purifinity.uhura.parser.lr.LALR1Parser;
import com.puresoltechnologies.purifinity.uhura.source.SourceCode;
import com.puresoltechnologies.purifinity.uhura.source.UnspecifiedSourceCodeLocation;

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
