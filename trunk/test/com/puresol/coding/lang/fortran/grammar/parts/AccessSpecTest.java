package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class AccessSpecTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("access-spec", "PUBLIC"));
		assertTrue(GrammarPartTester.test("access-spec", "PRIVATE"));
	}

}
