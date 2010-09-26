package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R417_ComplexLiteralConstantTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("complex-literal-constant", "(A,B)"));
		assertTrue(GrammarPartTester.test("complex-literal-constant", "(1,-2)"));
		assertTrue(GrammarPartTester.test("complex-literal-constant",
				"(+1.23,-4.56)"));
	}

}
