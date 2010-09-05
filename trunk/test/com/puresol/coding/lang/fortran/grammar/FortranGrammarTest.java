package com.puresol.coding.lang.fortran.grammar;

import java.io.IOException;

import junit.framework.TestCase;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.puresol.uhura.grammar.Grammar;
import com.puresol.uhura.grammar.GrammarException;
import com.puresol.uhura.parser.Parser;
import com.puresol.uhura.parser.lr.LR1Parser;

public class FortranGrammarTest extends TestCase {

	@Test
	public void testSingleton() {
		try {
			Logger.getRootLogger().setLevel(Level.DEBUG);
			Grammar grammar = FortranGrammar.get();
			assertNotNull(grammar);
			assertSame(grammar, FortranGrammar.get());
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

	@Test
	public void testPrint() {
		try {
			Grammar grammar = FortranGrammar.get();
			System.out.println(grammar);
		} catch (IOException e) {
			e.printStackTrace();
			fail("No exception was expected!");
		}
	}

//	@Test
//	public void testLR1() {
//		Logger.getRootLogger().setLevel(Level.TRACE);
//		try {
//			Parser parser = new LR1Parser(FortranGrammar.get());
//			parser.getParserTable();
//		} catch (GrammarException e) {
//			e.printStackTrace();
//			fail("No exception was expected!");
//		} catch (IOException e) {
//			e.printStackTrace();
//			fail("No exception was expected!");
//		}
//	}
}
