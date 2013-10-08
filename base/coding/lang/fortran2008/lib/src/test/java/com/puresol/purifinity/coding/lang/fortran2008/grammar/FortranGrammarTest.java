package com.puresol.purifinity.coding.lang.fortran2008.grammar;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresol.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;
import com.puresol.purifinity.uhura.grammar.Grammar;

public class FortranGrammarTest {

    @BeforeClass
    public static void initialize() {
	URL url = FortranGrammar.class
		.getResource(FortranGrammar.GRAMMAR_RESOURCE);
	assertNotNull(url);
    }

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
