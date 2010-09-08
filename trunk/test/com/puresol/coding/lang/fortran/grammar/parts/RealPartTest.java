package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class RealPartTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("real-part", "+1"));
		assertTrue(GrammarPartTester.test("real-part", "0"));
		assertTrue(GrammarPartTester.test("real-part", "-1"));
		assertTrue(GrammarPartTester.test("real-part", "+1.0"));
		assertTrue(GrammarPartTester.test("real-part", "0.0"));
		assertTrue(GrammarPartTester.test("real-part", "-1.0"));
		assertTrue(GrammarPartTester.test("real-part", "variable"));
	}

}
