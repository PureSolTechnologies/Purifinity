package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class ImagPartTest extends TestCase {

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
