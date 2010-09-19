package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R742_WhereConstructTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("where-construct",
				"      WHERE (PRESSURE <= 1.0)        \n"
						+ "      END WHERE            \n"));
		assertTrue(GrammarPartTester.test("where-construct",
				"      WHERE (PRESSURE <= 1.0)        \n"
						+ "      A=1                  \n"
						+ "      END WHERE            \n"));
		assertTrue(GrammarPartTester.test("where-construct",
				"      WHERE (PRESSURE <= 1.0)                     \n"
						+ "      PRESSURE = PRESSURE + INC_PRESSURE\n"
						+ "      TEMP = TEMP - 5.0                 \n"
						+ "      ELSEWHERE                         \n"
						+ "      RAINING = .TRUE.                  \n"
						+ "      END WHERE                         \n"));
	}

}
