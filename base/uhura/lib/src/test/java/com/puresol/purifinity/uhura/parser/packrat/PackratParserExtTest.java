package com.puresol.purifinity.uhura.parser.packrat;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

import com.puresol.commons.trees.TreePrinter;
import com.puresol.purifinity.uhura.grammar.Grammar;
import com.puresol.purifinity.uhura.grammar.GrammarConverter;
import com.puresol.purifinity.uhura.grammar.GrammarFile;
import com.puresol.purifinity.uhura.parser.ParserTree;
import com.puresol.purifinity.uhura.source.CodeLocation;
import com.puresol.purifinity.uhura.source.FixedCodeLocation;

/**
 * This tests are taken from the paper 'Packrat Parsers Can Support Left
 * Recursion' by Warth et.al.. The grammar is from figure 10 and the results can
 * be found in table 1.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackratParserExtTest {

	private ParserTree parseText(CodeLocation source) throws Throwable {
		InputStream inputStream = getClass()
				.getResourceAsStream(
						"/com/puresol/purifinity/uhura/grammar/TestGrammarForJavaPrimaryExpressions.g");
		assertNotNull(inputStream);
		try {
			GrammarFile grammarFile = new GrammarFile(inputStream);
			try {
				Grammar grammar = new GrammarConverter(grammarFile.getAST())
						.getGrammar();
				assertNotNull(grammar);
				PackratParser parser = new PackratParser(grammar);
				ParserTree parseTree = parser.parse(source.loadSourceCode());
				assertNotNull(parseTree);
				TreePrinter printer = new TreePrinter(System.out);
				printer.println(parseTree);
				return parseTree;
			} finally {
				grammarFile.close();
			}
		} finally {
			inputStream.close();
		}
	}

	@Test
	public void test1() throws Throwable {
		parseText(new FixedCodeLocation("this"));
	}

	@Test
	public void test2() throws Throwable {
		parseText(new FixedCodeLocation("this.x"));
	}

	@Test
	public void test3() throws Throwable {
		parseText(new FixedCodeLocation("this.x.y"));
	}

	@Test
	public void test4() throws Throwable {
		parseText(new FixedCodeLocation("this.x.m()"));
	}

	@Test
	public void test5() throws Throwable {
		parseText(new FixedCodeLocation("x[i][j].y"));
	}
}
