package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R832_IfConstructTest extends TestCase {

	@Test
	public void testSubroutine() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("if-construct", "IF (A > 0.0) THEN\n"
				+ "B = SQRT (A) \n" + "C = LOG (A)\n" + "END IF"));
	}

}
