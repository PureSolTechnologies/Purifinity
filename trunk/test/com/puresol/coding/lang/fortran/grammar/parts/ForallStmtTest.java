package com.puresol.coding.lang.fortran.grammar.parts;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class ForallStmtTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		fail();
		assertTrue(GrammarPartTester.test("forall-stmt",
				"FORALL (I = 1:10, J = 1:10, B(I, J) /= 0.0)\n"
						+ "A(I, J) = REAL (I + J - 2)\n"
						+ "B(I, J) = A(I, J) + B(I, J) * REAL (I * J)\n"
						+ "END FORALL"));
	}

}
