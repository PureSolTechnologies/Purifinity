package com.puresol.coding.lang.java.grammar;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.grammar.GrammarReader;

public class JavaGrammarTest {

    @Test
    public void testGetGrammarStream() {
	assertNotNull(JavaGrammar.class
		.getResource(JavaGrammar.GRAMMAR_RESOURCE));
    }

    @Test
    public void testReadGrammar() {
	try {
	    InputStream inputStream = JavaGrammar.class
		    .getResourceAsStream(JavaGrammar.GRAMMAR_RESOURCE);
	    try {
		new GrammarReader(inputStream);
	    } finally {
		inputStream.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	} catch (GrammarException e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }

    @Test
    public void testSingleton() {
	Grammar grammar = JavaGrammar.getInstance();
	assertNotNull(grammar);
	assertSame(grammar, JavaGrammar.getInstance());
    }

    @Test
    public void testPrint() {
	try {
	    Grammar grammar = JavaGrammar.getInstance();
	    System.out.println(grammar);
	} catch (Throwable e) {
	    e.printStackTrace();
	    fail("No exception was expected!");
	}
    }
}
