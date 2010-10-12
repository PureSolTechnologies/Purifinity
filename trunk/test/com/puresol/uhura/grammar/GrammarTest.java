package com.puresol.uhura.grammar;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.Persistence;
import com.puresol.utils.PersistenceException;

public class GrammarTest {

	@Test
	public void testSettersAndGetters() {
		try {
			Properties options = new Properties();
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
			ProductionSet productions = new ProductionSet();

			Grammar grammar = new Grammar(options, tokenDefinitions,
					productions);
			assertSame(options, grammar.getOptions());
			assertSame(tokenDefinitions, grammar.getTokenDefinitions());
			assertSame(productions, grammar.getProductions());
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testSerialization() {
		try {
			File file = new File("test", FileUtilities
					.classToRelativePackagePath(GrammarTest.class).toString()
					.replaceAll("\\.java", ".persist"));

			Properties options = new Properties();
			TokenDefinitionSet tokenDefinitions = new TokenDefinitionSet();
			ProductionSet productions = new ProductionSet();

			Grammar grammar = new Grammar(options, tokenDefinitions,
					productions);

			Persistence.persist(grammar, file);
			Grammar restoredGrammar = (Grammar) Persistence.restore(file);

			assertEquals(grammar, restoredGrammar);
		} catch (GrammarException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
