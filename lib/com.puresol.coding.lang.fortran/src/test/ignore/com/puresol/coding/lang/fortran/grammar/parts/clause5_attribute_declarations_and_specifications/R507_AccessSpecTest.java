package com.puresol.coding.lang.fortran.grammar.parts.clause5_attribute_declarations_and_specifications;

import org.junit.Test;

import junit.framework.TestCase;

import com.puresol.coding.lang.fortran.grammar.FortranGrammarPartTester;

public class R507_AccessSpecTest extends TestCase {

	@Test
	public void test() {
		assertTrue(FortranGrammarPartTester.test("access-spec", "PUBLIC"));
		assertTrue(FortranGrammarPartTester.test("access-spec", "PRIVATE"));
	}

}
