package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R741_WhereStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("where-stmt",
				"WHERE (TEMP > 100.0) TEMP = TEMP - REDUCE_TEMP"));
	}

}
