package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.utils.PersistenceException;

public class FortranGrammarTest extends TestCase {

	@Test
	public void testInstance() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			Grammar grammar = FortranGrammar.getInstance().getGrammar();
			assertNotNull(grammar);
		} catch (PersistenceException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = FortranGrammar.getInstance().getGrammar();
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
