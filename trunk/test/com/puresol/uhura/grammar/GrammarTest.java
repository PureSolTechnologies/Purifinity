package com.puresol.uhura.grammar;

import java.io.File;
import java.util.Properties;

import org.junit.Test;

import com.puresol.uhura.grammar.production.ProductionSet;
import com.puresol.uhura.grammar.token.TokenDefinitionSet;
import com.puresol.utils.FileUtilities;
import com.puresol.utils.Persistence;

import junit.framework.TestCase;

public class GrammarTest extends TestCase {

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
		} catch (Throwable e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}
}
