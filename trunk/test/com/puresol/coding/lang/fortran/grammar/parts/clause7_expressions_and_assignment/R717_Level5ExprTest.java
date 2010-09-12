package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R717_Level5ExprTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("level-5-expr", "A.EQV.B"));
		assertTrue(GrammarPartTester.test("level-5-expr", "A.NEQV.B"));
	}

}
