package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class WhereStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("where-stmt",
				"WHERE (TEMP > 100.0) TEMP = TEMP - REDUCE_TEMP"));
		fail();
		assertTrue(GrammarPartTester.test("where-stmt",
				"WHERE (PRESSURE <= 1.0)\n"
						+ "PRESSURE = PRESSURE + INC_PRESSURE\n"
						+ "TEMP = TEMP - 5.0\n" + "ELSEWHERE\n"
						+ "RAINING = .TRUE.\n" + "END WHERE"));

	}

}
