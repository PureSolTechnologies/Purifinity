package com.puresol.coding.lang.fortran.grammar;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;

public class FortranGrammarTest {

    @Test
    public void testInstance() {
	Grammar grammar = FortranGrammar.getInstance();
	assertNotNull(grammar);
    }

    @Test
    public void testPrint() {
	Grammar grammar = FortranGrammar.getInstance();
	System.out.println(grammar);
    }

}
