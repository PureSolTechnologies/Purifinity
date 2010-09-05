package com.puresol.coding.lang.fortran.grammar.parts;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.GrammarPartTester;

public class LetterSpecTest extends TestCase {

	@Test
	public void test() {
		assertTrue(GrammarPartTester.test("letter-spec", "A-Z"));
	}

}
