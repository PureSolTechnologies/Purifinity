package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;

public class GrammarReaderTest {

	@Test
	public void testInstance() {
		try {
			assertNotNull(new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g")));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testInitValues() {
		try {
			GrammarReader grammar = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g"));
			assertEquals(null, grammar.getGrammar());
			assertEquals(null, grammar.getSyntaxTree());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testReadTestGrammar() {
		try {
			// Logger.getRootLogger().setLevel(Level.TRACE);
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestGrammar.g"));
			assertTrue(reader.call());
			assertNotNull(reader.getSyntaxTree());
			Grammar grammar = reader.getGrammar();
			assertNotNull(grammar);
			assertEquals(Visibility.IGNORED, grammar.getTokenDefinitions()
					.getDefinition("NEWLINE").getVisibility());
			assertEquals(Visibility.HIDDEN, grammar.getTokenDefinitions()
					.getDefinition("WHITESPACE").getVisibility());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testReadAutoGeneration() {
		try {
			// Logger.getRootLogger().setLevel(Level.TRACE);
			GrammarReader reader = new GrammarReader(
					new File(
							"test/com/puresol/uhura/grammar/TestGrammarForAutoGeneration.g"));
			assertTrue(reader.call());
			assertNotNull(reader.getSyntaxTree());
			Grammar grammar = reader.getGrammar();
			assertNotNull(grammar);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionOptionalList() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertNotNull(grammar);
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", ""));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1"));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 2"));
			assertTrue(GrammarPartTester.test(grammar, "OptionalList", "1 3"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionList() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertNotNull(grammar);
			assertFalse(GrammarPartTester.test(grammar, "List", ""));
			assertTrue(GrammarPartTester.test(grammar, "List", "1"));
			assertTrue(GrammarPartTester.test(grammar, "List", "1 2"));
			assertTrue(GrammarPartTester.test(grammar, "List", "1 3"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testAutoConstructionOptionalPart() {
		try {
			GrammarReader reader = new GrammarReader(new File(
					"test/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
			assertTrue(reader.call());
			Grammar grammar = reader.getGrammar();
			assertNotNull(grammar);
			assertTrue(GrammarPartTester.test(grammar, "OptionalPart", ""));
			assertTrue(GrammarPartTester.test(grammar, "OptionalPart", "1"));
			assertFalse(GrammarPartTester.test(grammar, "OptionalPart", "1 2"));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

}
