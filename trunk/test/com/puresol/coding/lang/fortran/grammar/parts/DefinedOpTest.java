package com.puresol.coding.lang.fortran.grammar.parts;

import junit.framework.TestCase;

import org.junit.Test;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class DefinedOpTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("defined-op", ".INVERSE."));
		assertTrue(GrammarPartTester.test("defined-op", ".DEFINED."));
	}
}
