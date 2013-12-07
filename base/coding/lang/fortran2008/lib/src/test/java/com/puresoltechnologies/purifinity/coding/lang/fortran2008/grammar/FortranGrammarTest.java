package com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.BeforeClass;
import org.junit.Test;

import com.puresoltechnologies.parser.impl.grammar.Grammar;
import com.puresoltechnologies.purifinity.coding.lang.fortran2008.grammar.FortranGrammar;

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
