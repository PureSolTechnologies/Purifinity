package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class Level2ExprTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("level-2-expr", "A"));
		assertTrue(GrammarPartTester.test("level-2-expr", "B ** C"));
		assertTrue(GrammarPartTester.test("level-2-expr", "D * E"));
		assertTrue(GrammarPartTester.test("level-2-expr", "+1"));
		assertTrue(GrammarPartTester.test("level-2-expr", "F - I"));
	}

}
