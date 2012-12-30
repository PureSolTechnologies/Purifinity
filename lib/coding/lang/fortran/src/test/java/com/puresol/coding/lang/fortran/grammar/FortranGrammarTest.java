package com.puresol.coding.lang.fortran.grammar;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

public class FortranGrammarTest extends TestCase {

    @Test
    public void testInstance() {
	Grammar grammar = FortranGrammar.getInstance();
	assertNotNull(grammar);
    }

    @Test
    public void testPrint() {
	try {
	    Grammar grammar = FortranGrammar.getInstance();
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
