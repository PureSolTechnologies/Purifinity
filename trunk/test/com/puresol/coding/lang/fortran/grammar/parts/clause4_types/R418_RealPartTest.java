package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R418_RealPartTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("real-part", "+1"));
		assertTrue(FortranGrammarPartTester.test("real-part", "0"));
		assertTrue(FortranGrammarPartTester.test("real-part", "-1"));
		assertTrue(FortranGrammarPartTester.test("real-part", "+1.0"));
		assertTrue(FortranGrammarPartTester.test("real-part", "0.0"));
		assertTrue(FortranGrammarPartTester.test("real-part", "-1.0"));
		assertTrue(FortranGrammarPartTester.test("real-part", "variable"));
	}

}
