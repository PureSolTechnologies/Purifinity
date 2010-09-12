package com.puresol.coding.lang.fortran.grammar.parts.clause7_expressions_and_assignment;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R750_ForallConstructTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("forall-construct",
				"FORALL (I = 1:10, J = 1:10, B(I, J) /= 0.0)\n"
						+ "A(I, J) = REAL (I + J - 2)\n"
						+ "B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"
						+ "END FORALL"));
	}

}
