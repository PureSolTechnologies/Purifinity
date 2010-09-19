package com.puresol.coding.lang.fortran.grammar.parts.clause8_execution_control;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R802_AssociateConstructTest extends TestCase {

	@Test
	public void testSubroutine() {
		assertTrue(GrammarPartTester.test("associate-construct",
				"      ASSOCIATE ( Z => EXP(-(X**2+Y**2)) * COS(THETA) )\n"
						+ "          PRINT *, A+Z, A-Z                      \n"
						+ "      END ASSOCIATE                          \n"));
		assertTrue(GrammarPartTester.test("associate-construct",
				"      ASSOCIATE ( XC => AX%B(I,J)%C )              \n"
						+ "          XC%DV = XC%DV + PRODUCT(XC%EV(1:N))\n"
						+ "      END ASSOCIATE                      \n"));
		assertTrue(GrammarPartTester.test("associate-construct",
				"      ASSOCIATE ( ARRAY => AX%B(I,:)%C )   \n"
						+ "          ARRAY(N)%EV = ARRAY(N-1)%EV\n"
						+ "      END ASSOCIATE              \n"));
		assertTrue(GrammarPartTester
				.test("associate-construct",
						"      ASSOCIATE ( W => RESULT(I,J)%W, ZX => AX%B(I,J)%D, ZY => AY%B(I,J)%D )\n"
								+ "          W = ZX*X + ZY*Y\n"
								+ "      END ASSOCIATE      \n"));
	}
}
