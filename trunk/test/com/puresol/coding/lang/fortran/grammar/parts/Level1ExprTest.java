package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class Level1ExprTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("level-1-expr", "A"));
		assertTrue(GrammarPartTester.test("level-1-expr", ".INVERSE. B"));
		assertTrue(GrammarPartTester.test("level-1-expr", ".INVERSE. (A + B)"));
	}

}
