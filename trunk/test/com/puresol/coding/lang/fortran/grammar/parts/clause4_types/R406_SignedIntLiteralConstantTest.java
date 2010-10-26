package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R406_SignedIntLiteralConstantTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "+1"));
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "0"));
		assertTrue(GrammarPartTester.test("signed-int-literal-constant", "-1"));
	}

}
