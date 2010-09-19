package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R810_CriticalConstructTest extends TestCase {

	@Test
	public void testSubroutine() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("critical-construct",
				"      CRITICAL                  \n"
						+ "      END CRITICAL    \n"));
		assertTrue(GrammarPartTester.test("critical-construct",
				"      CRITICAL                                           \n"
						+ "      GLOBAL_COUNTER[1] = GLOBAL_COUNTER[1] + 1\n"
						+ "      END CRITICAL                             \n"));
	}

}
