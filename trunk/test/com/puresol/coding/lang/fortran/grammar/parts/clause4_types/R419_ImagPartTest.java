package com.puresol.coding.lang.fortran.grammar.parts.clause4_types;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class R419_ImagPartTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("imag-part", "+1"));
		assertTrue(GrammarPartTester.test("imag-part", "0"));
		assertTrue(GrammarPartTester.test("imag-part", "-1"));
		assertTrue(GrammarPartTester.test("imag-part", "+1.0"));
		assertTrue(GrammarPartTester.test("imag-part", "0.0"));
		assertTrue(GrammarPartTester.test("imag-part", "-1.0"));
		assertTrue(GrammarPartTester.test("imag-part", "variable"));
	}

}
