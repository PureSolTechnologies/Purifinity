package com.puresol.uhura.parser.packrat;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

import com.puresol.trees.TreePrinter;
import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarConverter;
import com.puresol.uhura.grammar.GrammarFile;
import com.puresol.uhura.parser.ParserTree;

/**
 * This tests are taken from the paper 'Packrat Parsers Can Support Left
 * Recursion' by Warth et.al.. The grammar is from figure 10 and the results can
 * be found in table 1.
 * 
 * @author Rick-Rainer Ludwig
 * 
 */
public class PackratParserExtTest {

    private ParserTree parseText(String text) throws Throwable {
	InputStream inputStream = getClass()
		.getResourceAsStream(
			"/com/puresol/uhura/grammar/TestGrammarForJavaPrimaryExpressions.g");
	assertNotNull(inputStream);
	try {
	    GrammarFile grammarFile = new GrammarFile(inputStream);
	    Grammar grammar = new GrammarConverter(grammarFile.getAST())
		    .getGrammar();
	    assertNotNull(grammar);
	    PackratParser parser = new PackratParser(grammar);
	    ParserTree parseTree = parser.parse(text, "TEXT_PARSE");
	    assertNotNull(parseTree);
	    TreePrinter printer = new TreePrinter(System.out);
	    printer.println(parseTree);
	    return parseTree;
	} finally {
	    inputStream.close();
	}
    }

    @Test
    public void test1() throws Throwable {
	parseText("this");
    }

    @Test
    public void test2() throws Throwable {
	parseText("this.x");
    }

    @Test
    public void test3() throws Throwable {
	parseText("this.x.y");
    }

    @Test
    public void test4() throws Throwable {
	parseText("this.x.m()");
    }

    @Test
    public void test5() throws Throwable {
	parseText("x[i][j].y");
    }
}
