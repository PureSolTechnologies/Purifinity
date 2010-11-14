package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R406_SignedIntLiteralConstantTest extends TestCase {

	@Test
	public void test() {
		Logger.getRootLogger().setLevel(Level.TRACE);
		assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant", "+1"));
		assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant", "0"));
		assertTrue(FortranGrammarPartTester.test("signed-int-literal-constant", "-1"));
	}

}
