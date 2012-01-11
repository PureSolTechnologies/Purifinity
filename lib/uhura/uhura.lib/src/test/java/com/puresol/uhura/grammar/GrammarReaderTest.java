package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.puresol.uhura.grammar.token.Visibility;

public class GrammarReaderTest {

	@Test
	public void testInstance() {
		try {
			assertNotNull(new GrammarReader(getClass().getResourceAsStream(
					"/com/puresol/uhura/grammar/TestGrammar.g")));
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testInitValues() {
		try {
			GrammarReader grammar = new GrammarReader(getClass()
					.getResourceAsStream(
							"/com/puresol/uhura/grammar/TestGrammar.g"));
			assertNotNull(grammar.getGrammar());
			assertNotNull(grammar.getAST());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testReadTestGrammar() {
		try {
			GrammarReader reader = new GrammarReader(getClass()
					.getResourceAsStream(
							"/com/puresol/uhura/grammar/TestGrammar.g"));
			assertNotNull(reader.getAST());
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
			GrammarReader reader = new GrammarReader(
					getClass()
							.getResourceAsStream(
									"/com/puresol/uhura/grammar/TestGrammarForAutoGeneration.g"));
			assertNotNull(reader.getAST());
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
			GrammarReader reader = new GrammarReader(
					getClass()
							.getResourceAsStream(
									"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
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
			GrammarReader reader = new GrammarReader(
					getClass()
							.getResourceAsStream(
									"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
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
			GrammarReader reader = new GrammarReader(
					getClass()
							.getResourceAsStream(
									"/com/puresol/uhura/grammar/TestRuleAutoGeneration.g"));
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