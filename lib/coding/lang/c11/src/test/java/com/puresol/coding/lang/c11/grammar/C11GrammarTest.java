package com.puresol.coding.lang.c11.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

public class C11GrammarTest {

    @BeforeClass
    public static void initialize() {
	URL url = C11Grammar.class.getResource(C11Grammar.GRAMMAR_RESOURCE);
	assertNotNull(url);
    }

    @Test
    public void testInstance() {
	Grammar grammar = C11Grammar.getInstance();
	assertNotNull(grammar);
    }

    @Test
    public void testPrint() {
	try {
	    Grammar grammar = C11Grammar.getInstance();
	    System.out.println(grammar);
	} catch (Throwable e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

    // @Test
    // public void testLR1() {
    // Logger.getRootLogger().setLevel(Level.TRACE);
    // try {
    // Parser parser = new LR1Parser(FortranGrammar.get());
    // parser.getParserTable();
    // } catch (GrammarException e) {
    // e.printStackTrace();
    // fail("No exception was expected!");
    // } catch (IOException e) {
    // e.printStackTrace();
    // fail("No exception was expected!");
    // }
    // }
}
