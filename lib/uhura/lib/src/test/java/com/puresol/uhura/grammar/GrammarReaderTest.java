package com.puresol.uhura.grammar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;

public class GrammarReaderTest {

    @Test
    public void testInstance() throws GrammarException, IOException {
	assertNotNull(new GrammarReader(getClass().getResourceAsStream(
		"/com/puresol/uhura/grammar/TestGrammar.g")));
    }

    @Test
    public void testInitValues() throws GrammarException, IOException {
	GrammarReader grammar = new GrammarReader(
		getClass().getResourceAsStream(
			"/com/puresol/uhura/grammar/TestGrammar.g"));
	try {
	    assertNotNull(grammar.getGrammar());
	    assertNotNull(grammar.getAST());
	} finally {
	    grammar.close();
	}
    }

    @Test
    public void testReadTestGrammar() throws GrammarException, IOException {
	GrammarReader reader = new GrammarReader(
		getClass().getResourceAsStream(
			"/com/puresol/uhura/grammar/TestGrammar.g"));
	try {
	    assertNotNull(reader.getAST());
	    Grammar grammar = reader.getGrammar();
	    assertNotNull(grammar);
	    assertEquals(Visibility.IGNORED, grammar.getTokenDefinitions()
		    .getDefinition("NEWLINE").getVisibility());
	    assertEquals(Visibility.HIDDEN, grammar.getTokenDefinitions()
		    .getDefinition("WHITESPACE").getVisibility());
	} finally {
	    reader.close();
	}
    }

    @Test
    public void testReadAutoGeneration() throws GrammarException, IOException {
	GrammarReader reader = new GrammarReader(
		getClass()
			.getResourceAsStream(
				"/com/puresol/uhura/grammar/TestGrammarForAutoGeneration.g"));
	try {
	    assertNotNull(reader.getAST());
	    Grammar grammar = reader.getGrammar();
	    assertNotNull(grammar);
	} finally {
	    reader.close();
	}
    }

    @Test
    public void testAutoConstructionOptionalList() throws GrammarException,
	    IOException {
	GrammarReader reader = new GrammarReader(getClass()
		.getResourceAsStream(
			"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
	try {
	    Grammar grammar = reader.getGrammar();
	    assertNotNull(grammar);
	    assertTrue(GrammarPartTester.test(grammar, "OptionalList", ""));
	    assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1"));
	    assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 2"));
	    assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 3"));
	} finally {
	    reader.close();
	}
    }

    @Test
    public void testAutoConstructionList() throws GrammarException, IOException {
	GrammarReader reader = new GrammarReader(getClass()
		.getResourceAsStream(
			"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
	try {
	    Grammar grammar = reader.getGrammar();
	    assertNotNull(grammar);
	    assertFalse(GrammarPartTester.test(grammar, "List", ""));
	    assertTrue(GrammarPartTester.test(grammar, "List", "1"));
	    assertTrue(GrammarPartTester.test(grammar, "List", "1 2"));
	    assertTrue(GrammarPartTester.test(grammar, "List", "1 3"));
	} finally {
	    reader.close();
	}
    }

    @Test
    public void testAutoConstructionOptionalPart() throws GrammarException,
	    IOException {
	GrammarReader reader = new GrammarReader(getClass()
		.getResourceAsStream(
			"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
	try {
	    Grammar grammar = reader.getGrammar();
	    assertNotNull(grammar);
	    assertTrue(GrammarPartTester.test(grammar, "OptionalPart", ""));
	    assertTrue(GrammarPartTester.test(grammar, "OptionalPart", "1"));
	    assertFalse(GrammarPartTester.test(grammar, "OptionalPart", "1 2"));
	} finally {
	    reader.close();
	}
    }
}
